package hu.ideaimpl.mageofscrum.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UserLoggedInEvent extends GwtEvent<UserLoggedInHandler>{
	public static Type<UserLoggedInHandler> TYPE = new Type<UserLoggedInHandler>();
	
	@Override
	public Type<UserLoggedInHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UserLoggedInHandler handler) {
		handler.onUserLoggedIn(this);
	}

}
