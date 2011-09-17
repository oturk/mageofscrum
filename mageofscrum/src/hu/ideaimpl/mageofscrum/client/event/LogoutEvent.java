package hu.ideaimpl.mageofscrum.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LogoutEvent extends GwtEvent<LogoutHandler> {
	public static Type<LogoutHandler> TYPE = new Type<LogoutHandler>();

	@Override
	public GwtEvent.Type<LogoutHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LogoutHandler handler) {
		handler.onLoggedOut(this);
	}

}
