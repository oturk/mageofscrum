package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.service.SecurityService;
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
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientFactoryImpl implements ClientFactory {
	private static final TeamsView teamsView = new TeamsView();
	private static final HasWidgets container = RootLayoutPanel.get();
	private static final SecurityServiceAsync securityService = GWT.create(SecurityService.class);
	private EventBus eBus = new SimpleEventBus();
	private PlaceController placeController = new PlaceController(eBus);
	private static final MenuBar menuBar = new MenuBar();
	private static final Header header = new Header(Resources.instance.mageofscrumLogo());
	private static final LoginForm loginForm = new LoginForm();
	private static final RolesView roleView = new RolesView();
	private static final ProfileView profileView = new ProfileView();
	private static final ProjectsView projectView = new ProjectsView();
	private static final BacklogView backlogView = new BacklogView();
	private static final SprintView sprintView = new SprintView();
	private static final DiagnoseView diagnoseView = new DiagnoseView();

	@Override
	public TeamsView getTeamsView() {
		return teamsView;
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
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public EventBus getEBus() {
		return eBus;
	}

	@Override
	public MenuBar getMenuBar() {
		menuBar.updateMenuBar();
		return menuBar;
	}

	@Override
	public MageOfScrumApp getMageOfScrumApp() {
		return new MageOfScrumApp(new MageOfScrumShell());
	}

	@Override
	public Header getHeader() {
		return header;
	}

	@Override
	public LoginForm getLoginForm() {
		return loginForm;
	}

	@Override
	public RolesView getRoleView() {
		return roleView;
	}

	@Override
	public ProfileView getProfileView() {
		return profileView;
	}

	@Override
	public ProjectsView getProjectsView() {
		return projectView;
	}

	@Override
	public BacklogView getBacklogView() {
		return backlogView;
	}

	@Override
	public SprintView getSprintView() {
		return sprintView;
	}

	@Override
	public DiagnoseView getDiagnoseView() {
		return diagnoseView;
	}

}
