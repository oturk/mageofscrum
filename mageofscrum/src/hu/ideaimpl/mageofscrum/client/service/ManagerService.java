package hu.ideaimpl.mageofscrum.client.service;

import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;

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
	public void addUsersToRole(ArrayList<Long> userIds, Long roleId);
	public void removeUsersFromRole(ArrayList<Long> userIds, Long roleId);
	//User operations
	public ArrayList<UserDO> fetchUsers();
	public UserDO addUser(UserDO user);
	public void deleteUsers(ArrayList<String> users);
	
}
