package hu.ideaimpl.mageofscrum.server.crud;

import org.junit.Test;

import hu.ideaimpl.mageofscrum.server.dao.ProjectDAO;
import hu.ideaimpl.mageofscrum.server.dao.ProjectDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.SprintDAO;
import hu.ideaimpl.mageofscrum.server.dao.SprintDAOImpl;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAO;
import hu.ideaimpl.mageofscrum.server.dao.TaskDAOImpl;
import hu.ideaimpl.mageofscrum.server.entity.Project;

public class OneToManyTest {

	private ProjectDAO projectDAO = new ProjectDAOImpl();
	private TaskDAO taskDAO = new TaskDAOImpl();
	private SprintDAO sprintDAO = new SprintDAOImpl();
	
	@Test
	public void createProject(){
		projectDAO.saveProject("projet_15", "Some important project");
	}
	
	@Test
	public void createTask(){
		Project proj = projectDAO.findProject("projet_15");
		taskDAO.saveTask(proj.getId(), "first task", "This is my first task", 8, 1);
	}
	
	@Test
	public void createSprint(){
		Long taskId = taskDAO.findTask("first task").getId();
		Project proj = projectDAO.findProject("projet_15");
		sprintDAO.startSprint(proj.getId());
		sprintDAO.addTask(proj.getId(), taskId);
	}
}
