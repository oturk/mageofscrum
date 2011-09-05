package hu.ideaimpl.mageofscrum.server;

import hu.ideaimpl.mageofscrum.server.entity.User;

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
					.addAnnotatedClass(User.class).configure()
					.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
		
		System.out.println("Add first user");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		User user = new User();
		user.setEmail("test@test.hu");
		user.setPassword("test");
		session.persist(user);
		tx.commit();
	}
	
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}