package hu.ideaimpl.mageofscrum.server.service;

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
import hu.ideaimpl.mageofscrum.server.entity.Task;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.shared.Roles;
import hu.ideaimpl.mageofscrum.shared.TaskStatuses;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/createdata")
public class CreateData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TeamDAOImpl teamDAO = new TeamDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
		ProjectDAOImpl projectDAO = new ProjectDAOImpl();
		RoleDAOImpl roleDAO = new RoleDAOImpl();
		TaskStatusDAO statusDAO = new TaskStatusDAOImpl();
		TaskDAO taskDAO = new TaskDAOImpl();
		SprintDAO sprintDAO = new SprintDAOImpl();
		
		roleDAO.saveRole(Roles.ADMIN.name(), "Admin role");
		roleDAO.saveRole(Roles.OWNER.name(), "Owner role");
		roleDAO.saveRole(Roles.MASTER.name(), "Master role");
		roleDAO.saveRole(Roles.USER.name(), "User role");
		
		statusDAO.saveStatus(TaskStatuses.BACKLOG.name());
		statusDAO.saveStatus(TaskStatuses.PLANNED.name());
		statusDAO.saveStatus(TaskStatuses.STARTED.name());
		statusDAO.saveStatus(TaskStatuses.COMPLETED.name());
		statusDAO.saveStatus(TaskStatuses.MOVEDBACK.name());
		
		teamDAO.saveTeam("A team");
		teamDAO.saveTeam("B team");
		teamDAO.saveTeam("C team");
		
		
		userDAO.saveUser("oturk", "oturk");
		userDAO.saveUser("sys", "sys");
		Team bTeam = teamDAO.findTeam("B team");
		teamDAO.addUser(bTeam.getId(), "sys");
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
		
		projectDAO.saveProject("First projet", "Some important project");
		
		Task task1 = taskDAO.findTask("first task");
		Task task2 = taskDAO.findTask("second task");
		Task task3 = taskDAO.findTask("third task");
		Project proj = projectDAO.findProject("First projet");
		
		taskDAO.saveTask(proj.getId(), "first task", "This is my first task", 8, 1);
		taskDAO.saveTask(proj.getId(), "second task", "This is my second task", 8, 1);
		taskDAO.saveTask(proj.getId(), "third task", "This is my third task", 8, 1);
//		
//		projectDAO.addTask(proj.getId(), task1.getId());
//		projectDAO.addTask(proj.getId(), task2.getId());
//		projectDAO.addTask(proj.getId(), task3.getId());
		
		User user = userDAO.findUser("oturk");
		UserDataDO data = new UserDataDO();
		data.setEmail("oturk@email.hu");
		data.setSurname("Ottó");
		data.setForename("Türk");
		userDAO.updateUserData(1L, data);
		Role role = roleDAO.findRole(Roles.ADMIN.name());
		Role owner = roleDAO.findRole(Roles.OWNER.name());
		Team team = teamDAO.findTeam("A team");
		
		Project project = projectDAO.findProject("First projet");
		Task task = taskDAO.findTask("first task");
		sprintDAO.startSprint(project.getId());
		sprintDAO.addTask(project.getId(), task.getId());
		
		userDAO.addRole(user.getId(), role.getId());
		userDAO.addRole(user.getId(), owner.getId());
		projectDAO.addOwner("oturk", project.getId());
		projectDAO.addTeam(team.getId(), project.getId());
		teamDAO.addUser(team.getId(), "oturk");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
