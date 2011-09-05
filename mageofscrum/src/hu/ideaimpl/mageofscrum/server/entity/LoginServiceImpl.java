package hu.ideaimpl.mageofscrum.server.entity;

import javax.servlet.annotation.WebServlet;

import hu.ideaimpl.mageofscrum.client.user.LoginService;
import hu.ideaimpl.mageofscrum.server.HibernateUtil;

import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns="/LoginService")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	private static final long serialVersionUID = 1L;

	@Override
	public String getPassword(String email) {
		System.out.println("get user password");
		Session session = HibernateUtil.getSession();
		User user = (User) session.get(User.class, email);
		if(user != null){
			return user.getPassword();
		}
		return null;
	}

}
