package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.ui.dialog.ErrorDialog;
import hu.ideaimpl.mageofscrum.client.view.TeamsView;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;
import java.util.Set;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class TeamActivity extends AbstractActivity {
	private TeamsView view;
	private SingleSelectionModel<TeamDO> teamSelectionModel = new SingleSelectionModel<TeamDO>();
	private MultiSelectionModel<UserDO> memebersSelectionModel = new MultiSelectionModel<UserDO>();
	private MultiSelectionModel<UserDO> othersSelectionModel = new MultiSelectionModel<UserDO>();
	
	private ArrayList<UserDO> members = null;
	private ArrayList<UserDO> others = null;
	private ArrayList<TeamDO> teams = new ArrayList<TeamDO>();
	
	public TeamActivity(ClientFactory clientFactory) {
		view = clientFactory.getTeamsView();
		view.getTeamsList().setSelectionModel(teamSelectionModel);
		view.getFromList().setSelectionModel(memebersSelectionModel);
		view.getToList().setSelectionModel(othersSelectionModel);
		initTeams();
		bind();
	}
	
	private void initTeams() {
		ManagerService.Util.getService().fetchTeams(new AsyncCallback<ArrayList<TeamDO>>() {
			
			@Override
			public void onSuccess(ArrayList<TeamDO> result) {
				teams = result;
				view.getTeamsList().setRowData(teams);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Team data fetch failed!");
			}
		});
	}

	private void bind(){
		teamSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				doOnSelectionChanged();
			}
		});
		view.getAddBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnAddBtnClicked();
			}
		});
		view.getRemoveBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnRemoveBtnClicked();
			}
		});
		view.getCreateBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnCreateBtnClicked();
			}
		});
		view.getDeleteBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnDeleteBtnClicked();
			}
		});
	}
	
	private void doOnDeleteBtnClicked() {
		final TeamDO selectedTeam = teamSelectionModel.getSelectedObject();
		if (selectedTeam != null && teams.contains(selectedTeam)) {
			ManagerService.Util.getService().deleteTeam(selectedTeam.getId(), new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					teams.remove(selectedTeam);
					view.getTeamsList().setRowData(teams);
					view.getFromList().setRowData(new ArrayList<UserDO>());
					view.getToList().setRowData(new ArrayList<UserDO>());
				}

				@Override
				public void onFailure(Throwable caught) {
					ErrorDialog.show("Server error", caught.getMessage());
				}
			});
		}
	}

	private void doOnCreateBtnClicked() {
		String value = view.getTeamName().getValue();
		if (value != null && !value.isEmpty()) {
			ManagerService.Util.getService().addTeam(value, new AsyncCallback<TeamDO>() {

				@Override
				public void onSuccess(TeamDO result) {
					teams.add(result);
					view.getTeamsList().setRowData(teams);
					view.getTeamName().setValue("");
				}

				@Override
				public void onFailure(Throwable caught) {
					ErrorDialog.show("Server error", caught.getMessage());
				}
			});
		}
	}

	private void doOnRemoveBtnClicked() {
		Long selectedTeam = teamSelectionModel.getSelectedObject().getId();
		final Set<UserDO> teamMembers = memebersSelectionModel.getSelectedSet();
		ManagerService.Util.getService().removeUsersFromTeam(convertUserIds(teamMembers), selectedTeam, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				members.removeAll(teamMembers);
				others.addAll(teamMembers);
				view.getFromList().setRowData(members);
				view.getToList().setRowData(others);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Something goes wrong!");
			}
		});
	}

	private void doOnAddBtnClicked() {
		Long selectedTeam = teamSelectionModel.getSelectedObject().getId();
		final Set<UserDO> otherMembers = othersSelectionModel.getSelectedSet();
		ManagerService.Util.getService().addUsersToTeam(convertUserIds(otherMembers), selectedTeam, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				members.addAll(otherMembers);
				others.removeAll(otherMembers);
				view.getFromList().setRowData(members);
				view.getToList().setRowData(others);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Something goes wrong!");
			}
		});
		
	}
	
	private ArrayList<String> convertUserIds(Set<UserDO> users){
		ArrayList<String> result = new ArrayList<String>();
		for(UserDO user : users){
			result.add(user.getUsername());
		}
		return result;
	}

	private void doOnSelectionChanged(){
		Long teamId = teamSelectionModel.getSelectedObject().getId();
		ManagerService.Util.getService().fetchTeamMembers(teamId, new AsyncCallback<ArrayList<UserDO>>() {
			
			@Override
			public void onSuccess(ArrayList<UserDO> result) {
				members = result;
				view.getFromList().setRowData(members);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Team members fetch failed!");
			}
		});
		ManagerService.Util.getService().fetchNotTeamMembers(teamId, new AsyncCallback<ArrayList<UserDO>>() {
			
			@Override
			public void onSuccess(ArrayList<UserDO> result) {
				others = result;
				view.getToList().setRowData(others);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Team members fetch failed!");
			}
		});
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}
	
}
