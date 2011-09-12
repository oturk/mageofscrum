package hu.ideaimpl.mageofscrum.server;

import javax.servlet.annotation.WebServlet;

import hu.ideaimpl.mageofscrum.client.user.LoginService;
import hu.ideaimpl.mageofscrum.server.auth.MosRealm;
import hu.ideaimpl.mageofscrum.server.entity.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns="/LoginService")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	private static final long serialVersionUID = 1L;

//	@Override
//	public boolean getUserSID(String email, String password) {
//		Session session = HibernateUtil.getSession();
//		User user = (User) session.get(User.class, email);
//		if(user != null && user.getPassword().equals(password)){
//			return true;
//		}
//		return false;
//	}
	
	@Override
	public boolean getUserSID(String email, String password) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager(new MosRealm());
		manager.setSessionMode(DefaultWebSecurityManager.NATIVE_SESSION_MODE);
		UsernamePasswordToken token = new UsernamePasswordToken(email, password);
		SecurityUtils.setSecurityManager(manager);
		
		SecurityUtils.getSubject().login(token);
		System.out.println("SID: "+SecurityUtils.getSubject().getSession().getId());
		return SecurityUtils.getSubject().isAuthenticated();
	}

}
