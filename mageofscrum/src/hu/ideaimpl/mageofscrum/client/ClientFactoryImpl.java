package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.presenter.MainPresenter.Display;
import hu.ideaimpl.mageofscrum.client.service.SecurityService;
import hu.ideaimpl.mageofscrum.client.service.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.view.MainView;
import hu.ideaimpl.mageofscrum.client.view.TeamsView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class ClientFactoryImpl implements ClientFactory {
	private static final TeamsView teamsView = new TeamsView();
	private static final HandlerManager eventBus = new HandlerManager(null);
	private static final HasWidgets container = RootLayoutPanel.get();
	private static final SecurityServiceAsync securityService = GWT.create(SecurityService.class);
	private static final MainView mainView = new MainView();
	private static final HasWidgets contentPanel = mainView.getContentPanel();
	
	@Override
	public TeamsView getTeamsView() {
		return teamsView;
	}

	@Override
	public HandlerManager getEventBus() {
		return eventBus;
	}

	@Override
	public HasWidgets getContainer() {
		return container;
	}

	@Override
	public SecurityServiceAsync getSecurityService() {
		return securityService;
	}

	@Override
	public HasWidgets getContentPanel() {
		return mainView.getContentPanel();
	}

	@Override
	public Display getMainView() {
		return mainView;
	}

}
