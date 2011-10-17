package hu.ideaimpl.mageofscrum.server.dao;

import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.User;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.util.List;

public interface UserDAO {
	public void saveUser(String username, String password);
	public void updateUser(User user);
	public void updateUserData(Long userId, UserDataDO data);
	public void deleteUser(String username);
	public User findUser(String username);
	public List<User> listAll(String username);
	
	public void addRole(Long userId, Long roleId);
	public void removeRole(Long userId, Long roleId);
	public void addProject(String username, Long projectId);
	public void changePassword(Long userId, String password);
	public List<Project> listUsersProjects(Long userId);
}
