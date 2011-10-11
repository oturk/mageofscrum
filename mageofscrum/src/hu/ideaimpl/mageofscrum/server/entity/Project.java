package hu.ideaimpl.mageofscrum.server.entity;

import hu.ideaimpl.mageofscrum.shared.ProjectDO;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	@ManyToOne
	@JoinColumn(name = "USER_FK")
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "TEAM_FK")
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
		result.setName(this.name);
		result.setDescription(this.description);
		result.setOwnerName(this.user.getUsername());
		result.setTeamName(this.team.getName());
		return result;
	}

}
