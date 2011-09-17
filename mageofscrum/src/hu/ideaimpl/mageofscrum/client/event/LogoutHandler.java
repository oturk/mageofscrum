package hu.ideaimpl.mageofscrum.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LogoutHandler extends EventHandler {
	void onLoggedOut(LogoutEvent event);
}
