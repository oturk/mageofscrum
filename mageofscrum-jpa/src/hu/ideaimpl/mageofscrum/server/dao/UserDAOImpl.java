package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.server.entity.UserData;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class UserDAOImpl implements UserDAO {
	
	@Override
	public void saveUser(String username, String password) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		User existing = findUser(username);
		if (existing == null) {
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
	}

	@Override
	public User findUser(String username) {
		EntityManager em = HibernateUtil.getEntityManager();
		javax.persistence.Query query = em.createQuery("select u from User u where u.username = :username").setParameter("username", username);
		List<User> users = query.getResultList();
		if (users.size() > 0) {
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
			User newUser = em.getReference(User.class, user.getId());
			UserData data = newUser.getData();
			data.setEmail(user.getData().getEmail());
			newUser.setData(data);
			em.persist(newUser);
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

	@Override
	public void removeRole(Long userId, Long roleId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
		tx = em.getTransaction();
		tx.begin();
		User user = em.getReference(User.class, userId);
		
		Role role = em.getReference(Role.class, roleId);
		user.removeRole(role);
		em.persist(role);
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
	public void changePassword(Long userId, String password) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
		tx = em.getTransaction();
		tx.begin();
		User user = em.getReference(User.class, userId);
		Sha256Hash hash = new Sha256Hash(password);
		user.setPassword(hash.toString());
		em.persist(user);
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
	public void updateUserData(Long userId, UserDataDO data) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			User user = em.getReference(User.class, userId);
			UserData uData = null;
			if (user.getData() == null) {
				uData = new UserData();
				uData.setEmail(data.getEmail());
				uData.setSurname(data.getSurname());
				uData.setForename(data.getForename());
			}else{
				uData = user.getData(); 
				uData.setEmail(data.getEmail());
				uData.setSurname(data.getSurname());
				uData.setForename(data.getForename());
			}
			user.setData(uData);
			em.persist(uData);
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
	public List<Project> listUsersProjects(Long userId) {
		EntityManager em = HibernateUtil.getEntityManager();
		List<Project> result = new ArrayList<Project>();
		try {
			User user = em.getReference(User.class, userId);
			for(Team team : user.getTeams()){
				result.addAll(team.getProjects());
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
