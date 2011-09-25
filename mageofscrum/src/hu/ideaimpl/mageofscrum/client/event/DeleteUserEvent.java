package hu.ideaimpl.mageofscrum.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DeleteUserEvent extends GwtEvent<DeleteUserEventHandler> {
	private static final Type<DeleteUserEventHandler> TYPE = new Type<DeleteUserEventHandler>();
	private final Long userId;

	public DeleteUserEvent(Long userId) {
		this.userId = userId;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DeleteUserEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DeleteUserEventHandler handler) {
		handler.doOnDeleteUser(this);
	}

	public Long getUserId() {
		return userId;
	}

}
