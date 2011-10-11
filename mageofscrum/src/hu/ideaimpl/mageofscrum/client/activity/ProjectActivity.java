package hu.ideaimpl.mageofscrum.client.activity;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.view.ProjectsView;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

public class ProjectActivity extends AbstractActivity {
	private ProjectsView projectView = ClientFactory.Util.getClientFactory().getProjectsView();
	private SingleSelectionModel<ProjectDO> projectSelectionMode = new SingleSelectionModel<ProjectDO>();
	private ArrayList<UserDO> owners; 
	private ArrayList<TeamDO> teams;
	private ArrayList<ProjectDO> projects;
	
	public ProjectActivity() {
		owners = new ArrayList<UserDO>();
		teams = new ArrayList<TeamDO>();
		projectView.setSelectionModel(projectSelectionMode);
		projects = new ArrayList<ProjectDO>();
		projectView.initView();
		ManagerService.Util.getService().fetchProjects(new AsyncCallback<ArrayList<ProjectDO>>() {
			
			@Override
			public void onSuccess(ArrayList<ProjectDO> result) {
				projects = result;
				projectView.setTableData(projects);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
		ManagerService.Util.getService().fetchOwners(new AsyncCallback<ArrayList<UserDO>>() {
			
			@Override
			public void onSuccess(ArrayList<UserDO> result) {
				owners = result;
				projectView.setOwnerCombo(owners);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
		ManagerService.Util.getService().fetchTeams(new AsyncCallback<ArrayList<TeamDO>>() {
			
			@Override
			public void onSuccess(ArrayList<TeamDO> result) {
				teams = result;
				projectView.setTeamCombo(teams);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
		
		bind();
	}
	
	private void bind(){
		projectSelectionMode.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				doOnSelectionChanged();
			}
		});
		projectView.getSaveBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(projectView.validateForm()){
					doOnSaveBtnClicked();
				}
			}
		});
	}
	
	protected void doOnSaveBtnClicked() {
		ManagerService.Util.getService().saveProject(projectView.getFormData(), new AsyncCallback<ProjectDO>() {
			
			@Override
			public void onSuccess(ProjectDO result) {
				System.out.println("success");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
		
	}

	private void doOnSelectionChanged(){
		ProjectDO selectedObject = projectSelectionMode.getSelectedObject();
		int ownerIndex = 0;
		int teamIndex = 0;
		for(UserDO user : owners){
			if(user.getEmail().equals(selectedObject.getOwnerName())){
				break;
			}
			ownerIndex++;
		}
		for(TeamDO team : teams){
			if(team.getName().equals(selectedObject.getTeamName())){
				break;
			}
			teamIndex++;
		}
		projectView.setFormData(selectedObject, ownerIndex, teamIndex);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(projectView);
	}

}