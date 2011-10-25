package hu.ideaimpl.mageofscrum.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserDO implements IsSerializable {

	private String username;
	private String password;
	private UserDataDO userData;

	public UserDO(String email) {
		this.username = email;
	}

	public UserDO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String email) {
		this.username = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDataDO getUserData() {
		return userData;
	}

	public void setUserData(UserDataDO userData) {
		this.userData = userData;
	}

}
