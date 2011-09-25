package hu.ideaimpl.mageofscrum.client.security;

import hu.ideaimpl.mageofscrum.shared.Roles;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../SecurityService")
public interface SecurityService extends RemoteService {
	public static class Util {
		private static SecurityServiceAsync service = null;

		public static SecurityServiceAsync getService() {
			if (service == null) {
				service = GWT.create(SecurityService.class);
			}
			return service;
		}
	}

	public boolean loginUser(String email, String password, boolean rememberMe);

	public void logoutUser();

	public boolean checkHasRole(String role);

	public boolean isAuthenticated();

	public String getEmail();

	public boolean forgotPassword(String email);
	
	public Roles getRole();
}
