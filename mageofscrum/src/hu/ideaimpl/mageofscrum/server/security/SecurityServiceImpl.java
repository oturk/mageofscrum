package hu.ideaimpl.mageofscrum.server.security;

import hu.ideaimpl.mageofscrum.client.security.SecurityService;
import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.User;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns="/SecurityService")
public class SecurityServiceImpl extends RemoteServiceServlet implements
		SecurityService {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean loginUser(String email, String password, boolean rememberMe) {
		UsernamePasswordToken token = new UsernamePasswordToken(email, password,rememberMe);
		SecurityUtils.getSubject().login(token);
		return SecurityUtils.getSubject().isAuthenticated();
	}

	@Override
	public void logoutUser() {
		SecurityUtils.getSubject().logout();
	}

	@Override
	public boolean checkHasRole(String role) {
		// TODO Auto-generated method stub
		return false;
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
		Session session = HibernateUtil.getSession();
		User user = (User) session.get(User.class, email);
		//TODO emailt küldeni a passwordrõl
		if(user != null){
			return true;
		}
		return false;
	}

}
