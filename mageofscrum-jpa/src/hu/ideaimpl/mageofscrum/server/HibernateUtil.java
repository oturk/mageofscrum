package hu.ideaimpl.mageofscrum.server;

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

	static{
		entityManagerFactory= new Ejb3Configuration()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class)
				.addAnnotatedClass(Team.class)
				.addAnnotatedClass(UserData.class)
				.addAnnotatedClass(Project.class)
				.configure("hibernate.cfg.xml")
				.buildEntityManagerFactory();
	}
	
//	static{
//		TeamDAOImpl teamDBO = new TeamDAOImpl();
//		UserDAOImpl userDBO = new UserDAOImpl();
//		ProjectDAOImpl projectDBO = new ProjectDAOImpl();
//		RoleDAOImpl roleDBO = new RoleDAOImpl();
//		
//		roleDBO.saveRole("admin", "Has all privileges");
//		roleDBO.saveRole("owner", "Has almost all privileges");
//		roleDBO.saveRole("master", "Has some privileges");
//		
//		teamDBO.saveTeam("A team");
//		teamDBO.saveTeam("B team");
//		teamDBO.saveTeam("C team");
//		
//		userDBO.saveUser("oturk", "oturk");
//		
//		projectDBO.saveProject("First projet", "Some important project");
//		
//		User user = userDBO.findUser("oturk");
//		Role role = roleDBO.findRole("admin");
//		Team team = teamDBO.findTeam("A team");
//		Project project = projectDBO.findProject("First projet");
//		
//		role.addUser(user);
////		team.addMember(user);
//		user.addRole(role);
////		user.addTeam(team);
////		project.setUser(user);
////		user.addProject(project);
////		project.setTeam(team);
////		team.addProject(project);
//		
//		userDBO.updateUser(user);
////		teamDBO.updateTeam(team);
//	}
	
	
	public static EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
}