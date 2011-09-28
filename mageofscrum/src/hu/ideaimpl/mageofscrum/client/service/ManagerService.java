package hu.ideaimpl.mageofscrum.client.service;

import hu.ideaimpl.mageofscrum.server.entity.UserData;
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
	public void addTeam(String teamName);
	public void addUsersToTeam(ArrayList<Long> userIds, Long teamId);
	public void removeUsersFromTeam(ArrayList<Long> userIds, Long teamId);
	//Role operations
	public ArrayList<RoleDO> fetchRoles();
	public ArrayList<UserDO> fetchRoleMembers(Long roleId);
	public void addUsersToRole(ArrayList<Long> userIds, Long roleId);
	public void removeUsersFromRole(ArrayList<Long> userIds, Long roleId);
	//User operations
	public void addUser(UserDO user);
	public void deleteUsers(ArrayList<Long> users);
	
}
