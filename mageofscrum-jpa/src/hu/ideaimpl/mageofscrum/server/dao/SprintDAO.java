package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.entity.Sprint;
import hu.ideaimpl.mageofscrum.shared.Operations;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SprintDAO {
	public void startSprint(Long projectId);
	public void stopSprint(Long projectId);
	public void addTask(Long projectId, Long taskId);
	public void removeTask(Long projectId, Long taskId);
	
	public Sprint findActualSprint(Long projectId);
	public boolean hasActiveSprint(Long projectId);
	public void reportTime(Long projectId, Long taskId, int time, Date date);
	
	public void logHistory(Long projectId, Operations operation, int time, Date date);
	public Map<String, Integer> getHistory(Long projectId);
	public List<Sprint> fetchSprints(Long sprintId);
}
