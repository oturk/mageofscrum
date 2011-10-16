package hu.ideaimpl.mageofscrum.server.dbo;

import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.User;

import java.util.List;

public interface RoleDAO {
	public void saveRole(String name, String description);
	public void updateRole(Role role);
	public Role findRole(String name);
	public Role findRole(Long id);
	public List<User> listUsersByRole(String role);
	public List<User> listOwners();
	public List<Role> listAll();
	public List<Role> listInverse(List<Long> roles);
	
	public List<Role> listOtherRoles(Long userId);
}
