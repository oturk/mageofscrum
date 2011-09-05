package hu.ideaimpl.mageofscrum.client.user;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void getPassword(String email, AsyncCallback<String> callback);
}
