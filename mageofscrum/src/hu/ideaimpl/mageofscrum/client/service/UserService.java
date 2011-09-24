package hu.ideaimpl.mageofscrum.client.service;

import hu.ideaimpl.mageofscrum.shared.User;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.view.client.Range;

@RemoteServiceRelativePath("../UserService")
public interface UserService extends RemoteService {
	public static class Util{
		private static UserServiceAsync service = null;
		public static UserServiceAsync getService(){
			if(service == null){
				service = GWT.create(UserService.class);
			}
			return service;
		}
	}
	
	public ArrayList<User> requestRows(Range range);
}
