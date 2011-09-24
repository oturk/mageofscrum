package hu.ideaimpl.mageofscrum.server;

import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.server.entity.UserData;

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
		adminRole.setName("admin");
		adminRole.setDescription("Admin role");
		session.persist(adminRole);
		
		Role userRole = new Role();
		userRole.setId(2L);
		userRole.setName("user");
		userRole.setDescription("User role");
		session.persist(userRole);
		
		User user = new User();
		user.setEmail("test@test.hu");
		user.setPassword("test");
		user.addRole(adminRole);
		user.addRole(userRole);
		session.persist(user);
		
		User user2 = new User();
		user2.setEmail("user2@user.hu");
		user2.setPassword("user");
		user2.addRole(userRole);
		session.persist(user2);
		
		UserData data = new UserData();
		data.setId(100000L);
		data.setForeName("Ottó");
		data.setSureName("Türk");
		data.setAddress("4028");
		data.setCity("Debrecen");
		data.setAddress("Tölgyfa utca 26/B 35");
		data.setPhone("20/2665292");
		data.setUser(user);
		session.persist(data);
		tx.commit();
	}
	
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}