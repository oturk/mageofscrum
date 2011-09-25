package hu.ideaimpl.mageofscrum.shared;

import java.io.Serializable;

public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	public Team() {
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

}
