package hu.ideaimpl.mageofscrum.server.dbo;

import hu.ideaimpl.mageofscrum.server.entity.Project;

import java.util.List;

public interface ProjectDAO {
	public void saveProject(String name, String description);
	public void updateProject(Project project);
	public Project findProject(String name);
	public void deleteProject(Long id);
	public List<Project> listAll();
	public List<Project> listUsersProjects(String username);
	
	public void addOwner(String username, Long projectId);
	public void addTeam(Long teamId, Long projectId);
}
