package hu.ideaimpl.mageofscrum.server.service;

import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

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
		for(Role role : user.getRoles()){
			result.add(role.getRoleDO());
		}
		return result;
	}
	
	@Override
	public ArrayList<RoleDO> fetchOtherRoles(String userId) {
		ArrayList<RoleDO> result = new ArrayList<RoleDO>();
		ArrayList<Role> partResult = new ArrayList<Role>();
		User user = (User) session.get(User.class, userId);

		Criteria criteria = session.createCriteria(Role.class);
		criteria.add(Restrictions.not(Restrictions.in("id", user.getRoleIds())));
		partResult = (ArrayList<Role>) criteria.list();

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
		User newUser = User.createUserObj(user);
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.save(newUser);
		tx.commit();
		return newUser.getUserDO();
	}

	@Override
	public void deleteUsers(ArrayList<String> users) {
		Transaction tx = session.beginTransaction();
		tx.begin();
		for(String id : users){
			User user = (User) session.get(User.class, id);
			session.delete(user);
		}
		tx.commit();
	}

	@Override
	public ArrayList<UserDO> fetchUsers() {
		ArrayList<User> result = new ArrayList<User>();
		Query query = session.createQuery("from User");
		result = (ArrayList<User>) query.list();
		
		ArrayList<UserDO> userDOs = new ArrayList<UserDO>();
		for(User user : result){
			userDOs.add(user.getUserDO());
		}
		return userDOs;
	}

}
