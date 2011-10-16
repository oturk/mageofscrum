package hu.ideaimpl.mageofscrum.server.dbo;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class UserDAOImpl implements UserDAO {
	
	@Override
	public void saveUser(String username, String password) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			User user = new User();
			user.setUsername(username);
			Sha256Hash hash = new Sha256Hash(password);
			user.setPassword(hash.toString());
			tx = em.getTransaction();
			tx.begin();
			em.persist(user);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public User findUser(String username) {
		EntityManager em = HibernateUtil.getEntityManager();
		javax.persistence.Query query = em.createQuery("select u from User u where u.username = :username").setParameter("username", username);
		List<User> users = query.getResultList();
		if (users != null) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(user);
			tx.commit();
		} catch (RuntimeException ex) {
			if(tx != null && tx.isActive()){
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteUser(String username) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		List<User> users = em.createQuery("from User u where u.username = :username").setParameter("username", username).getResultList();
		try {
			if (users != null) {
				tx = em.getTransaction();
				tx.begin();
				em.remove(users.get(0));
				tx.commit();
			}
		} catch (RuntimeException ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}

	}

	@Override
	public List<User> listAll(String username) {
		EntityManager em = HibernateUtil.getEntityManager();
		List<User> users = em.createQuery("from User u where u.username != :username")
				.setParameter("username", username).getResultList();
		return users;
	}

	@Override
	public void addRole(Long userId, Long roleId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
		tx = em.getTransaction();
		tx.begin();
		User user = em.getReference(User.class, userId);
		
		Role role = em.getReference(Role.class, roleId);
		user.addRole(role);
//		role.addUser(user);
		em.persist(role);
//		em.flush();
//		em.refresh(user);
		tx.commit();
		}catch(RuntimeException ex){
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			ex.printStackTrace();
		}finally{
			em.close();
		}
	}

	@Override
	public void addProject(String username, Long projectId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		
		User user = (User) em.createQuery("from User u where u.username = :username")
				.setParameter("username", username).getResultList().get(0);
		
		Project project = em.getReference(Project.class, projectId);
		try{
			tx = em.getTransaction();
			tx.begin();
			
			
			
			tx.commit();
		}catch(RuntimeException ex){
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			ex.printStackTrace();
		}finally{
			em.close();
		}
	}

}
