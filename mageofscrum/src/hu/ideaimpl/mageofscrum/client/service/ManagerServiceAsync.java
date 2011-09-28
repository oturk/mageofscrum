package hu.ideaimpl.mageofscrum.client.service;

import hu.ideaimpl.mageofscrum.server.entity.UserData;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ManagerServiceAsync {

	void fetchTeams(AsyncCallback<ArrayList<TeamDO>> callback);

	void fetchRoles(AsyncCallback<ArrayList<RoleDO>> callback);

	void fetchTeamMembers(Long teamId, AsyncCallback<ArrayList<UserDO>> callback);

	void fetchRoleMembers(Long roleId, AsyncCallback<ArrayList<UserDO>> callback);

	void addUsersToTeam(ArrayList<Long> userIds, Long teamId, AsyncCallback<Void> callback);

	void addUsersToRole(ArrayList<Long> userIds, Long teamId, AsyncCallback<Void> callback);

	void removeUsersFromTeam(ArrayList<Long> userIds, Long teamId, AsyncCallback<Void> callback);

	void removeUsersFromRole(ArrayList<Long> userIds, Long roleId, AsyncCallback<Void> callback);

	void addUser(UserDO user, AsyncCallback<Void> callback);

	void deleteUsers(ArrayList<Long> users, AsyncCallback<Void> callback);

	void addTeam(String teamName, AsyncCallback<Void> callback);
	
}
