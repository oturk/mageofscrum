package hu.ideaimpl.mageofscrum.server.entity;

import hu.ideaimpl.mageofscrum.shared.RoleDO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ROLE_ID")
	private Long id;
	private String name;
	private String description;
	
	public Role() {
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
	public RoleDO getRoleDO(){
		RoleDO role = new RoleDO();
		role.setId(id);
		role.setName(name);
		return role;
	}
}
