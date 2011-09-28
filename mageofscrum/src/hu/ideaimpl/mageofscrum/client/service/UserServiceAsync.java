package hu.ideaimpl.mageofscrum.client.service;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.shared.UserDetails;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.Range;

public interface UserServiceAsync {
	void requestRows(int start, AsyncCallback<ArrayList<UserDetails>> callback);
}
