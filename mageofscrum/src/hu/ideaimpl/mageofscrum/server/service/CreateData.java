package hu.ideaimpl.mageofscrum.server.service;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.dao.ProjectDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.RoleDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.SprintDAO;
import hu.ideaimpl.mageofscrum.server.dao.SprintDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAO;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TaskStatusDAO;
import hu.ideaimpl.mageofscrum.server.dao.TaskStatusDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TeamDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.UserDAOImpl;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.shared.Operations;
import hu.ideaimpl.mageofscrum.shared.Roles;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/createdata")
public class CreateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SprintDAO sprintDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TeamDAOImpl teamDAO = new TeamDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
		ProjectDAOImpl projectDAO = new ProjectDAOImpl();
		RoleDAOImpl roleDAO = new RoleDAOImpl();
		TaskStatusDAO statusDAO = new TaskStatusDAOImpl();
		TaskDAO taskDAO = new TaskDAOImpl();
		sprintDAO = new SprintDAOImpl();
		
		Role owner = roleDAO.findRole(Roles.OWNER.name());
		Role master = roleDAO.findRole(Roles.MASTER.name());
		
		teamDAO.saveTeam("A team");
		teamDAO.saveTeam("B team");
		teamDAO.saveTeam("C team");
		teamDAO.saveTeam("D team");
		teamDAO.saveTeam("E team");
		teamDAO.saveTeam("F team");
		teamDAO.saveTeam("G team");
		teamDAO.saveTeam("H team");
		teamDAO.saveTeam("J team");
		teamDAO.saveTeam("K team");
		teamDAO.saveTeam("L team");
		teamDAO.saveTeam("M team");
		teamDAO.saveTeam("N team");
		teamDAO.saveTeam("O team");
		teamDAO.saveTeam("P team");
		teamDAO.saveTeam("Q team");
		teamDAO.saveTeam("R team");
		teamDAO.saveTeam("S team");
		teamDAO.saveTeam("T team");
		
		
		Long teamId = teamDAO.findTeam("A team").getId();
		
		userDAO.saveUser("owner", "owner");
		Long userId = userDAO.findUser("owner").getId();
		userDAO.addRole(userId, owner.getId());
		teamDAO.addUser(teamId, "owner");
		
		userDAO.saveUser("master", "master");
		userId = userDAO.findUser("master").getId();
		userDAO.addRole(userId, master.getId());
		teamDAO.addUser(teamId, "master");
		
		userDAO.saveUser("dzinnn", "dzinnn");
		userDAO.saveUser("sys1", "sys");
		userDAO.saveUser("sys2", "sys");
		userDAO.saveUser("sys3", "sys");
		userDAO.saveUser("sys4", "sys");
		userDAO.saveUser("sys5", "sys");
		userDAO.saveUser("sys6", "sys");
		userDAO.saveUser("sys7", "sys");
		userDAO.saveUser("sys8", "sys");
		userDAO.saveUser("sys9", "sys");
		userDAO.saveUser("sys10", "sys");
		userDAO.saveUser("sys11", "sys");
		userDAO.saveUser("sys12", "sys");
		userDAO.saveUser("sys13", "sys");
		userDAO.saveUser("sys14", "sys");
		userDAO.saveUser("sys15", "sys");
		userDAO.saveUser("sys16", "sys");
		userDAO.saveUser("sys17", "sys");
		userDAO.saveUser("sys18", "sys");
		userDAO.saveUser("sys19", "sys");
		userDAO.saveUser("sys20", "sys");
		
		Team aTeam = teamDAO.findTeam("A team");
		Team bTeam = teamDAO.findTeam("B team");
		Team cTeam = teamDAO.findTeam("C team");
		
		projectDAO.saveProject("projet_1", "Some important project");
		Long projectId = projectDAO.findProject("projet_1").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_2", "Some important project");
		projectId = projectDAO.findProject("projet_2").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(bTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_3", "Some important project");
		projectId = projectDAO.findProject("projet_3").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(cTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_4", "Some important project");
		projectId = projectDAO.findProject("projet_4").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(cTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_5", "Some important project");
		projectId = projectDAO.findProject("projet_5").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_6", "Some important project");
		projectId = projectDAO.findProject("projet_6").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(bTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_7", "Some important project");
		projectId = projectDAO.findProject("projet_7").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(bTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_8", "Some important project");
		projectId = projectDAO.findProject("projet_8").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(bTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_9", "Some important project");
		projectId = projectDAO.findProject("projet_9").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_10", "Some important project");
		projectId = projectDAO.findProject("projet_10").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(cTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_11", "Some important project");
		projectId = projectDAO.findProject("projet_11").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_12", "Some important project");
		projectId = projectDAO.findProject("projet_12").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_13", "Some important project");
		projectId = projectDAO.findProject("projet_13").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_14", "Some important project");
		projectId = projectDAO.findProject("projet_14").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_15", "Some important project");
		projectId = projectDAO.findProject("projet_15").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_16", "Some important project");
		projectId = projectDAO.findProject("projet_16").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_17", "Some important project");
		projectId = projectDAO.findProject("projet_17").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_18", "Some important project");
		projectId = projectDAO.findProject("projet_18").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_19", "Some important project");
		projectId = projectDAO.findProject("projet_19").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		projectDAO.saveProject("projet_20", "Some important project");
		projectId = projectDAO.findProject("projet_20").getId();
		projectDAO.addOwner("owner", projectId);
		projectDAO.addTeam(aTeam.getId(), projectId);
		
		Project proj = projectDAO.findProject("projet_1");
		
		taskDAO.saveTask(proj.getId(), "first task", "This is my first task", 8, 1);
		taskDAO.saveTask(proj.getId(), "second task", "This is my second task", 8, 1);
		taskDAO.saveTask(proj.getId(), "third task", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_4", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_5", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_6", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_7", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_8", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_9", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_10", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_11", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_12", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_13", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_14", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_15", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_16", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_17", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_18", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_19", "This is my third task", 8, 1);
		taskDAO.saveTask(proj.getId(), "task_20", "This is my third task", 8, 1);
		
		taskDAO.saveTask(proj.getId(), "task_21", "This is my third task", 8, 1);
		projectId = projectDAO.findProject("projet_1").getId();
		Long taskId = taskDAO.findTask("task_21").getId();
		sprintDAO.startSprint(projectId);
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_22", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_22").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_23", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_23").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_24", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_24").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_25", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_25").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_26", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_26").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_27", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_27").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_28", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_28").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_29", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_29").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_30", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_30").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_31", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_31").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_32", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_32").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_33", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_33").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_34", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_34").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_35", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_35").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_36", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_36").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_37", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_37").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_38", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_38").getId();
		sprintDAO.addTask(projectId, taskId);
		
		taskDAO.saveTask(proj.getId(), "task_39", "This is my third task", 8, 1);
		taskId = taskDAO.findTask("task_39").getId();
		sprintDAO.addTask(projectId, taskId);

		createLogHistory();
	}
	
	public void createLogHistory(){
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			
			List<BigInteger> projId = em.createNativeQuery("select id from project where name = 'projet_1'").getResultList();
			
			em.createNativeQuery("INSERT INTO sprint (id, startdate, enddate, project_fk) values(:id, '2011-09-01 14:26:02.380000','2011-09-20 14:26:02.380000', :pFk)")
			.setParameter("id", 10000L)
			.setParameter("pFk", projId.get(0))
			.executeUpdate();
			
//			em.createNativeQuery("insert into project_sprints (project_id, sprint_id) values(:pId, :sId)")
//			.setParameter("pId", projId.get(0))
//			.setParameter("sId", 10000L)
//			.executeUpdate();
			
			long day = 86400000;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse("2011-09-01");
			
			em.createNativeQuery("insert into history (id, moddate, operation, time, sprint_fk) values(:sId, :moddate, 'ADD', 8, :sFk)")
			.setParameter("sId", 10010L)
			.setParameter("moddate", new Date(date.getTime() + day * 2))
			.setParameter("sFk", 10000L)
			.executeUpdate();
			
//				em.createNativeQuery("insert into sprint_history (sprint_id, history_id) values(:sId, :hId)")
//				.setParameter("sId", 10000L)
//				.setParameter("hId", 10010L)
//				.executeUpdate();
			
			em.createNativeQuery("insert into history (id, moddate, operation, time, sprint_fk) values(:sId, :moddate, 'ADD', 8, :sFk)")
			.setParameter("sId", 10011L)
			.setParameter("moddate", new Date(date.getTime() + day * 2))
			.setParameter("sFk", 10000L)
			.executeUpdate();
			
//				em.createNativeQuery("insert into sprint_history (sprint_id, history_id) values(:sId, :hId)")
//				.setParameter("sId", 10000L)
//				.setParameter("hId", 10011L)
//				.executeUpdate();
			
			em.createNativeQuery("insert into history (id, moddate, operation, time, sprint_fk) values(:sId, :moddate, 'ADD', 8, :sFk)")
			.setParameter("sId", 10012L)
			.setParameter("moddate", new Date(date.getTime() + day * 2))
			.setParameter("sFk", 10000L)
			.executeUpdate();
			
//				em.createNativeQuery("insert into sprint_history (sprint_id, history_id) values(:sId, :hId)")
//				.setParameter("sId", 10000L)
//				.setParameter("hId", 10012L)
//				.executeUpdate();
			
			em.createNativeQuery("insert into history (id, moddate, operation, time, sprint_fk) values(:sId, :moddate, 'ADD', 8, :sFk)")
			.setParameter("sId", 10013L)
			.setParameter("moddate", new Date(date.getTime() + day * 2))
			.setParameter("sFk", 10000L)
			.executeUpdate();
			
//				em.createNativeQuery("insert into sprint_history (sprint_id, history_id) values(:sId, :hId)")
//				.setParameter("sId", 10000L)
//				.setParameter("hId", 10013L)
//				.executeUpdate();
			
			em.createNativeQuery("insert into history (id, moddate, operation, time, sprint_fk) values(:sId, :moddate, 'ADD', 8, :sFk)")
			.setParameter("sId", 10014L)
			.setParameter("moddate", new Date(date.getTime() + day * 2))
			.setParameter("sFk", 10000L)
			.executeUpdate();
			
//				em.createNativeQuery("insert into sprint_history (sprint_id, history_id) values(:sId, :hId)")
//				.setParameter("sId", 10000L)
//				.setParameter("hId", 10014L)
//				.executeUpdate();
				
			em.createNativeQuery("insert into history (id, moddate, operation, time, sprint_fk) values(:sId, :moddate, 'REPORT', 8, :sFk)")
			.setParameter("sId", 10015L)
			.setParameter("moddate", new Date(date.getTime() + day * 4))
			.setParameter("sFk", 10000L)
			.executeUpdate();
			
//				em.createNativeQuery("insert into sprint_history (sprint_id, history_id) values(:sId, :hId)")
//				.setParameter("sId", 10000L)
//				.setParameter("hId", 10015L)
//				.executeUpdate();
				
			em.createNativeQuery("insert into history (id, moddate, operation, time, sprint_fk) values(:sId, :moddate, 'REPORT', 8, :sFk)")
			.setParameter("sId", 10016L)
			.setParameter("moddate", new Date(date.getTime() + day * 6))
			.setParameter("sFk", 10000L)
			.executeUpdate();
			
//				em.createNativeQuery("insert into sprint_history (sprint_id, history_id) values(:sId, :hId)")
//				.setParameter("sId", 10000L)
//				.setParameter("hId", 10016L)
//				.executeUpdate();
			
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			ex.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if(em.isOpen()){
				em.close();
			}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
