package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.view.ErrorView;
import hu.ideaimpl.mageofscrum.shared.Errors;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ErrorActivity extends AbstractActivity {

	private final ClientFactory clientFactory;
	private Errors error;
	
	public ErrorActivity(Errors error, ClientFactory clientFactory) {
		this.error = error;
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ErrorView errorView = clientFactory.getErrorView();
		errorView.setError(error);
		panel.setWidget(errorView);
	}

}
