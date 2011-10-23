package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.History;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Sprint;
import hu.ideaimpl.mageofscrum.server.entity.Task;
import hu.ideaimpl.mageofscrum.server.entity.TaskStatus;
import hu.ideaimpl.mageofscrum.shared.Operations;
import hu.ideaimpl.mageofscrum.shared.TaskStatuses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			Sprint sprint = (Sprint) em.createQuery("from Sprint s where s.project = :project and s.endDate is null").setParameter("project", project).getResultList().get(0);

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
			Sprint sprint = (Sprint) em.createQuery("from Sprint s where s.project = :project and s.endDate is null").setParameter("project", project).getResultList().get(0);
			TaskStatus status = (TaskStatus) em.createQuery("from TaskStatus s where s.status = :status").setParameter("status", TaskStatuses.PLANNED.name()).getResultList().get(0);
			Task task = em.getReference(Task.class, taskId);
			task.setStatus(status);
			task.setSprint(sprint);
			em.persist(task);
			tx.commit();
			logHistory(projectId, Operations.ADD, task.getEstimateTime(), new Date());
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
		Sprint sprint = (Sprint) em.createQuery("from Sprint s where s.project = :project and s.endDate is null").setParameter("project", project).getResultList().get(0);
		return sprint;
	}

	@Override
	public boolean hasActiveSprint(Long projectId) {
		EntityManager em = HibernateUtil.getEntityManager();
		Project project = em.getReference(Project.class, projectId);
		if (project.getSprints() == null || project.getSprints().size() == 0) {
			return false;
		}
		List<Sprint> sprint = em.createQuery("from Sprint s where s.project = :project and s.endDate is null").setParameter("project", project).getResultList();
		if (sprint == null || sprint.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void logHistory(Long projectId, Operations operation, int time, Date date) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Project project = em.getReference(Project.class, projectId);
			List<Sprint> sprints = em.createQuery("from Sprint s where s.project = :project and s.endDate is null").setParameter("project", project).getResultList();
			Sprint sprint = sprints.get(0);

			History history = new History();
			history.setModDate(date);
			history.setOperation(operation.name());
			history.setSprint(sprint);
			history.setTime(time);

			sprint.addHistory(history);
			em.persist(history);
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
	public void removeTask(Long projectId, Long taskId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			// Project project = em.getReference(Project.class, projectId);
			// Sprint sprint = (Sprint)
			// em.createQuery("from Sprint s where s.project = :project and s.endDate is null").setParameter("project",
			// project).getResultList().get(0);
			TaskStatus status = (TaskStatus) em.createQuery("from TaskStatus s where s.status = :status").setParameter("status", TaskStatuses.MOVEDBACK.name()).getResultList().get(0);

			Task task = em.getReference(Task.class, taskId);
			task.setStatus(status);
			task.setSprint(null);
			em.persist(task);
			tx.commit();
			logHistory(projectId, Operations.REMOVE, task.getEstimateTime(), new Date());
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
	public void reportTime(Long projectId, Long taskId, int time, Date date) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Task task = em.getReference(Task.class, taskId);
			task.setReportTime(time);
			em.persist(task);
			tx.commit();
			logHistory(projectId, Operations.REPORT, time, date);
		} catch (RuntimeException ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Map<String, Integer> getHistory(Long sprintId) {

		EntityManager em = HibernateUtil.getEntityManager();
		Map<String, Integer> result = new HashMap<String, Integer>();

		Sprint sprint = em.getReference(Sprint.class, sprintId);

		for (History history : sprint.getHistory()) {
			String key = sdf.format(history.getModDate());
			int value = result.containsKey(key) ? result.get(key) : 0;

			if (Operations.ADD.name().equals(history.getOperation())) {
				value += history.getTime();
			} else if (Operations.REPORT.name().equals(history.getOperation())) {
				value -= history.getTime();
			}

			result.put(key, value);
		}

		corrigateData(result);
		return result;
	}
	
	private Comparator<String> keyComparator = new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			Date date1 = new Date();
			Date date2 = new Date();
			try {
				date1 = sdf.parse(o1);
				date2 = sdf.parse(o2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date1.compareTo(date2);
		}
	};

	private void corrigateData(Map<String, Integer> data) {
		long day = 86400000;
		System.out.println("corrigate");
		try {
			String[] keys = new String[data.size()];
			keys = data.keySet().toArray(keys);
			Arrays.sort(keys, keyComparator);
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				Date keyDate = sdf.parse(key);
				Date datePlusOne = new Date(keyDate.getTime() + day);
				String missingKey = sdf.format(datePlusOne);
				while (i + 1 < keys.length && !missingKey.equals(keys[i + 1])) {
					System.out.println("keys: "+missingKey+" "+keys[i + 1]);
					if (!data.containsKey(sdf.format(datePlusOne))) {
						data.put(sdf.format(datePlusOne), 0);
					}
					datePlusOne = new Date(datePlusOne.getTime() + day);
					missingKey = sdf.format(datePlusOne);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("end of corrigate");
	}

	@Override
	public List<Sprint> fetchSprints(Long projectId) {
		EntityManager em = HibernateUtil.getEntityManager();
		Project project = em.getReference(Project.class, projectId);

		return project.getSprints();
	}

}
