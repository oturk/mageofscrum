package hu.ideaimpl.mageofscrum.client.security;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../SecurityService")
public interface SecurityService extends RemoteService {
	public boolean loginUser(String email, String password, boolean rememberMe);
	public void logoutUser();
	public boolean checkHasRole(String role);
	public boolean isAuthenticated();
	public String getEmail();
	public boolean forgotPassword(String email);
}
