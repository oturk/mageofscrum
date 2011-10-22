package hu.ideaimpl.mageofscrum.client.service;

import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.TaskDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../ManagerService")
public interface ManagerService extends RemoteService {
	public static class Util{
		private static ManagerServiceAsync service = null;
		public static ManagerServiceAsync getService(){
			if(service == null){
				service = GWT.create(ManagerService.class);
			}
			return service;
		}
	}
	//Team operations
	public ArrayList<TeamDO> fetchTeams();
	public ArrayList<UserDO> fetchTeamMembers(Long teamId);
	public ArrayList<UserDO> fetchNotTeamMembers(Long teamId);
	public TeamDO addTeam(String teamName);
	public void deleteTeam(Long id);
	public void addUsersToTeam(ArrayList<String> userIds, Long teamId);
	public void removeUsersFromTeam(ArrayList<String> userIds, Long teamId);
	//Role operations
	public ArrayList<RoleDO> fetchUsersRoles(String userId);
	public ArrayList<RoleDO> fetchOtherRoles(String userId);
	public ArrayList<UserDO> fetchRoleMembers(Long roleId);
	public void addRolesToUser(String userId, ArrayList<Long> roleIds);
	public void removeRolesFromUser(String userId, ArrayList<Long> roleIds);
	public void addUsersToRole(ArrayList<Long> userIds, Long roleId);
	public void removeUsersFromRole(ArrayList<Long> userIds, Long roleId);
	//User operations
	public ArrayList<UserDO> fetchUsers();
	public UserDO addUser(UserDO user);
	public void deleteUsers(ArrayList<String> users);
	public UserDataDO fetchUserData();
	public void updateUserData(UserDataDO data);
	public void changePassword(String password);
	//Project operations
	public ArrayList<ProjectDO> fetchProjects();
	public ArrayList<UserDO> fetchOwners();
	public ProjectDO saveProject(ProjectDO project);
	public void deleteProject(Long id);
	public ArrayList<ProjectDO> listTeamsProjects();
	public ArrayList<TaskDO> fetchTasks(Long projectId);
	public TaskDO addTask(Long projectId, TaskDO task);
	public void deleteTask(Long taskId);
	//Sprint operations
	public boolean hasActiveSprint(Long projectId);
	public void startSprint(Long projectId);
	public void stopSprint(Long projectId);
	public void moveTaskToSprint(Long projectId, Long taskId);
	public ArrayList<TaskDO> fetchSprintTask(Long projectId);
	public TaskDO reportToTask(Long projectId, Long taskId, int time, Date date, String desc);
	public void removeTaskFromSprint(Long projectId, Long taskId);
}
