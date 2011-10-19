package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.entity.Sprint;

public interface SprintDAO {
	public void startSprint(Long projectId);
	public void stopSprint(Long projectId);
	public void addTask(Long projectId, Long taskId);
	
	public Sprint findActualSprint(Long projectId);
	public boolean hasActiveSprint(Long projectId);
}
