package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.Task;
import hu.ideaimpl.mageofscrum.server.entity.TaskStatus;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TaskDAOImpl implements TaskDAO {

	@Override
	public void saveTask(String name, String desc, int estTime, int priority) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
		Task task = new Task();
		task.setName(name);
		task.setDescription(desc);
		task.setEstimateTime(estTime);
		task.setPriority(priority);
		task.setCreated(new Date());
		TaskStatus status = em.getReference(TaskStatus.class, 1L);
		task.setStatus(status);
		
		tx = em.getTransaction();
		tx.begin();
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

}
