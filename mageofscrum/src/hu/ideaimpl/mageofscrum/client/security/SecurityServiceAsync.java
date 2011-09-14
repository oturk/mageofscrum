package hu.ideaimpl.mageofscrum.client.security;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SecurityServiceAsync {

	void loginUser(String email, String password, boolean rememberMe,
			AsyncCallback<Boolean> callback);

	void logoutUser(AsyncCallback<Boolean> callback);

	void checkHasRole(String role, AsyncCallback<Boolean> callback);

	void isAuthenticated(AsyncCallback<Boolean> callback);

	void getEmail(AsyncCallback<String> callback);
}
