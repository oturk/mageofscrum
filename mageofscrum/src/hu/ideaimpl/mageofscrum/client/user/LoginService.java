package hu.ideaimpl.mageofscrum.client.user;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../LoginService")
public interface LoginService extends RemoteService {
	public String getPassword(String email);
}
