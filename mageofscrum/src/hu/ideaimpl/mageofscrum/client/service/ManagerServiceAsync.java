package hu.ideaimpl.mageofscrum.client.service;

import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.TaskDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ManagerServiceAsync {

	void fetchTeams(AsyncCallback<ArrayList<TeamDO>> callback);

	void fetchTeamMembers(Long teamId, AsyncCallback<ArrayList<UserDO>> callback);

	void fetchRoleMembers(Long roleId, AsyncCallback<ArrayList<UserDO>> callback);

	void addUsersToTeam(ArrayList<String> userIds, Long teamId, AsyncCallback<Void> callback);

	void addUsersToRole(ArrayList<Long> userIds, Long teamId, AsyncCallback<Void> callback);

	void removeUsersFromTeam(ArrayList<String> userIds, Long teamId, AsyncCallback<Void> callback);

	void removeUsersFromRole(ArrayList<Long> userIds, Long roleId, AsyncCallback<Void> callback);

	void addUser(UserDO user, AsyncCallback<UserDO> callback);

	void deleteUsers(ArrayList<String> users, AsyncCallback<Void> callback);

	void addTeam(String teamName, AsyncCallback<TeamDO> callback);

	void fetchNotTeamMembers(Long teamId, AsyncCallback<ArrayList<UserDO>> callback);

	void deleteTeam(Long id, AsyncCallback<Void> callback);

	void fetchUsers(AsyncCallback<ArrayList<UserDO>> callback);

	void fetchUsersRoles(String userId, AsyncCallback<ArrayList<RoleDO>> callback);

	void fetchOtherRoles(String userId, AsyncCallback<ArrayList<RoleDO>> callback);

	void addRolesToUser(String userId, ArrayList<Long> roleIds, AsyncCallback<Void> callback);

	void removeRolesFromUser(String userId, ArrayList<Long> roleIds, AsyncCallback<Void> callback);

	void fetchUserData(AsyncCallback<UserDataDO> callback);

	void updateUserData(UserDataDO data, AsyncCallback<Void> callback);

	void changePassword(String password, AsyncCallback<Void> callback);

	void fetchProjects(AsyncCallback<ArrayList<ProjectDO>> callback);

	void fetchOwners(AsyncCallback<ArrayList<UserDO>> callback);

	void saveProject(ProjectDO project, AsyncCallback<ProjectDO> callback);

	void deleteProject(Long id, AsyncCallback<Void> callback);

	void listTeamsProjects(AsyncCallback<ArrayList<ProjectDO>> callback);

	void fetchTasks(Long projectId, AsyncCallback<ArrayList<TaskDO>> callback);

	void addTask(Long projectId, TaskDO task, AsyncCallback<TaskDO> callback);

	void deleteTask(Long taskId, AsyncCallback<Void> callback);

	void hasActiveSprint(Long projectId, AsyncCallback<Boolean> callback);

	void startSprint(Long projectId, AsyncCallback<Void> callback);

	void stopSprint(Long projectId, AsyncCallback<Void> callback);

	void moveTaskToSprint(Long projectId, Long taskId, AsyncCallback<Void> callback);

	void fetchSprintTask(Long projectId, AsyncCallback<ArrayList<TaskDO>> callback);

	void reportToTask(Long projectId, Long taskId, int time, Date date, String desc, AsyncCallback<TaskDO> callback);

	void removeTaskFromSprint(Long projectId, Long taskId, AsyncCallback<Void> callback);
	
}
