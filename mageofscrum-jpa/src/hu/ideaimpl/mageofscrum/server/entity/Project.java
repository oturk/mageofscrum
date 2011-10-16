package hu.ideaimpl.mageofscrum.server.entity;

import hu.ideaimpl.mageofscrum.shared.ProjectDO;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	@ManyToOne
	@JoinTable(name="OWNER_PROJECTS",joinColumns = @JoinColumn(name="PROJECT_ID"), inverseJoinColumns = @JoinColumn(name="OWNER_ID"))
//	@JoinColumn(name = "USER_FK")
	private User user;
	@ManyToOne
	@JoinTable(name="TEAMS_PROJECTS",joinColumns = @JoinColumn(name="PROJECT_ID"), inverseJoinColumns = @JoinColumn(name="TEAM_ID"))
	private Team team;
	
	public static Project convertToProjectObj(ProjectDO data){
		Project result = new Project();
		result.setId(data.getId());
		result.setName(data.getName());
		result.setDescription(data.getDescription());
		
		return result;
	}

	public Project() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public ProjectDO getProjectDO(){
		ProjectDO result = new ProjectDO();
		result.setId(this.id);
		result.setName(this.name);
		result.setDescription(this.description != null ? this.description : "");
		result.setOwnerName(this.user.getUsername());
		result.setTeamName(this.team.getName());
		return result;
	}

}
