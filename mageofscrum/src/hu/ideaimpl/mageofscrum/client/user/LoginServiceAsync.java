package hu.ideaimpl.mageofscrum.client.user;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void getUserSID(String email, String password,
			AsyncCallback<Boolean> callback);
}
