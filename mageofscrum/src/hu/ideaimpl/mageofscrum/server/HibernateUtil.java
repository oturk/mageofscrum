package hu.ideaimpl.mageofscrum.server;

import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.server.entity.UserData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration()
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(Role.class)
					.addAnnotatedClass(Team.class)
					.addAnnotatedClass(UserData.class).configure()
					.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		Role adminRole = new Role();
		adminRole.setId(1L);
		adminRole.setName("ADMIN");
		adminRole.setDescription("Admin role");
		session.save(adminRole);
		
		Role projectOwnerRole = new Role();
		projectOwnerRole.setId(2L);
		projectOwnerRole.setName("OWNER");
		projectOwnerRole.setDescription("Project owner");
		session.save(projectOwnerRole);
		
		Role scrumMasterRole = new Role();
		scrumMasterRole.setId(3L);
		scrumMasterRole.setName("MASTER");
		scrumMasterRole.setDescription("Scrum master");
		session.save(scrumMasterRole);
		
		Role userRole = new Role();
		userRole.setId(4L);
		userRole.setName("USER");
		userRole.setDescription("User role");
		session.save(userRole);
		
		Team team1 = new Team();
		team1.setName("Team 1");
		session.save(team1);
		
		Team team2 = new Team();
		team2.setName("Team 2");
		session.save(team2);
		
		Team team3 = new Team();
		team3.setName("Team 3");
		session.save(team3);
		
		UserData adminData = new UserData();
		adminData.setId(100000L);
		adminData.setForeName("Ottó");
		adminData.setSureName("Türk");
		adminData.setAddress("4028");
		adminData.setCity("Debrecen");
		adminData.setAddress("Tölgyfa utca 26/B 35");
		adminData.setPhone("20/2665292");
		adminData.setNickname("superuser");
		session.save(adminData);
		
		List<Role> roles = new ArrayList<Role>();
		List<Team> teams = new ArrayList<Team>();
		teams.add(team1);
		teams.add(team2);
		User admin = new User();
		admin.setEmail("admin");
		admin.setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
		roles.add(adminRole);
		admin.setRoles(roles);
		admin.setTeams(teams);
		admin.setData(adminData);
		session.save(admin);
		
		roles = new ArrayList<Role>();
		User owner = new User();
		owner.setEmail("owner");
		owner.setPassword("4c1029697ee358715d3a14a2add817c4b01651440de808371f78165ac90dc581");
		roles.add(projectOwnerRole);
		roles.add(adminRole);
		owner.setRoles(roles);
//		owner.setData(new UserData());
		session.save(owner);
		
		roles = new ArrayList<Role>();
		User master = new User();
		master.setEmail("master");
		master.setPassword("fc613b4dfd6736a7bd268c8a0e74ed0d1c04a959f59dd74ef2874983fd443fc9");
		roles.add(scrumMasterRole);
		master.setRoles(roles);
//		master.setData(new UserData());
		session.save(master);
		
		roles = new ArrayList<Role>();
		User user = new User();
		user.setEmail("user");
		user.setPassword("04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb");
		roles.add(userRole);
		user.setRoles(roles);
//		user.setData(new UserData());
		session.save(user);
		
		
//		data.setUser(admin);
//		session.persist(data);
		tx.commit();
	}
	
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}