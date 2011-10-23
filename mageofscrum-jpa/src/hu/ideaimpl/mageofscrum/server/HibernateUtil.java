package hu.ideaimpl.mageofscrum.server;

import hu.ideaimpl.mageofscrum.server.dao.ProjectDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.RoleDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.SprintDAO;
import hu.ideaimpl.mageofscrum.server.dao.SprintDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAO;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TaskStatusDAO;
import hu.ideaimpl.mageofscrum.server.dao.TaskStatusDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TeamDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.UserDAOImpl;
import hu.ideaimpl.mageofscrum.server.entity.History;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Sprint;
import hu.ideaimpl.mageofscrum.server.entity.Task;
import hu.ideaimpl.mageofscrum.server.entity.TaskStatus;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.server.entity.UserData;
import hu.ideaimpl.mageofscrum.shared.Operations;
import hu.ideaimpl.mageofscrum.shared.Roles;
import hu.ideaimpl.mageofscrum.shared.TaskStatuses;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.util.Date;

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
		.addAnnotatedClass(Sprint.class)
		.addAnnotatedClass(History.class)
		.configure("hibernate.cfg.xml").buildEntityManagerFactory();
	}

	static {
		TeamDAOImpl teamDAO = new TeamDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
		ProjectDAOImpl projectDAO = new ProjectDAOImpl();
		RoleDAOImpl roleDAO = new RoleDAOImpl();
		TaskStatusDAO statusDAO = new TaskStatusDAOImpl();
		TaskDAO taskDAO = new TaskDAOImpl();
		SprintDAO sprintDAO = new SprintDAOImpl();
		
		roleDAO.saveRole(Roles.ADMIN.name(), "Admin role");
		roleDAO.saveRole(Roles.OWNER.name(), "Owner role");
		roleDAO.saveRole(Roles.MASTER.name(), "Master role");
		roleDAO.saveRole(Roles.USER.name(), "User role");
		
		statusDAO.saveStatus(TaskStatuses.BACKLOG.name());
		statusDAO.saveStatus(TaskStatuses.PLANNED.name());
		statusDAO.saveStatus(TaskStatuses.STARTED.name());
		statusDAO.saveStatus(TaskStatuses.COMPLETED.name());
		statusDAO.saveStatus(TaskStatuses.MOVEDBACK.name());
		
		teamDAO.saveTeam("A team");
		teamDAO.saveTeam("B team");
		teamDAO.saveTeam("C team");
		
		
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
		
		taskDAO.saveTask(proj.getId(), "first task", "This is my first task", 8, 1);
		taskDAO.saveTask(proj.getId(), "second task", "This is my second task", 8, 1);
		taskDAO.saveTask(proj.getId(), "third task", "This is my third task", 8, 1);
//		
//		projectDAO.addTask(proj.getId(), task1.getId());
//		projectDAO.addTask(proj.getId(), task2.getId());
//		projectDAO.addTask(proj.getId(), task3.getId());
		
		User user = userDAO.findUser("oturk");
		UserDataDO data = new UserDataDO();
		data.setEmail("oturk@email.hu");
		data.setSurname("Ottó");
		data.setForename("Türk");
		userDAO.updateUserData(1L, data);
		Role role = roleDAO.findRole(Roles.ADMIN.name());
		Role owner = roleDAO.findRole(Roles.OWNER.name());
		Team team = teamDAO.findTeam("A team");
		
		Project project = projectDAO.findProject("First projet");
		Task task = taskDAO.findTask("first task");
		sprintDAO.startSprint(project.getId());
		sprintDAO.addTask(project.getId(), task.getId());
		
		userDAO.addRole(user.getId(), role.getId());
		userDAO.addRole(user.getId(), owner.getId());
		projectDAO.addOwner("oturk", project.getId());
		projectDAO.addTeam(team.getId(), project.getId());
		teamDAO.addUser(team.getId(), "oturk");
		
		long day = 86400000;
		
		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 12, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 4, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 16, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 20, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 4, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 6, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 10, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 24, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 24, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 10, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 12, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 6, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date());
		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date());
		
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day));
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 2));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 2));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 2));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 2));
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 3));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 3));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 3));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 3));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 3));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 3));
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 4));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 4));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 4));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 4));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 4));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 4));
		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 5));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 5));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 5));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 6));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 6));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 6));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 6));
//		
		sprintDAO.logHistory(project.getId(), Operations.ADD, 4, new Date(System.currentTimeMillis() + day * 7));
		sprintDAO.logHistory(project.getId(), Operations.ADD, 6, new Date(System.currentTimeMillis() + day * 7));
		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date(System.currentTimeMillis() + day * 7));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 8));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 8));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 8));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 8));
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 9));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 9));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 9));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 9));
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 10));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 10));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 10));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 10));
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 11));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 11));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 11));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 11));
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 12));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 12));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 12));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 12));
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 13));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 13));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 13));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 13));
		
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 14));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 14));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 14));
		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 14));
		
	
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}