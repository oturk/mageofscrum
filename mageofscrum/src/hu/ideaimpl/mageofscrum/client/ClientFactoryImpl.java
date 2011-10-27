package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.Header;
import hu.ideaimpl.mageofscrum.client.ui.MenuBar;
import hu.ideaimpl.mageofscrum.client.ui.forms.LoginForm;
import hu.ideaimpl.mageofscrum.client.view.BacklogView;
import hu.ideaimpl.mageofscrum.client.view.DiagnoseView;
import hu.ideaimpl.mageofscrum.client.view.HasInitState;
import hu.ideaimpl.mageofscrum.client.view.ProfileView;
import hu.ideaimpl.mageofscrum.client.view.ProjectsView;
import hu.ideaimpl.mageofscrum.client.view.RolesView;
import hu.ideaimpl.mageofscrum.client.view.SprintView;
import hu.ideaimpl.mageofscrum.client.view.TeamsView;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientFactoryImpl implements ClientFactory {
	private static TeamsView teamsView;
	private static HasWidgets container;
	private EventBus eBus = new SimpleEventBus();
	private PlaceController placeController = new PlaceController(eBus);
	private static MenuBar menuBar;
	private static Header header;
	private static LoginForm loginForm;
	private static RolesView roleView;
	private static ProfileView profileView;
	private static ProjectsView projectView;
	private static BacklogView backlogView;
	private static SprintView sprintView;
	private static DiagnoseView diagnoseView;

	@Override
	public TeamsView getTeamsView() {
		if (teamsView == null) {
			teamsView = new TeamsView();
		}
		runInitState(teamsView);
		return teamsView;
	}

	@Override
	public HasWidgets getContainer() {
		if (container == null) {
			container = RootLayoutPanel.get();
		}
		return container;
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
		if (menuBar == null) {
			menuBar = new MenuBar();
		}

		menuBar.updateMenuBar();
		return menuBar;
	}

	@Override
	public MageOfScrumApp getMageOfScrumApp() {
		return new MageOfScrumApp(new MageOfScrumShell());
	}

	@Override
	public Header getHeader() {
		if (header == null) {
			header = new Header(Resources.instance.mageofscrumLogo());
		}
		return header;
	}

	@Override
	public LoginForm getLoginForm() {
		if (loginForm == null) {
			loginForm = new LoginForm();
		}
		return loginForm;
	}

	@Override
	public RolesView getRoleView() {
		if (roleView == null) {
			roleView = new RolesView();
		}
		runInitState(roleView);
		return roleView;
	}

	@Override
	public ProfileView getProfileView() {
		if (profileView == null) {
			profileView = new ProfileView();
		}
		runInitState(profileView);
		return profileView;
	}

	@Override
	public ProjectsView getProjectsView() {
		if (projectView == null) {
			projectView = new ProjectsView();
		}
		runInitState(projectView);
		return projectView;
	}

	@Override
	public BacklogView getBacklogView() {
		if (backlogView == null) {
			backlogView = new BacklogView();
		}
		runInitState(backlogView);
		return backlogView;
	}

	@Override
	public SprintView getSprintView() {
		if (sprintView == null) {
			sprintView = new SprintView();
		}
		runInitState(sprintView);
		return sprintView;
	}

	@Override
	public DiagnoseView getDiagnoseView() {
		if (diagnoseView == null) {
			diagnoseView = new DiagnoseView();
		}
		runInitState(diagnoseView);
		return diagnoseView;
	}

	private void runInitState(Object obj) {
		if (obj instanceof HasInitState) {
			((HasInitState) obj).initState();
		}
	}

}
