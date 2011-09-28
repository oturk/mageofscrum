package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.presenter.MainPresenter;
import hu.ideaimpl.mageofscrum.client.service.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.view.TeamsView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public interface ClientFactory {
	public TeamsView getTeamsView();
	public HandlerManager getEventBus();
	public HasWidgets getContainer();
	public SecurityServiceAsync getSecurityService();
	public MainPresenter.Display getMainView();
	public HasWidgets getContentPanel();
}
