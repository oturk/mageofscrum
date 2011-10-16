package hu.ideaimpl.mageofscrum.server.dbo;

import hu.ideaimpl.mageofscrum.server.entity.Team;

import java.util.List;

public interface TeamDAO {
	public void saveTeam(String name);
	public void updateTeam(Team team);
	public Team findTeam(String name);
	public Team findTeam(Long id);
	public void deleteTeam(String name);
	public void deleteTeam(Long id);
	public List<Team> listAll();
	
	public void addUser(Long teamId, String username);
}
