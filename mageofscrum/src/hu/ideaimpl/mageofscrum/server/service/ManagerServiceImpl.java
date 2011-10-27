package hu.ideaimpl.mageofscrum.server.service;

import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.server.dao.ProjectDAO;
import hu.ideaimpl.mageofscrum.server.dao.ProjectDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.RoleDAO;
import hu.ideaimpl.mageofscrum.server.dao.RoleDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.SprintDAO;
import hu.ideaimpl.mageofscrum.server.dao.SprintDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAO;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TeamDAO;
import hu.ideaimpl.mageofscrum.server.dao.TeamDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.UserDAO;
import hu.ideaimpl.mageofscrum.server.dao.UserDAOImpl;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Sprint;
import hu.ideaimpl.mageofscrum.server.entity.Task;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.SprintDO;
import hu.ideaimpl.mageofscrum.shared.TaskDO;
import hu.ideaimpl.mageofscrum.shared.TaskStatuses;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns="/ManagerService")
public class ManagerServiceImpl extends RemoteServiceServlet implements ManagerService {
	private static final long serialVersionUID = 1L;
	private static final TeamDAO teamDAO = new TeamDAOImpl();
	private static final UserDAO userDAO = new UserDAOImpl();
	private static final RoleDAO roleDAO = new RoleDAOImpl(); 
	private static final ProjectDAO projectDAO = new ProjectDAOImpl();
	private static final TaskDAO taskDAO = new TaskDAOImpl();
	private static final SprintDAO sprintDAO = new SprintDAOImpl();
	private final Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class); 

	@Override
	public ArrayList<TeamDO> fetchTeams() {
		ArrayList<Team> result = (ArrayList<Team>) teamDAO.listAll();
		
		ArrayList<TeamDO> teamDOs = new ArrayList<TeamDO>();
		for(Team team : result){
			teamDOs.add(team.getTeamDO());
		}
		return teamDOs;
	}

	@Override
	public ArrayList<RoleDO> fetchUsersRoles(String username) {
		User user = userDAO.findUser(username);
		
		ArrayList<RoleDO> result = new ArrayList<RoleDO>();
		if (user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				result.add(role.getRoleDO());
			}
		}
		return result;
	}
	
	@Override
	public ArrayList<RoleDO> fetchOtherRoles(String username) {
		ArrayList<Role> tmpResult = (ArrayList<Role>) roleDAO.listOtherRoles(userDAO.findUser(username).getId());
		ArrayList<RoleDO> result = new ArrayList<RoleDO>();
		for (Role role : tmpResult) {
			result.add(role.getRoleDO());
		}
//		ArrayList<Role> partResult = new ArrayList<Role>();
//		User user = userDAO.findUser(userId);
//		if (user.getRoles() != null) {
////			Criteria criteria = em.createCriteria(Role.class);
////			criteria.add(Restrictions.not(Restrictions.in("id", user.getRoleIds())));
//			partResult = (ArrayList<Role>) roleDAO.listInverse(user.getRoleIds());
//		}else{
//			partResult = (ArrayList<Role>) roleDAO.listAll();
//		}
		
		return result;
	}

	@Override
	public ArrayList<UserDO> fetchTeamMembers(Long teamId) {
		Team team = (Team) teamDAO.findTeam(teamId);
		return (ArrayList<UserDO>) team.getUserDOs();
	}
	
	@Override
	public ArrayList<UserDO> fetchNotTeamMembers(Long teamId) {
		ArrayList<User> tmpResult = (ArrayList<User>) teamDAO.listNotTeamMembers(teamId);
		ArrayList<UserDO> result = new ArrayList<UserDO>();
		if (tmpResult != null) {
			for (User u : tmpResult) {
				result.add(u.getUserDO());
			}
		}
		return result;
	}

	@Override
	public TeamDO addTeam(String teamName) {
		teamDAO.saveTeam(teamName);
		Team team = teamDAO.findTeam(teamName);
		return team.getTeamDO();
	}
	
	@Override
	public void deleteTeam(Long id) {
		teamDAO.deleteTeam(id);
	}

