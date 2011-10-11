package hu.ideaimpl.mageofscrum.server.service;

import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.server.HibernateUtil;
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
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns="/ManagerService")
public class ManagerServiceImpl extends RemoteServiceServlet implements ManagerService {
	private static final long serialVersionUID = 1L;
	private Session session = HibernateUtil.getSession();

	@Override
	public ArrayList<TeamDO> fetchTeams() {
		ArrayList<Team> result = new ArrayList<Team>();
		Query query = session.createQuery("from Team");
		result = (ArrayList<Team>) query.list();
		
		ArrayList<TeamDO> teamDOs = new ArrayList<TeamDO>();
		for(Team team : result){
			teamDOs.add(team.getTeamDO());
		}
		return teamDOs;
	}

	@Override
	public ArrayList<RoleDO> fetchUsersRoles(String userId) {
		User user = (User) session.get(User.class, userId);
		
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
		User user = (User) session.get(User.class, userId);
		if (user.getRoles() != null) {
			Criteria criteria = session.createCriteria(Role.class);
			criteria.add(Restrictions.not(Restrictions.in("id", user.getRoleIds())));
			partResult = (ArrayList<Role>) criteria.list();
		}else{
			partResult = (ArrayList<Role>) session.createQuery("from Role").list();
		}
		for (Role role : partResult) {
			result.add(role.getRoleDO());
		}
		
		return result;
	}

	@Override
	public ArrayList<UserDO> fetchTeamMembers(Long teamId) {
		Team team = (Team) session.get(Team.class, teamId);
		return (ArrayList<UserDO>) team.getUserDOs();
	}
	
	@Override
	public ArrayList<UserDO> fetchNotTeamMembers(Long teamId) {
		ArrayList<UserDO> result = new ArrayList<UserDO>();
		Team team = (Team) session.get(Team.class, teamId);
		List<User> partResult = null;
		
		if (team.getMembersIds().size() > 0) {
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.not(Restrictions.in("email", team.getMembersIds())));
			partResult = criteria.list();
		}else{
			Query query = session.createQuery("from User");
			partResult = query.list();
		}
		if (partResult != null) {
			for (User user : partResult) {
				result.add(user.getUserDO());
			}
		}
		return result;
	}

	@Override
	public TeamDO addTeam(String teamName) {
		Team newTeam = new Team(teamName);
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.save(newTeam);
		tx.commit();
		return newTeam.getTeamDO();
	}
	
	@Override
	public void deleteTeam(Long id) {
		Team team = (Team) session.get(Team.class, id);
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.delete(team);
		tx.commit();
	}

	@Override
	public void addUsersToTeam(ArrayList<String> userIds, Long teamId) {
		Team team = (Team) session.get(Team.class, teamId);
		for(String id : userIds){
			User user = (User) session.get(User.class, id);
			team.addMember(user);
		}
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.save(team);
		tx.commit();
		
	}

	@Override
	public void removeUsersFromTeam(ArrayList<String> userIds, Long teamId) {
		Team team = (Team) session.get(Team.class, teamId);
		for(String id : userIds){
			User user = (User) session.get(User.class, id);
			team.removeMember(user);
		}
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.save(team);
		tx.commit();
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
		User existUser = (User) session.get(User.class, user.getEmail());
		if(existUser != null){
			return null;
		}
		User newUser = User.createUserObj(user);
		Sha256Hash hashedPass = new Sha256Hash(newUser.getPassword());
		System.out.println("newPass: "+hashedPass.toString());
		newUser.setPassword(hashedPass.toString());
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.save(newUser);
		tx.commit();
		return newUser.getUserDO();
	}

	@Override
	public void deleteUsers(ArrayList<String> users) {
		Transaction tx = session.beginTransaction();
		for(String id : users){
			User user = (User) session.get(User.class, id);
			if (user.getRoles() != null) {
				user.getRoles().clear();
				tx.begin();
				session.update(user);
				tx.commit();
			}
			tx.begin();
			session.delete(user);
			tx.commit();
		}
	}

	@Override
	public ArrayList<UserDO> fetchUsers() {
		ArrayList<User> result = new ArrayList<User>();
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.not(Restrictions.idEq(username)));
		result = (ArrayList<User>) criteria.list();
		
