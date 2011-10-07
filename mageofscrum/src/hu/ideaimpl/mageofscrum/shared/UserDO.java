package hu.ideaimpl.mageofscrum.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserDO implements IsSerializable {
	
	private String email;
	private String password;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