//	@Override
//	public void addUsersToTeam(ArrayList<String> userIds, Long teamId) {
//		Team team = teamDAO.findTeam(teamId);
//		for(String username : userIds){
//			User user = userDAO.findUser(username);
//			team.addMember(user);
//			user.addTeam(team);
//			userDAO.updateUser(user);
//		}
//	}
	
	@Override
	public void addUsersToTeam(ArrayList<String> usernames, Long teamId) {
		for(String username : usernames){
			teamDAO.addUser(teamId, username);
		}
	}

	@Override
	public void removeUsersFromTeam(ArrayList<String> userIds, Long teamId) {
		for(String username : userIds){
			teamDAO.removeUser(teamId, username);
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
		User existUser = userDAO.findUser(user.getUsername());
		if(existUser != null){
			return null;
		}
		userDAO.saveUser(user.getUsername(), user.getPassword());
		return userDAO.findUser(user.getUsername()).getUserDO();
	}

	@Override
	public void deleteUsers(ArrayList<String> users) {
		for(String username : users){
			userDAO.deleteUser(username);
		}
	}

	@Override
	public ArrayList<UserDO> fetchUsers() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		ArrayList<User> result = (ArrayList<User>) userDAO.listAll(username);
		
		ArrayList<UserDO> userDOs = new ArrayList<UserDO>();
		for(User user : result){
			userDOs.add(user.getUserDO());
		}
		return userDOs;
	}

	@Override
	public void addRolesToUser(String username, ArrayList<Long> roleIds) {
		User user = userDAO.findUser(username);
		for(Long roleId : roleIds){
			userDAO.addRole(user.getId(), roleId);
		}
	}

	@Override
	public void removeRolesFromUser(String username, ArrayList<Long> roleIds) {
		User user = userDAO.findUser(username);
		for(Long roleId : roleIds){
			userDAO.removeRole(user.getId(), roleId);
		}
	}

	@Override
	public UserDataDO fetchUserData() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = userDAO.findUser(username);
		
		return user.getUserDataDO();
	}

	@Override
	public void updateUserData(UserDataDO data) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
