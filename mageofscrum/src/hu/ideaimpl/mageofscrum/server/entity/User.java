package hu.ideaimpl.mageofscrum.server.entity;

import hu.ideaimpl.mageofscrum.shared.UserDO;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private List<Role> roles;
	@OneToOne(cascade = CascadeType.ALL)
	private UserData data;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_TEAMS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "TEAM_ID") })
	private List<Team> teams;

	public User() {
	}
	
	public static User createUserObj(UserDO user){
		User newUser = new User();
		newUser.setUsername(user.getEmail());
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

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
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
		ret.setEmail(userName);
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

}
