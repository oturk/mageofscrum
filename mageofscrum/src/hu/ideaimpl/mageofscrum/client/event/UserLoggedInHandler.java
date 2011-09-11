package hu.ideaimpl.mageofscrum.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface UserLoggedInHandler extends EventHandler{
	void onUserLoggedIn(UserLoggedInEvent event);
}