//		User user = userDAO.findUser(username);
//		UserData userData = user.getData();
//		if(userData == null){
//			userData = new UserData();
//		}
//		userData.setEmail(data.getEmail());
//		userData.setSurname(data.getSurname());
//		userData.setForename(data.getForename());
//		user.setData(userData);
		
		userDAO.updateUserData(userDAO.findUser(username).getId(),data);
	}

	@Override
	public void changePassword(String password) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = userDAO.findUser(username);
		userDAO.changePassword(user.getId(), password);
	}

	@Override
	public ArrayList<ProjectDO> fetchProjects() {
		ArrayList<ProjectDO> result = new ArrayList<ProjectDO>();
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		List<Project> projects = null;
		log.info("hasAdminRole: "+SecurityUtils.getSubject().hasRole("admin"));
		if(SecurityUtils.getSubject().hasRole("admin")){
			projects = projectDAO.listAll();
		}else{
			projects = projectDAO.listUsersProjects(username);
		}
		
		for(Project project : projects){
			result.add(project.getProjectDO());
		}
		return result;
	}

	@Override
	public ArrayList<UserDO> fetchOwners() {
		ArrayList<UserDO> result = new ArrayList<UserDO>();
		ArrayList<User> partResult = (ArrayList<User>) roleDAO.listOwners();
		
		for(User user : partResult){
			result.add(user.getUserDO());
		}
		return result;
	}

	@Override
	public ProjectDO saveProject(ProjectDO data) {
		log.info("isExisting: "+(data.getId() != null ? data.getId() : "N/A"));
		Team team = teamDAO.findTeam(data.getTeamName());
		User user = userDAO.findUser(data.getOwnerName());
		
		if(data.getId() != null){
			projectDAO.updateProject(data.getId(), user.getId(), team.getId(), data.getName(), data.getDescription());
		}else{
			projectDAO.saveProject(data.getName(), data.getDescription());
			Project project = projectDAO.findProject(data.getName());
			log.info("new project added: "+project.getId());
			
			projectDAO.addOwner(data.getOwnerName(), project.getId());
			projectDAO.addTeam(team.getId(), project.getId());
		}
		Project project = projectDAO.findProject(data.getName());
		log.info("projectTeam: "+project.getTeam().getName());
		log.info("projectOwner: "+project.getUser().getUsername());
		
		return project.getProjectDO();
	}

	@Override
	public void deleteProject(Long id) {
		projectDAO.deleteProject(id);
	}

	@Override
	public ArrayList<ProjectDO> listTeamsProjects() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = userDAO.findUser(username);
		ArrayList<ProjectDO> result = new ArrayList<ProjectDO>();
		for(Project p : userDAO.listUsersProjects(user.getId())){
			result.add(p.getProjectDO());
		}
		return result;
	}

	@Override
	public ArrayList<TaskDO> fetchTasks(Long projectId) {
		Project proj = projectDAO.findProject(projectId);
		ArrayList<TaskDO> result = new ArrayList<TaskDO>();
		for(Task t : proj.getTasks()){
			if(t.getStatus().getStatus().equals(TaskStatuses.BACKLOG.name()) || t.getStatus().getStatus().equals(TaskStatuses.MOVEDBACK.name())){
				result.add(t.getTaskDO());
			}
		}
		return result;
	}

	@Override
	public TaskDO addTask(Long projectId, TaskDO task) {
		log.info("addTask: "+task.getId());
		if(task.getId() != null){
			taskDAO.updateTask(task.getId(), task.getName(), task.getEstimateTime(), task.getPriority(), task.getDescription());
		}else{
			taskDAO.saveTask(projectId, task.getName(), task.getDescription(), task.getEstimateTime(), task.getPriority());
		}
		Task taskk = taskDAO.findTask(task.getName());
		log.info("newTask: "+taskk.getName());
		return taskDAO.findTask(task.getName()).getTaskDO();
	}

	@Override
	public void deleteTask(Long taskId) {
		taskDAO.deleteTask(taskId);
	}

	@Override
	public boolean hasActiveSprint(Long projectId) {
		return sprintDAO.hasActiveSprint(projectId);
	}

	@Override
	public void startSprint(Long projectId) {
		sprintDAO.startSprint(projectId);
	}

	@Override
	public void stopSprint(Long projectId) {
		sprintDAO.stopSprint(projectId);
	}

	@Override
	public void moveTaskToSprint(Long projectId, Long taskId) {
		sprintDAO.addTask(projectId, taskId);
	}

	@Override
	public ArrayList<TaskDO> fetchSprintTask(Long projectId) {
		Project proj = projectDAO.findProject(projectId);
		ArrayList<TaskDO> result = new ArrayList<TaskDO>();
		for(Task t : proj.getTasks()){
			if(!t.getStatus().getStatus().equals(TaskStatuses.BACKLOG.name()) && !t.getStatus().getStatus().equals(TaskStatuses.MOVEDBACK.name())){
				result.add(t.getTaskDO());
			}
		}
		return result;
	}

	@Override
	public TaskDO reportToTask(Long projectId, Long taskId, int time, Date date, String desc) {
		sprintDAO.reportTime(projectId, taskId, time, date);
		return taskDAO.findTask(taskId).getTaskDO();
	}

	@Override
	public void removeTaskFromSprint(Long projectId, Long taskId) {
		sprintDAO.removeTask(projectId, taskId);
	}

	@Override
	public ArrayList<SprintDO> fetchSprints(Long projectId) {
		List<Sprint> result = sprintDAO.fetchSprints(projectId);
		ArrayList<SprintDO> sprints = new ArrayList<SprintDO>();
		if(result != null){
			for(Sprint s : result){
				sprints.add(s.getSprintDO());
			}
		}
		return sprints;
	}

	@Override
	public Map<String, Integer> getSprintHistory(Long sprintId) {
		return sprintDAO.getHistory(sprintId);
	}

	@Override
	public void changeUserPassword(Long userId, String password) {
		userDAO.changePassword(userId, password);
	}
	
}
