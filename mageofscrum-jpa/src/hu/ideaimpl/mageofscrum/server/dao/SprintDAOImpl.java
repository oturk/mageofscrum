package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Sprint;
import hu.ideaimpl.mageofscrum.server.entity.Task;
import hu.ideaimpl.mageofscrum.server.entity.TaskStatus;
import hu.ideaimpl.mageofscrum.shared.TaskStatuses;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SprintDAOImpl implements SprintDAO {

	@Override
	public void startSprint(Long projectId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Project project = em.getReference(Project.class, projectId);
			Sprint sprint = new Sprint();
			sprint.setStartDate(new Date());
			sprint.setProject(project);
			
			em.persist(sprint);
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
	public void stopSprint(Long projectId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Project project = em.getReference(Project.class, projectId);
			Sprint sprint = (Sprint) em.createQuery("from Sprint s where s.project = :project and s.endDate is null")
					.setParameter("project", project).getResultList().get(0);

			sprint.setEndDate(new Date());
			em.persist(sprint);
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
	public void addTask(Long projectId, Long taskId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Project project = em.getReference(Project.class, projectId);
			Task task = em.getReference(Task.class, taskId);
			
			Sprint sprint = (Sprint) em.createQuery("from Sprint s where s.project = :project and s.endDate is null")
					.setParameter("project", project).getResultList().get(0);

			TaskStatus status = (TaskStatus) em.createQuery("from TaskStatus s where s.status = :status")
					.setParameter("status", TaskStatuses.PLANNED.name()).getResultList().get(0);
			task.setStatus(status);
			task.setSprint(sprint);
			em.persist(task);
//			sprint.addTask(task);
//			em.persist(sprint);
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
	public Sprint findActualSprint(Long projectId) {
		EntityManager em = HibernateUtil.getEntityManager();
		Project project = em.getReference(Project.class, projectId);
		Sprint sprint = (Sprint) em.createQuery("from Sprint s where s.project = :project and s.endDate is null")
				.setParameter("project", project).getResultList().get(0);
		return sprint;
	}

	@Override
	public boolean hasActiveSprint(Long projectId) {
		EntityManager em = HibernateUtil.getEntityManager();
		Project project = em.getReference(Project.class, projectId);
		if(project.getSprints() == null || project.getSprints().size() == 0){
			return false;
		}
		List<Sprint> sprint = em.createQuery("from Sprint s where s.project = :project and s.endDate is null")
				.setParameter("project", project).getResultList();
		if(sprint == null || sprint.size() == 0){
			return false;
		}
		return true;
	}

}
