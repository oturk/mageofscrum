package hu.ideaimpl.mageofscrum.server.service;

import hu.ideaimpl.mageofscrum.client.service.UserService;
import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.shared.User;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.view.client.Range;

@WebServlet(urlPatterns="/UserService")
public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<User> requestRows(Range range) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from User");
		List<hu.ideaimpl.mageofscrum.server.entity.User> users = query.list();
		
		return convertToUser(users);
	}
	
	private ArrayList<User> convertToUser(List<hu.ideaimpl.mageofscrum.server.entity.User> users){
		ArrayList<User> result = new ArrayList<User>();
		for(hu.ideaimpl.mageofscrum.server.entity.User user : users){
			User newUser= new User();
			newUser.setEmail(user.getEmail());
			result.add(newUser);
		}
		
		return result;
	}

}
