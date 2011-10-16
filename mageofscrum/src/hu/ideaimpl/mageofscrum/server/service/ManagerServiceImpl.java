package hu.ideaimpl.mageofscrum.server.service;

import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.server.dbo.ProjectDAO;
import hu.ideaimpl.mageofscrum.server.dbo.ProjectDAOImpl;
import hu.ideaimpl.mageofscrum.server.dbo.RoleDAO;
import hu.ideaimpl.mageofscrum.server.dbo.RoleDAOImpl;
import hu.ideaimpl.mageofscrum.server.dbo.TeamDAO;
import hu.ideaimpl.mageofscrum.server.dbo.TeamDAOImpl;
import hu.ideaimpl.mageofscrum.server.dbo.UserDAO;
import hu.ideaimpl.mageofscrum.server.dbo.UserDAOImpl;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.server.entity.UserData;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns="/ManagerService")
public class ManagerServiceImpl extends RemoteServiceServlet implements ManagerService {
	private static final long serialVersionUID = 1L;
	private static final TeamDAO teamDBO = new TeamDAOImpl();
	private static final UserDAO userDBO = new UserDAOImpl();
	private static final RoleDAO roleDBO = new RoleDAOImpl(); 
	private static final ProjectDAO projectDBO = new ProjectDAOImpl();

	@Override
	public ArrayList<TeamDO> fetchTeams() {
		ArrayList<Team> result = (ArrayList<Team>) teamDBO.listAll();
		
		ArrayList<TeamDO> teamDOs = new ArrayList<TeamDO>();
		for(Team team : result){
			teamDOs.add(team.getTeamDO());
		}
		return teamDOs;
	}

