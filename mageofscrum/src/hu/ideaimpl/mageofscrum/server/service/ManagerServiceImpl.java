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

import javax.servlet.annotation.WebServlet;

import org.hibernate.Query;
import org.hibernate.Session;

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
	public ArrayList<RoleDO> fetchRoles() {
		ArrayList<Role> result = new ArrayList<Role>();
		Query query = session.createQuery("from Role");
		result = (ArrayList<Role>) query.list();
		
		ArrayList<RoleDO> roleDOs = new ArrayList<RoleDO>();
		for(Role role : result){
			roleDOs.add(role.getRoleDO());
		}
		return roleDOs;
	}

	@Override
	public ArrayList<UserDO> fetchTeamMembers(Long teamId) {
		Team team = (Team) session.get(Team.class, teamId);
		return (ArrayList<UserDO>) team.getUserDOs();
	}

	@Override
	public void addTeam(String teamName) {
		Team newTeam = new Team(teamName);
		session.save(newTeam);
	}

	@Override
	public void addUsersToTeam(ArrayList<Long> userIds, Long teamId) {
		Team team = (Team) session.get(Team.class, teamId);
		for(Long id : userIds){
			User user = (User) session.get(User.class, id);
			team.addMember(user);
		}
		session.persist(team);
	}

	@Override
	public void removeUsersFromTeam(ArrayList<Long> userIds, Long teamId) {
		Team team = (Team) session.get(Team.class, teamId);
		for(Long id : userIds){
			User user = (User) session.get(User.class, id);
			team.removeMember(user);
		}
		session.persist(team);
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
	public void addUser(UserDO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUsers(ArrayList<Long> users) {
		// TODO Auto-generated method stub
		
	}

}
