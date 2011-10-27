package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.ui.dialog.ErrorDialog;
import hu.ideaimpl.mageofscrum.client.ui.dialog.ProjectDialog;
import hu.ideaimpl.mageofscrum.client.view.ProjectsView;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.SingleSelectionModel;

public class ProjectActivity extends AbstractActivity {
	private ProjectsView view = ClientFactory.Util.getClientFactory().getProjectsView();
	private SingleSelectionModel<ProjectDO> projectSelectionMode = new SingleSelectionModel<ProjectDO>();
	private ArrayList<UserDO> owners;
	private ArrayList<TeamDO> teams;
	private ArrayList<ProjectDO> projects;
	private ProjectDialog dialog = new ProjectDialog();
	private List<String> ownersLabels = new ArrayList<String>();
	private List<String> teamsLabels = new ArrayList<String>();

	public ProjectActivity() {
		owners = new ArrayList<UserDO>();
		teams = new ArrayList<TeamDO>();
		view.setSelectionModel(projectSelectionMode);
		projects = new ArrayList<ProjectDO>();
		ManagerService.Util.getService().fetchProjects(new AsyncCallback<ArrayList<ProjectDO>>() {

			@Override
			public void onSuccess(ArrayList<ProjectDO> result) {
				projects = result;
				 view.getProjectsTable().setRowData(projects);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});
		ManagerService.Util.getService().fetchOwners(new AsyncCallback<ArrayList<UserDO>>() {

			@Override
			public void onSuccess(ArrayList<UserDO> result) {
				owners = result;
				for (UserDO user : owners) {
					ownersLabels.add(user.getUsername());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});
		ManagerService.Util.getService().fetchTeams(new AsyncCallback<ArrayList<TeamDO>>() {

			@Override
			public void onSuccess(ArrayList<TeamDO> result) {
				teams = result;
				for(TeamDO team : teams){
					teamsLabels.add(team.getName());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});

		bind();
	}

	private void bind() {
		view.getDetailsBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnSelectionChanged();
			}
		});
		view.getCreateBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnCreateBtnClicked();
			}
		});
		view.getDeleteBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (projectSelectionMode.getSelectedObject() != null) {
					doOnDeleteBtnClicked();
				}
			}
		});
		dialog.getSaveBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (dialog.validate()) {
					doOnSaveBtnClicked();
				}
			}
		});
	}

	protected void doOnCreateBtnClicked() {
		dialog.clearForm();
		dialog.center();
		dialog.show();
		
		dialog.getTeamsCombo().addItems(teamsLabels, teams);
		dialog.getOwnersCombo().addItems(ownersLabels, owners);
	}

	protected void doOnDeleteBtnClicked() {
		final ProjectDO selectedProj = projectSelectionMode.getSelectedObject();
		ManagerService.Util.getService().deleteProject(selectedProj.getId(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				dialog.clearForm();
				projects.remove(selectedProj);
				view.getProjectsTable().setRowData(projects);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});
	}

	protected void doOnSaveBtnClicked() {
		ManagerService.Util.getService().saveProject(dialog.getFormData(), new AsyncCallback<ProjectDO>() {

			@Override
			public void onSuccess(ProjectDO result) {
				dialog.hide();
				ProjectDO existProject = null;
				for (ProjectDO p : projects) {
					if (p.getId() == result.getId()) {
						existProject = p;
						break;
					}
				}
				if (existProject != null) {
					projects.remove(existProject);
				}
				projects.add(result);
				view.getProjectsTable().setRowData(projects);
				projectSelectionMode.setSelected(result, true);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});

	}

	private void doOnSelectionChanged() {
		ProjectDO selectedObject = projectSelectionMode.getSelectedObject();
		dialog.clearForm();
		dialog.setFormData(selectedObject);
		dialog.getTeamsCombo().addItems(teamsLabels, teams);
		dialog.getOwnersCombo().addItems(ownersLabels, owners);
		dialog.getTeamsCombo().selectItem(selectedObject.getTeamName());
		dialog.getOwnersCombo().selectItem(selectedObject.getOwnerName());
		dialog.center();
		dialog.show();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		dialog.clearForm();
		panel.setWidget(view);
	}

}
