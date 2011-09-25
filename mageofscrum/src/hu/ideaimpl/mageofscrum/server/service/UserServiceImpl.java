package hu.ideaimpl.mageofscrum.server.service;

import hu.ideaimpl.mageofscrum.client.service.UserService;
import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.entity.UserData;
import hu.ideaimpl.mageofscrum.shared.Role;
import hu.ideaimpl.mageofscrum.shared.Team;
import hu.ideaimpl.mageofscrum.shared.UserDetails;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet(urlPatterns="/UserService")
public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<UserDetails> requestRows(int range) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from User");
		List<hu.ideaimpl.mageofscrum.server.entity.User> users = query.list();
		
		return convertToUser(users);
	}
	
	private ArrayList<UserDetails> convertToUser(List<hu.ideaimpl.mageofscrum.server.entity.User> users){
		ArrayList<UserDetails> result = new ArrayList<UserDetails>();
		for(hu.ideaimpl.mageofscrum.server.entity.User user : users){
			UserDetails newUser= new UserDetails();
			newUser.setEmail(user.getEmail());
			UserData data = user.getData();
			if(data != null){
				newUser.setFullName(data.getForeName() +" "+ data.getSureName());
				newUser.setAddress(data.getZone() != null ? data.getZone()+" " : ""+
				data.getCity() != null ? data.getCity()+" " : "" +
				data.getAddress() != null ? data.getAddress()+" " :"" );
				newUser.setNickname(data.getNickname() != null ? data.getNickname() : "");
			}
			List<hu.ideaimpl.mageofscrum.server.entity.Team> teams = user.getTeams();
			List<Team> detTeams = new ArrayList<Team>();
			for(hu.ideaimpl.mageofscrum.server.entity.Team team : teams){
				Team t = new Team();
				t.setId(team.getId());
				t.setName(team.getName());
				detTeams.add(t);
			}
			newUser.setTeams(detTeams);
			
			List<hu.ideaimpl.mageofscrum.server.entity.Role> roles = user.getRoles();
			List<Role> detRoles = new ArrayList<Role>();
			for(hu.ideaimpl.mageofscrum.server.entity.Role role : roles){
				Role t = new Role();
				t.setId(role.getId());
				t.setName(role.getName());
				detRoles.add(t);
			}
			newUser.setRoles(detRoles);
			result.add(newUser);
		}
		
		return result;
	}

}