	@Override
	public ArrayList<RoleDO> fetchUsersRoles(String username) {
		User user = userDBO.findUser(username);
		
		ArrayList<RoleDO> result = new ArrayList<RoleDO>();
		if (user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				result.add(role.getRoleDO());
			}
		}
		return result;
	}
	
	@Override
	public ArrayList<RoleDO> fetchOtherRoles(String userId) {
		ArrayList<RoleDO> result = new ArrayList<RoleDO>();
		ArrayList<Role> partResult = new ArrayList<Role>();
		User user = userDBO.findUser(userId);
		if (user.getRoles() != null) {
//			Criteria criteria = em.createCriteria(Role.class);
//			criteria.add(Restrictions.not(Restrictions.in("id", user.getRoleIds())));
			partResult = (ArrayList<Role>) roleDBO.listInverse(user.getRoleIds());
		}else{
			partResult = (ArrayList<Role>) roleDBO.listAll();
		}
		for (Role role : partResult) {
			result.add(role.getRoleDO());
		}
		
		return result;
	}

	@Override
	public ArrayList<UserDO> fetchTeamMembers(Long teamId) {
		Team team = (Team) teamDBO.findTeam(teamId);
		return (ArrayList<UserDO>) team.getUserDOs();
	}
	
	@Override
	public ArrayList<UserDO> fetchNotTeamMembers(Long teamId) {
		ArrayList<UserDO> result = new ArrayList<UserDO>();
//		Team team = (Team) em.get(Team.class, teamId);
//		List<User> partResult = null;
//		
//		if (team.getMembersIds().size() > 0) {
//			Criteria criteria = em.createCriteria(User.class);
//			criteria.add(Restrictions.not(Restrictions.in("email", team.getMembersIds())));
//			partResult = criteria.list();
//		}else{
//			Query query = em.createQuery("from User");
//			partResult = query.list();
//		}
//		if (partResult != null) {
//			for (User user : partResult) {
//				result.add(user.getUserDO());
//			}
//		}
		return result;
	}

	@Override
	public TeamDO addTeam(String teamName) {
		teamDBO.saveTeam(teamName);
		Team team = teamDBO.findTeam(teamName);
		return team.getTeamDO();
	}
	
	@Override
	public void deleteTeam(Long id) {
		teamDBO.deleteTeam(id);
	}

	@Override
	public void addUsersToTeam(ArrayList<String> userIds, Long teamId) {
		Team team = teamDBO.findTeam(teamId);
		for(String username : userIds){
			User user = userDBO.findUser(username);
			team.addMember(user);
			user.addTeam(team);
			userDBO.updateUser(user);
		}
	}

	@Override
	public void removeUsersFromTeam(ArrayList<String> userIds, Long teamId) {
		Team team = teamDBO.findTeam(teamId);
		for(String username : userIds){
			User user = userDBO.findUser(username);
			team.removeMember(user);
			user.removeTeam(team);
			userDBO.updateUser(user);
		}
	}

	@Override
	public ArrayList<UserDO> fetchRoleMembers(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUsersToRole(ArrayList<Long> userIds, Long roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUsersFromRole(ArrayList<Long> userIds, Long roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDO addUser(UserDO user) {
		User existUser = userDBO.findUser(user.getEmail());
		if(existUser != null){
			return null;
		}
		userDBO.saveUser(user.getEmail(), user.getPassword());
		return userDBO.findUser(user.getEmail()).getUserDO();
	}

	@Override
	public void deleteUsers(ArrayList<String> users) {
		for(String username : users){
			userDBO.deleteUser(username);
		}
	}

	@Override
	public ArrayList<UserDO> fetchUsers() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		ArrayList<User> result = (ArrayList<User>) userDBO.listAll(username);
		
		ArrayList<UserDO> userDOs = new ArrayList<UserDO>();
		for(User user : result){
			userDOs.add(user.getUserDO());
		}
		return userDOs;
	}

	@Override
	public void addRolesToUser(String username, ArrayList<Long> roleIds) {
		User user = userDBO.findUser(username);
		for(Long roleId : roleIds){
			Role role = roleDBO.findRole(roleId);
			role.addUser(user);
			user.addRole(role);
			userDBO.updateUser(user);
		}
	}

	@Override
	public void removeRolesFromUser(String username, ArrayList<Long> roleIds) {
		User user = userDBO.findUser(username);
		for(Long roleId : roleIds){
			Role role = roleDBO.findRole(roleId);
			role.getUsers().remove(user);
			user.getRoles().remove(role);
			userDBO.updateUser(user);
		}
	}

	@Override
	public UserDataDO fetchUserData() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = userDBO.findUser(username);
		
		return user.getUserDataDO();
	}

	@Override
	public void updateUserData(UserDataDO data) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = userDBO.findUser(username);
		UserData userData = user.getData();
		if(userData == null){
			userData = new UserData();
		}
		userData.setEmail(data.getEmail());
		userData.setSurname(data.getSurname());
		userData.setForename(data.getForename());
		user.setData(userData);
		
		userDBO.updateUser(user);
	}

	@Override
	public void changePassword(String password) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = userDBO.findUser(username);
		Sha256Hash hash = new Sha256Hash(password);
		user.setPassword(hash.toString());
		userDBO.updateUser(user);
	}

	@Override
	public ArrayList<ProjectDO> fetchProjects() {
		ArrayList<ProjectDO> result = new ArrayList<ProjectDO>();
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		List<Project> projects = null;
		if(SecurityUtils.getSubject().hasRole("ADMIN")){
			projects = projectDBO.listAll();
		}else{
			projects = projectDBO.listUsersProjects(username);
		}
		
		for(Project project : projects){
			result.add(project.getProjectDO());
		}
		return result;
	}

	@Override
	public ArrayList<UserDO> fetchOwners() {
		ArrayList<UserDO> result = new ArrayList<UserDO>();
//		ArrayList<User> partResult = new ArrayList<User>();
//		List<Role> roles = em.createQuery("from Role r where r.name in ('ADMIN', 'OWNER')").list();
//		
//		for(Role role : roles){
//			for(User user : role.getUsers()){
//				if (!partResult.contains(user)) {
//					partResult.add(user);
//				}
//			}
//		}
//		for(User user : partResult){
//			result.add(user.getUserDO());
//		}
		return result;
	}

	@Override
	public ProjectDO saveProject(ProjectDO data) {
		projectDBO.saveProject(data.getName(), data.getDescription());
		
		Project project = projectDBO.findProject(data.getName());
		Team team = teamDBO.findTeam(data.getTeamName());
		User owner = userDBO.findUser(data.getOwnerName());

		project.setTeam(team);
		team.addProject(project);
		project.setUser(owner);
		owner.addProject(project);
		userDBO.updateUser(owner);

		return project.getProjectDO();
	}

}
