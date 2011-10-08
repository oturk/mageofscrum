package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.presenter.MainPresenter;
import hu.ideaimpl.mageofscrum.client.service.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.ui.Header;
import hu.ideaimpl.mageofscrum.client.ui.MenuBar;
import hu.ideaimpl.mageofscrum.client.user.LoginForm2;
import hu.ideaimpl.mageofscrum.client.view.ErrorView;
import hu.ideaimpl.mageofscrum.client.view.ProfileView;
import hu.ideaimpl.mageofscrum.client.view.RolesView;
import hu.ideaimpl.mageofscrum.client.view.TeamsView;
import hu.ideaimpl.mageofscrum.client.view.UsersView;
import hu.ideaimpl.mageofscrum.client.view.WelcomeView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
	public static class Util {
		private static ClientFactory factory = null;

		public static ClientFactory getClientFactory() {
			if (factory == null) {
				factory = GWT.create(ClientFactory.class);
			}
			return factory;
		}
	}

	public TeamsView getTeamsView();

	public HandlerManager getEventBus();

	public HasWidgets getContainer();

	public SecurityServiceAsync getSecurityService();

	public MainPresenter.Display getMainView();

	public HasWidgets getContentPanel();

	// Places@Activities
	public PlaceController getPlaceController();

	public EventBus getEBus();

	public WelcomeView getWelcomeView();

	public ErrorView getErrorView();

	public MenuBar getMenuBar();
	
	public MageOfScrumApp getMageOfScrumApp();
	
	public Header getHeader();
	
	public LoginForm2 getLoginForm();
	
	public RolesView getRoleView();
	
	public UsersView getUsersView();
	
	public ProfileView getProfileView();
}
