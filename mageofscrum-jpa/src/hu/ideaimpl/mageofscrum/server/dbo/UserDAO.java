package hu.ideaimpl.mageofscrum.server.dbo;

import hu.ideaimpl.mageofscrum.server.entity.User;

import java.util.List;

public interface UserDAO {
	public void saveUser(String username, String password);
	public void updateUser(User user);
	public void deleteUser(String username);
	public User findUser(String username);
	public List<User> listAll(String username);
	
	public void addRole(Long userId, Long roleId);
	public void addProject(String username, Long projectId);
}
