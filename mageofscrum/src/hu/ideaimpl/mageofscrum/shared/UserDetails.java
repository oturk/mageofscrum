package hu.ideaimpl.mageofscrum.shared;

import java.io.Serializable;

public class UserDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String fullName;
	private String address;

	public UserDetails() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
