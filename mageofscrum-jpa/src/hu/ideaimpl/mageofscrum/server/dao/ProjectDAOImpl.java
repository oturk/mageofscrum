package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Task;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectDAOImpl implements ProjectDAO{
	Logger log = LoggerFactory.getLogger(ProjectDAOImpl.class);
	
	@Override
	public void saveProject(String name, String description) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			Project project = new Project();
			project.setName(name);
			project.setDescription(description);
			tx = em.getTransaction();
			tx.begin();
			em.persist(project);
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
	public Project findProject(String name) {
		EntityManager em = HibernateUtil.getEntityManager();
		javax.persistence.Query query = em.createQuery("select p from Project p where p.name = :name")
				.setParameter("name", name);
		List<Project> result = query.getResultList();
		if(result.size() > 0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public void updateProject(Long id, Long ownerId, Long teamId, String name, String description) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Project project = em.getReference(Project.class, id);
			User owner = em.getReference(User.class, ownerId);
			Team team = em.getReference(Team.class, teamId);
			
			project.setName(name);
			project.setDescription(description);
			project.setUser(owner);
			project.setTeam(team);
			em.persist(project);
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
	public void deleteProject(Long id) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			Project project = em.find(Project.class, id);
			tx = em.getTransaction();
			tx.begin();
			em.remove(project);
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
	public List<Project> listAll() {
		EntityManager em = HibernateUtil.getEntityManager();
		return em.createQuery("from Project").getResultList();
	}

	@Override
	public List<Project> listUsersProjects(String username) {
		EntityManager em = HibernateUtil.getEntityManager();
		User user = (User) em.createQuery("from User u where u.username = :username")
				.setParameter("username", username).getResultList().get(0);
		return em.createQuery("from Project p where p.user = :user")
				.setParameter("user", user).getResultList();
	}
	
	@Override
	public void addOwner(String username, Long projectId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			User user = (User) em.createQuery("from User u where u.username = :username")
					.setParameter("username", username).getResultList().get(0);
			
			Project project = em.getReference(Project.class, projectId);
			project.setUser(user);
			em.persist(project);
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
	public void addTeam(Long teamId, Long projectId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			Team team = em.getReference(Team.class, teamId);
			
			Project project = em.getReference(Project.class, projectId);
			project.setTeam(team);
			em.persist(project);
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
	public void addTask(Long projectId, Long taskId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			Task task = em.getReference(Task.class, taskId);
			Project project = em.getReference(Project.class, projectId);
			task.setProject(project);
//			project.addTask(task);
			log.info("taskAdded: "+task.getName());
			em.persist(project);
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
	public Project findProject(Long id) {
		EntityManager em = HibernateUtil.getEntityManager();
		
		return em.getReference(Project.class, id);
	}

}
