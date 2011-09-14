package hu.ideaimpl.mageofscrum.server.security;

import hu.ideaimpl.mageofscrum.client.security.SecurityService;
import hu.ideaimpl.mageofscrum.server.auth.MosRealm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns="/SecurityService")
public class SecurityServiceImpl extends RemoteServiceServlet implements
		SecurityService {
	private static final long serialVersionUID = 1L;
	
	Subject currentUser = null;
	
	@Override
	public void init() throws ServletException {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager(new MosRealm());
		manager.setSessionMode(DefaultWebSecurityManager.NATIVE_SESSION_MODE);
		SecurityUtils.setSecurityManager(manager);
		
		currentUser = SecurityUtils.getSubject();
		
		super.init();
	}

	@Override
	public boolean loginUser(String email, String password, boolean rememberMe) {
		UsernamePasswordToken token = new UsernamePasswordToken(email, password,rememberMe);
		
		currentUser.login(token);
		System.out.println("SID: "+SecurityUtils.getSubject().getSession().getId());
		return SecurityUtils.getSubject().isAuthenticated();
	}

	@Override
	public boolean logoutUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkHasRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAuthenticated() {
		return currentUser.isAuthenticated();
	}

	@Override
	public String getEmail() {
		return (String) currentUser.getPrincipal();
	}

}
