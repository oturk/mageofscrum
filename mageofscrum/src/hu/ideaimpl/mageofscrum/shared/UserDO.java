package hu.ideaimpl.mageofscrum.shared;

import java.io.Serializable;

public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	public UserDO(String email) {
		this.email = email;
	}
	
	public UserDO() {
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
