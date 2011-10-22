package hu.ideaimpl.mageofscrum.server.service;

import hu.ideaimpl.mageofscrum.client.service.SecurityService;
import hu.ideaimpl.mageofscrum.shared.Roles;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns = "/SecurityService")
public class SecurityServiceImpl extends RemoteServiceServlet implements SecurityService {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean loginUser(String email, String password, boolean rememberMe) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(email, password, rememberMe);
			SecurityUtils.getSubject().login(token);
			return SecurityUtils.getSubject().isAuthenticated();
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public void logoutUser() {
		SecurityUtils.getSubject().logout();
	}

	@Override
	public boolean checkHasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}

	@Override
	public boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	@Override
	public String getEmail() {
		return (String) SecurityUtils.getSubject().getPrincipal();
	}

	@Override
	public boolean forgotPassword(String email) {
		// Session session = HibernateUtil.getSession();
		// User user = (User) session.get(User.class, email);
		// //TODO emailt küldeni a passwordrõl
		// if(user != null){
		// return true;
		// }
		return false;
	}

	@Override
	public Roles getRole() {
		Subject currentUser = SecurityUtils.getSubject();

		if (currentUser.hasRole(Roles.ADMIN.name())) {
			return Roles.ADMIN;
		} else if (currentUser.hasRole(Roles.OWNER.name())) {
			return Roles.OWNER;
		} else if (currentUser.hasRole(Roles.MASTER.name())) {
			return Roles.MASTER;
		} else if (currentUser.hasRole(Roles.USER.name())) {
			return Roles.USER;
		}

		return Roles.UNKNOWN;
	}

}
