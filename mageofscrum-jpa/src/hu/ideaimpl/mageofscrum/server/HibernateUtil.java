package hu.ideaimpl.mageofscrum.server;

import hu.ideaimpl.mageofscrum.server.dbo.ProjectDAOImpl;
import hu.ideaimpl.mageofscrum.server.dbo.RoleDAOImpl;
import hu.ideaimpl.mageofscrum.server.dbo.TeamDAOImpl;
import hu.ideaimpl.mageofscrum.server.dbo.UserDAOImpl;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.server.entity.UserData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;

public class HibernateUtil {
	private static final EntityManagerFactory entityManagerFactory;

	static {
		entityManagerFactory = new Ejb3Configuration()
		.addAnnotatedClass(User.class)
		.addAnnotatedClass(Role.class)
		.addAnnotatedClass(Team.class)
		.addAnnotatedClass(UserData.class)
		.addAnnotatedClass(Project.class)
		.configure("hibernate.cfg.xml").buildEntityManagerFactory();
	}

	static {
		TeamDAOImpl teamDAO = new TeamDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
		ProjectDAOImpl projectDAO = new ProjectDAOImpl();
		RoleDAOImpl roleDAO = new RoleDAOImpl();

		roleDAO.saveRole("admin", "Has all privileges");
		roleDAO.saveRole("owner", "Has almost all privileges");
		roleDAO.saveRole("master", "Has some privileges");

		teamDAO.saveTeam("A team");
		teamDAO.saveTeam("B team");
		teamDAO.saveTeam("B team");

		userDAO.saveUser("oturk", "oturk");
		userDAO.saveUser("sys", "sys");
		Team bTeam = teamDAO.findTeam("B team");
		teamDAO.addUser(bTeam.getId(), "sys");
		userDAO.saveUser("dzinnn", "dzinnn");

		projectDAO.saveProject("First projet", "Some important project");

		User user = userDAO.findUser("oturk");
		Role role = roleDAO.findRole("admin");
		Team team = teamDAO.findTeam("A team");

		Project project = projectDAO.findProject("First projet");
		userDAO.addRole(user.getId(), role.getId());
		userDAO.addRole(user.getId(), 2L);
		projectDAO.addOwner("oturk", project.getId());
		projectDAO.addTeam(team.getId(), project.getId());
		teamDAO.addUser(team.getId(), "oturk");
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}