package hu.ideaimpl.mageofscrum.server.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import hu.ideaimpl.mageofscrum.server.dbo.ProjectDAOImpl;
import hu.ideaimpl.mageofscrum.server.dbo.RoleDAOImpl;
import hu.ideaimpl.mageofscrum.server.dbo.TeamDAOImpl;
import hu.ideaimpl.mageofscrum.server.dbo.UserDAOImpl;
import hu.ideaimpl.mageofscrum.server.entity.Project;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.Team;
import hu.ideaimpl.mageofscrum.server.entity.User;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitTest {
	private static TeamDAOImpl teamDBO = new TeamDAOImpl();
	private static UserDAOImpl userDBO = new UserDAOImpl();
	private static ProjectDAOImpl projectDBO = new ProjectDAOImpl();
	private static RoleDAOImpl roleDBO = new RoleDAOImpl();
	Logger log = LoggerFactory.getLogger(InitTest.class);

	@BeforeClass
	public static void beforeClass(){
		roleDBO.saveRole("admin", "Has all privileges");
		roleDBO.saveRole("owner", "Has almost all privileges");
		roleDBO.saveRole("master", "Has some privileges");
		
		teamDBO.saveTeam("A team");
		teamDBO.saveTeam("B team");
		teamDBO.saveTeam("B team");
		
		userDBO.saveUser("oturk", "oturk");
		
		projectDBO.saveProject("First projet", "Some important project");
		
		User user = userDBO.findUser("oturk");
		Role role = roleDBO.findRole("admin");
		Team team = teamDBO.findTeam("A team");
		
		Project project = projectDBO.findProject("First projet");
		userDBO.addRole(user.getId(), role.getId());
		projectDBO.addOwner("oturk", project.getId());
		projectDBO.addTeam(team.getId(), project.getId());
		teamDBO.addUser(team.getId(), "oturk");
	}
	
	@Test
	public void testInitRoles(){
		assertNotNull(roleDBO.findRole("admin"));
		assertNotNull(roleDBO.findRole("owner"));
		assertNotNull(roleDBO.findRole("master"));
	}
	
	@Test
	public void testAdminUser(){
		User user = userDBO.findUser("oturk");
		assertNotNull(user);
		assertEquals("admin", user.getRoles().get(0).getName());
	}
	
	@Test
	public void testATeam(){
		Team team = teamDBO.findTeam("A team");
		assertNotNull(team);
		assertEquals("oturk", team.getUsers().get(0).getUsername());
	}
	
	@Test
	public void testProject(){
		Project project = projectDBO.findProject("First projet");
		assertNotNull(project);
		assertEquals("oturk", project.getUser().getUsername());
		assertEquals("A team", project.getTeam().getName());
	}
}
