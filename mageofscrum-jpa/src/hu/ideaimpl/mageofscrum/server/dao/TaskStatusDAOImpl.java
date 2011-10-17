package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.TaskStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TaskStatusDAOImpl implements TaskStatusDAO {

	@Override
	public void saveStatus(String name) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
		TaskStatus status = new TaskStatus();
		status.setStatus(name);
		tx = em.getTransaction();
		tx.begin();
		em.persist(status);
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
