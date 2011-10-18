package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.entity.Task;

public interface TaskDAO {
	public Task findTask(String name);
	public void saveTask(Long projectId, String name, String desc, int estTime, int priority);
	public void updateTask(Long id, String name, int estTime, int priority, String description);
	public void changeStatus(String status);
	public void deleteTask(Long id);
}
