package hu.ideaimpl.mageofscrum.client.security;

import hu.ideaimpl.mageofscrum.shared.Roles;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SecurityServiceAsync {

	void loginUser(String email, String password, boolean rememberMe,
			AsyncCallback<Boolean> callback);

	void logoutUser(AsyncCallback<Void> callback);

	void checkHasRole(String role, AsyncCallback<Boolean> callback);

	void isAuthenticated(AsyncCallback<Boolean> callback);

	void getEmail(AsyncCallback<String> callback);

	void forgotPassword(String email, AsyncCallback<Boolean> callback);

	void getRole(AsyncCallback<Roles> callback);
}
