package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.entity.Task;

public interface TaskDAO {
	public Task findTask(String name);
	public void saveTask(String name, String desc, int estTime, int priority);
}
