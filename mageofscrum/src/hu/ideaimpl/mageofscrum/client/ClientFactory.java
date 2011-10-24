package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.service.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.ui.Header;
import hu.ideaimpl.mageofscrum.client.ui.MenuBar;
import hu.ideaimpl.mageofscrum.client.ui.forms.LoginForm;
import hu.ideaimpl.mageofscrum.client.view.BacklogView;
import hu.ideaimpl.mageofscrum.client.view.DiagnoseView;
import hu.ideaimpl.mageofscrum.client.view.ProfileView;
import hu.ideaimpl.mageofscrum.client.view.ProjectsView;
import hu.ideaimpl.mageofscrum.client.view.RolesView;
import hu.ideaimpl.mageofscrum.client.view.SprintView;
import hu.ideaimpl.mageofscrum.client.view.TeamsView;

import com.google.gwt.core.client.GWT;
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

	public HasWidgets getContainer();

	public SecurityServiceAsync getSecurityService();

	// Places@Activities
	public PlaceController getPlaceController();

	public EventBus getEBus();

	public MenuBar getMenuBar();
	
	public MageOfScrumApp getMageOfScrumApp();
	
	public Header getHeader();
	
	public LoginForm getLoginForm();
	
	public RolesView getRoleView();
	
	public ProfileView getProfileView();
	
	public ProjectsView getProjectsView();
	
	public BacklogView getBacklogView();
	
	public SprintView getSprintView();
	
	public DiagnoseView getDiagnoseView();
}
