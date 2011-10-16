package hu.ideaimpl.mageofscrum.server.dbo;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TeamDAOImpl implements TeamDAO {

	@Override
	public void saveTeam(String name) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			Team team = new Team();
			team.setName(name);
			tx = em.getTransaction();
			tx.begin();
			em.persist(team);
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
	public void deleteTeam(String name) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			Team team = (Team) em.createQuery("from Team t where t.name = :name").setParameter("name", name).getResultList().get(0);
			tx = em.getTransaction();
			tx.begin();
			em.remove(team);
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
	public Team findTeam(String name) {
		EntityManager em = HibernateUtil.getEntityManager();
		javax.persistence.Query query = em.createQuery("select t from Team t where t.name = :name")
				.setParameter("name", name);
		List<Team> teams = query.getResultList();
		if(teams != null){
			return teams.get(0);
		}
		return null;
	}
	
	@Override
	public void updateTeam(Team team) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(team);
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
	public List<Team> listAll() {
		EntityManager em = HibernateUtil.getEntityManager();
		List<Team> teams = em.createQuery("from Team t").getResultList();
		return teams;
	}

	@Override
	public Team findTeam(Long id) {
		EntityManager em = HibernateUtil.getEntityManager();
		return em.find(Team.class, id);
	}

	@Override
	public void deleteTeam(Long id) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			Team team = em.find(Team.class, id);
			tx = em.getTransaction();
			tx.begin();
			em.remove(team);
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
	public void addUser(Long teamId, String username) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Team team = em.getReference(Team.class, teamId);
			User user = (User) em.createQuery("from User u where u.username = :username")
					.setParameter("username", username).getResultList().get(0);
			
			team.addMember(user);
			em.persist(team);
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
