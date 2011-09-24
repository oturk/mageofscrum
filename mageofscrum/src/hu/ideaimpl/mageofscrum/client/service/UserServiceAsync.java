package hu.ideaimpl.mageofscrum.client.service;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.Range;

public interface UserServiceAsync {
	void requestRows(Range range, AsyncCallback<ArrayList<User>> callback);
}
