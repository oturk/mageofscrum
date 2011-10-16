package hu.ideaimpl.mageofscrum.server.entity;

import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToMany
	@JoinTable(name = "USER_TEAMS", joinColumns = { @JoinColumn(name = "TEAM_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	private List<User> users;
	@OneToMany(mappedBy = "team")
	private List<Project> projects = new ArrayList<Project>();

	public Team() {
	}

	public Team(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<User> getUsers() {
		if (users == null) {
			users = new ArrayList<User>();
		}
		return users;
	}
	
	public void addProject(Project project){
		if(projects == null){
			projects = new ArrayList<Project>();
		}
		projects.add(project);
	}

	public List<String> getMembersIds() {
		List<String> ids = new ArrayList<String>();
		if (users != null) {
			for (User user : users) {
				ids.add(user.getUsername());
			}
		}
		return ids;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<UserDO> getUserDOs() {
		List<UserDO> result = new ArrayList<UserDO>();
		if (users != null) {
			for (User user : users) {
				result.add(user.getUserDO());
			}
		}
		return result;
	}

	public TeamDO getTeamDO() {
		TeamDO ret = new TeamDO();
		ret.setId(id);
		ret.setName(name);
		return ret;
	}

	public void addMember(User user) {
		if (users == null) {
			users = new ArrayList<User>();
		}
		users.add(user);
	}

	public void removeMember(User user) {
		users.remove(user);
	}

}
