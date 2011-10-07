package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
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
import com.google.gwt.view.client.SelectionChangeEvent;

public class TeamActivity extends AbstractActivity {
	private final ClientFactory clientFactory;
	private TeamsView teamsView;
	private ArrayList<UserDO> members = null;
	private ArrayList<UserDO> others = null;
	private ArrayList<TeamDO> teams = new ArrayList<TeamDO>();
	
	public TeamActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.teamsView = clientFactory.getTeamsView();
		initTeams();
		bind();
	}
	
	private void initTeams() {
		ManagerService.Util.getService().fetchTeams(new AsyncCallback<ArrayList<TeamDO>>() {
			
			@Override
			public void onSuccess(ArrayList<TeamDO> result) {
				teams = result;
				teamsView.setTeams(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Team data fetch failed!");
			}
		});
	}

	private void bind(){
		teamsView.getTeamSelection().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				doOnSelectionChanged();
			}
		});
		teamsView.getAddBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnAddBtnClicked();
			}
		});
		teamsView.getRemoveBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnRemoveBtnClicked();
			}
		});
		teamsView.getCreateBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnCreateBtnClicked();
			}
		});
		teamsView.getDeleteBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnDeleteBtnClicked();
			}
		});
	}
	
	private void doOnDeleteBtnClicked() {
		final TeamDO selectedTeam = teamsView.getSelectedTeam();
		if (selectedTeam != null && teams.contains(selectedTeam)) {
			ManagerService.Util.getService().deleteTeam(teamsView.getSelectedObject(), new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					teams.remove(selectedTeam);
					teamsView.setTeams(teams);
					teamsView.clearListToList();
				}

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("deletion failed");
				}
			});
		}
	}

	private void doOnCreateBtnClicked() {
		String value = teamsView.getTeamName().getValue();
		if (value != null && !value.isEmpty()) {
			ManagerService.Util.getService().addTeam(value, new AsyncCallback<TeamDO>() {

				@Override
				public void onSuccess(TeamDO result) {
					teams.add(result);
					teamsView.setTeams(teams);
					teamsView.getTeamName().setValue("");
				}

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("failed");
				}
			});
		}
	}

	private void doOnRemoveBtnClicked() {
		Long selectedTeam = teamsView.getSelectedObject();
		Set<UserDO> teamMembers = teamsView.getSelectedMembers();
		members.removeAll(teamMembers);
		others.addAll(teamMembers);
		teamsView.setTeamMembers(members);
		teamsView.setOtherUsers(others);
		ManagerService.Util.getService().removeUsersFromTeam(convertUserIds(teamMembers), selectedTeam, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				System.out.println("success");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Something goes wrong!");
			}
		});
	}

	private void doOnAddBtnClicked() {
		Long selectedTeam = teamsView.getSelectedObject();
		Set<UserDO> otherMembers = teamsView.getSelectedOthers();
		members.addAll(otherMembers);
		others.removeAll(otherMembers);
		teamsView.setTeamMembers(members);
		teamsView.setOtherUsers(others);
		ManagerService.Util.getService().addUsersToTeam(convertUserIds(otherMembers), selectedTeam, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				System.out.println("success");
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
			result.add(user.getEmail());
		}
		return result;
	}

	private void doOnSelectionChanged(){
		Long selectedObject = teamsView.getSelectedObject();
		ManagerService.Util.getService().fetchTeamMembers(selectedObject, new AsyncCallback<ArrayList<UserDO>>() {
			
			@Override
			public void onSuccess(ArrayList<UserDO> result) {
				members = result;
				teamsView.setTeamMembers(members);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Team members fetch failed!");
			}
		});
		ManagerService.Util.getService().fetchNotTeamMembers(selectedObject, new AsyncCallback<ArrayList<UserDO>>() {
			
			@Override
			public void onSuccess(ArrayList<UserDO> result) {
				others = result;
				teamsView.setOtherUsers(others);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Team members fetch failed!");
			}
		});
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(teamsView);
	}
	
}
