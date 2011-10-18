package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Task;
import hu.ideaimpl.mageofscrum.server.entity.TaskStatus;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskDAOImpl implements TaskDAO {
	Logger log = LoggerFactory.getLogger(TaskDAOImpl.class);
	
	@Override
	public void saveTask(Long projectId, String name, String desc, int estTime, int priority) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
		tx = em.getTransaction();
		tx.begin();
		Project project = em.getReference(Project.class, projectId);
		
		Task task = new Task();
		task.setName(name);
		task.setDescription(desc);
		task.setEstimateTime(estTime);
		task.setPriority(priority);
		task.setCreated(new Date());
		task.setProject(project);
		TaskStatus status = em.getReference(TaskStatus.class, 1L);
		log.info("taskStatus: "+status.getStatus());
		task.setStatus(status);
		
		em.persist(task);
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
	public Task findTask(String name) {
		EntityManager em = HibernateUtil.getEntityManager();
		List<Task> result = em.createQuery("from Task t where t.name = :name")
				.setParameter("name", name).getResultList();
		if(result.size() >0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public void updateTask(Long id, String name, int estTime, int priority, String description) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
		tx = em.getTransaction();
		tx.begin();
		Task task = em.getReference(Task.class, id);
		task.setName(name);
		task.setDescription(description);
		task.setEstimateTime(estTime);
		task.setPriority(priority);
		task.setCreated(new Date());
		
		em.persist(task);
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
	public void changeStatus(String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTask(Long id) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Task task = em.getReference(Task.class, id);
			em.remove(task);
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
