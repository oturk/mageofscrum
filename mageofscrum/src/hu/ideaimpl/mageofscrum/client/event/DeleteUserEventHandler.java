package hu.ideaimpl.mageofscrum.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface DeleteUserEventHandler extends EventHandler {
	void doOnDeleteUser(DeleteUserEvent event);
}
