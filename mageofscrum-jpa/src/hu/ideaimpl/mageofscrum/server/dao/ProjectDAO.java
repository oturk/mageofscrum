package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Task;

import java.util.List;

public interface ProjectDAO {
	public void saveProject(String name, String description);
	public void updateProject(Long id, Long ownerId, Long teamId, String name, String description);
	public Project findProject(String name);
	public Project findProject(Long id);
	public void deleteProject(Long id);
	public List<Project> listAll();
	public List<Project> listUsersProjects(String username);
	
	public void addOwner(String username, Long projectId);
	public void addTeam(Long teamId, Long projectId);
	public void addTask(Long projectId, Long taskId);
}
