package hu.ideaimpl.mageofscrum.server.dbo;

public interface TaskDAO {
	public void saveTask(String name, String desc, int estTime, int priority);
}
