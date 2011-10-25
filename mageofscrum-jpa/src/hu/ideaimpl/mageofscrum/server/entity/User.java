package hu.ideaimpl.mageofscrum.server.entity;

import hu.ideaimpl.mageofscrum.shared.UserDO;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@ManyToMany
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private List<Role> roles;
	@OneToOne
	private UserData data;
	@ManyToMany(mappedBy = "users")
//	@JoinTable(name = "USER_TEAMS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "TEAM_ID") })
	private List<Team> teams;
	@OneToMany(mappedBy = "user")
	private List<Project> projects;

	public User() {
	}
	
	public static User createUserObj(UserDO user){
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		
		if(user.getUserData() != null){
			UserDataDO data = user.getUserData();
			UserData userData = new UserData();
			userData.setEmail(data.getEmail());
			userData.setSurname(data.getSurname());
			userData.setForename(data.getForename());
			
			newUser.setData(userData);
		}
		
		return newUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if(roles == null){
			roles = new ArrayList<Role>();
		}
		roles.add(role);
	}

	public UserData getData() {
		return data;
	}

	public void setData(UserData data) {
		this.data = data;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public UserDO getUserDO(){
		UserDO ret = new UserDO();
		ret.setUsername(username);
		return ret;
	}
	
	public UserDataDO getUserDataDO(){
		UserDataDO dataDO = new UserDataDO();
		if(data != null){
			dataDO.setEmail(data.getEmail());
			dataDO.setSurname(data.getSurname());
			dataDO.setForename(data.getForename());
		}
		return dataDO;
	}
	
	public List<Long> getRoleIds(){
		List<Long> result = new ArrayList<Long>();
		for(Role role : roles){
			result.add(role.getId());
		}
		return result;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public void addTeam(Team team){
		if(teams == null){
			teams = new ArrayList<Team>();
		}
		teams.add(team);
	}
	
	public void addProject(Project project){
		if(projects == null){
			projects = new ArrayList<Project>();
		}
		projects.add(project);
	}
	
	public void removeTeam(Team team){
		teams.remove(team);
	}
	
	public void removeRole(Role role){
		roles.remove(role);
	}

}
