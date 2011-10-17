package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RoleDAOImpl implements RoleDAO {
	
	@Override
	public void saveRole(String name, String description) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);
		tx = em.getTransaction();
		tx.begin();
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
	public Role findRole(String name) {
		EntityManager em = HibernateUtil.getEntityManager();
		Query q = em.createQuery("from Role r where r.name = :name")
				.setParameter("name", name);
		List<Role> roles = q.getResultList();
		if(roles != null){
			return roles.get(0);
		}
		return null;
	}

	@Override
	public void updateRole(Role role) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(role);
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
	public List<User> listUsersByRole(String name) {
		EntityManager em = HibernateUtil.getEntityManager();
		Role role = (Role) em.createQuery("from Role r where r.name = :name")
				.setParameter("name", name).getResultList().get(0);
		
		if(role.getUsers() != null){
			return role.getUsers();
		}
		
		return new ArrayList<User>();
	}

	@Override
	public Role findRole(Long id) {
		EntityManager em = HibernateUtil.getEntityManager();
		return em.find(Role.class, id);
	}

	@Override
	public List<Role> listAll() {
		EntityManager em = HibernateUtil.getEntityManager();
		return em.createQuery("from Role").getResultList();
	}

	@Override
	public List<Role> listInverse(List<Long> roles) {
		return null;
	}

	@Override
	public List<Role> listOtherRoles(Long userId) {
		EntityManager em = HibernateUtil.getEntityManager();
		List<Role> roles = null;
		User user = em.getReference(User.class, userId);
		try {
			if (user.getRoles().size() > 0) {
				List<Long> rolesIds = new ArrayList<Long>();
				for (Role u : user.getRoles()) {
					rolesIds.add(u.getId());
				}
				 roles = em.createQuery("select u from Role u where u.id not in (:rolesIds)").setParameter("rolesIds", rolesIds).getResultList();
			}else{
				roles = em.createQuery("from Role").getResultList();
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		
		return roles;
	}

	@Override
	public List<User> listOwners() {
		Role owner = findRole("owner");
		List<User> result = new ArrayList<User>();
		for(User u : owner.getUsers()){
			result.add(u);
		}
		return result;
	}

}