//		Query query = session.createQuery("from User");
//		result = (ArrayList<User>) query.list();
		
		ArrayList<UserDO> userDOs = new ArrayList<UserDO>();
		for(User user : result){
			userDOs.add(user.getUserDO());
		}
		return userDOs;
	}

	@Override
	public void addRolesToUser(String userId, ArrayList<Long> roleIds) {
		User user = (User) session.get(User.class, userId);
		for(Long roleId : roleIds){
			Role role = (Role) session.get(Role.class, roleId);
			user.addRole(role);
		}
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.update(user);
		tx.commit();
		
	}

	@Override
	public void removeRolesFromUser(String userId, ArrayList<Long> roleIds) {
		User user = (User) session.get(User.class, userId);
		for(Long roleId : roleIds){
			Role role = (Role) session.get(Role.class, roleId);
			user.getRoles().remove(role);
		}
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.update(user);
		tx.commit();
	}

	@Override
	public UserDataDO fetchUserData() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = (User) session.get(User.class, username);
		
		return user.getUserDataDO();
	}

	@Override
	public void updateUserData(UserDataDO data) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = (User) session.get(User.class, username);
		UserData userData = user.getData();
		if(userData == null){
			userData = new UserData();
		}
		userData.setEmail(data.getEmail());
		userData.setSurname(data.getSurname());
		userData.setForename(data.getForename());
		user.setData(userData);
		
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.update(user);
		tx.commit();
	}

	@Override
	public void changePassword(String password) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = (User) session.get(User.class, username);
		Sha256Hash hash = new Sha256Hash(password);
		user.setPassword(hash.toString());
		
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.update(user);
		tx.commit();
	}

	@Override
	public ArrayList<ProjectDO> fetchProjects() {
		ArrayList<ProjectDO> result = new ArrayList<ProjectDO>();
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = (User) session.get(User.class, username);
		Query query = null;
		if(SecurityUtils.getSubject().hasRole("ADMIN")){
			query = session.createQuery("from Project");
		}else{
			query = session.createQuery("from Project p where p.user = :user");
			query.setParameter("user", user);
		}
		List<Project> projects = query.list();
		
		for(Project project : projects){
			result.add(project.getProjectDO());
		}
		return result;
	}

	@Override
	public ArrayList<UserDO> fetchOwners() {
		ArrayList<UserDO> result = new ArrayList<UserDO>();
		ArrayList<User> partResult = new ArrayList<User>();
		List<Role> roles = session.createQuery("from Role r where r.name in ('ADMIN', 'OWNER')").list();
		
		for(Role role : roles){
			for(User user : role.getUsers()){
				if (!partResult.contains(user)) {
					partResult.add(user);
				}
			}
		}
		for(User user : partResult){
			result.add(user.getUserDO());
		}
		return result;
	}

	@Override
	public ProjectDO saveProject(ProjectDO data) {
		Project project = Project.convertToProjectObj(data);
		List<Team> team = session.createQuery("from Team t where t.name like :name")
				.setParameter("name", data.getTeamName()).list();
		List<User> owner = session.createQuery("from User u where u.userName like :name")
				.setParameter("name", data.getOwnerName()).list();
//		project.setTeam(team.get(0));
//		project.setUser(owner.get(0));
		
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		if (project.getId() != null) {
			session.update(project);
		}else{
			project.setTeam(team.get(0));
//			project.setUser(owner.get(0));
			session.save(project);
			owner.get(0).addProject(project);
			session.update(owner);
			
			session.update(project);
		}
		tx.commit();
		
		return project.getProjectDO();
	}

}
