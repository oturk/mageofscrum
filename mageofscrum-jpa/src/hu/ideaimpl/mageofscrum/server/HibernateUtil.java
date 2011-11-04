package hu.ideaimpl.mageofscrum.server;

import hu.ideaimpl.mageofscrum.server.dao.RoleDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TaskStatusDAO;
import hu.ideaimpl.mageofscrum.server.dao.TaskStatusDAOImpl;
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
import hu.ideaimpl.mageofscrum.shared.Roles;
import hu.ideaimpl.mageofscrum.shared.TaskStatuses;

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
		UserDAOImpl userDAO = new UserDAOImpl();
		RoleDAOImpl roleDAO = new RoleDAOImpl();
		TaskStatusDAO statusDAO = new TaskStatusDAOImpl();
//		
		roleDAO.saveRole(Roles.ADMIN.name(), "Admin role");
		roleDAO.saveRole(Roles.OWNER.name(), "Owner role");
		roleDAO.saveRole(Roles.MASTER.name(), "Master role");
		roleDAO.saveRole(Roles.USER.name(), "User role");
		
		statusDAO.saveStatus(TaskStatuses.BACKLOG.name());
		statusDAO.saveStatus(TaskStatuses.PLANNED.name());
		statusDAO.saveStatus(TaskStatuses.STARTED.name());
		statusDAO.saveStatus(TaskStatuses.COMPLETED.name());
		statusDAO.saveStatus(TaskStatuses.MOVEDBACK.name());
		
		Role admin = roleDAO.findRole(Roles.ADMIN.name());
		
		userDAO.saveUser("admin", "admin");
		Long userId = userDAO.findUser("admin").getId();
		userDAO.addRole(userId, admin.getId());
		
//		
//		long day = 86400000;
//		
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 12, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 4, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 16, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 20, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 4, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 6, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 10, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 24, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 24, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 10, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 12, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 6, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date());
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date());
//		
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 2));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 2));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 2));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 2));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 3));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 3));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 3));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 3));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 3));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 3));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 4));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 4));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 4));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 4));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 4));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 4));
//		
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 5));
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 5));
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 5));
////		
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 6));
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 6));
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 6));
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 6));
////		
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 4, new Date(System.currentTimeMillis() + day * 7));
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 6, new Date(System.currentTimeMillis() + day * 7));
//		sprintDAO.logHistory(project.getId(), Operations.ADD, 8, new Date(System.currentTimeMillis() + day * 7));
////		
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 8));
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 8));
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 8));
////		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 8));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 9));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 9));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 9));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 9));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 10));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 10));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 10));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 10));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 11));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 11));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 11));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 11));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 12));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 12));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 12));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 12));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 13));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 13));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 6, new Date(System.currentTimeMillis() + day * 13));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 13));
//		
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 14));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 2, new Date(System.currentTimeMillis() + day * 14));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 4, new Date(System.currentTimeMillis() + day * 14));
//		sprintDAO.logHistory(project.getId(), Operations.REPORT, 8, new Date(System.currentTimeMillis() + day * 14));
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}