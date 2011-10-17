package hu.ideaimpl.mageofscrum.server;

import hu.ideaimpl.mageofscrum.server.dao.ProjectDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.RoleDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAO;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TaskStatusDAO;
import hu.ideaimpl.mageofscrum.server.dao.TaskStatusDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TeamDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.UserDAOImpl;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Task;
import hu.ideaimpl.mageofscrum.server.entity.TaskStatus;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.server.entity.UserData;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

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
		.addAnnotatedClass(Task.class)
		.addAnnotatedClass(TaskStatus.class)
		.configure("hibernate.cfg.xml").buildEntityManagerFactory();
	}

	static {
		TeamDAOImpl teamDAO = new TeamDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
		ProjectDAOImpl projectDAO = new ProjectDAOImpl();
		RoleDAOImpl roleDAO = new RoleDAOImpl();
		TaskStatusDAO statusDAO = new TaskStatusDAOImpl();
		TaskDAO taskDAO = new TaskDAOImpl();
		
		roleDAO.saveRole("admin", "Has all privileges");
		roleDAO.saveRole("owner", "Has almost all privileges");
		roleDAO.saveRole("master", "Has some privileges");
		
		statusDAO.saveStatus("backlog");
		statusDAO.saveStatus("sprint");
		statusDAO.saveStatus("completed");
		
		teamDAO.saveTeam("A team");
		teamDAO.saveTeam("B team");
		teamDAO.saveTeam("C team");
		
		taskDAO.saveTask("first task", "This is my first task", 8, 1);
		taskDAO.saveTask("second task", "This is my second task", 8, 1);
		taskDAO.saveTask("third task", "This is my third task", 8, 1);
		
		userDAO.saveUser("oturk", "oturk");
		userDAO.saveUser("sys", "sys");
		Team bTeam = teamDAO.findTeam("B team");
		teamDAO.addUser(bTeam.getId(), "sys");
		userDAO.saveUser("dzinnn", "dzinnn");
		
		projectDAO.saveProject("First projet", "Some important project");
		Task task1 = taskDAO.findTask("first task");
		Task task2 = taskDAO.findTask("second task");
		Task task3 = taskDAO.findTask("third task");
		Project proj = projectDAO.findProject("First projet");
		
		projectDAO.addTask(proj.getId(), task1.getId());
		projectDAO.addTask(proj.getId(), task2.getId());
		projectDAO.addTask(proj.getId(), task3.getId());
		
		User user = userDAO.findUser("oturk");
		UserDataDO data = new UserDataDO();
		data.setEmail("oturk@email.hu");
		data.setSurname("Ottó");
		data.setForename("Türk");
		userDAO.updateUserData(1L, data);
		Role role = roleDAO.findRole("admin");
		Team team = teamDAO.findTeam("A team");
		
		Project project = projectDAO.findProject("First projet");
		userDAO.addRole(user.getId(), role.getId());
		projectDAO.addOwner("oturk", project.getId());
		projectDAO.addTeam(team.getId(), project.getId());
		teamDAO.addUser(team.getId(), "oturk");
	
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}