package hu.ideaimpl.mageofscrum.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RoleDO implements IsSerializable {
	
	private Long id;
	private String name;

	public RoleDO() {
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
