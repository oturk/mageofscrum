package hu.ideaimpl.mageofscrum.client.ui;

import hu.ideaimpl.mageofscrum.client.MageOfScrum;
import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.shared.Roles;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuBar extends Composite {

	private ToggleButton projects = new ToggleButton("projects");
	private ToggleButton backlog = new ToggleButton("backlog");
	private ToggleButton sprint = new ToggleButton("sprint");
	private ToggleButton profile = new ToggleButton("profile");
	private ToggleButton diagnose = new ToggleButton("diagnose");
	private ToggleButton logout = new ToggleButton("logout");
	private ToggleButton teams = new ToggleButton("teams");
	private ToggleButton roles = new ToggleButton("users & roles");
	private VerticalPanel verticalPanel = new VerticalPanel();
	private ToggleButton current = null;
	private ClickHandler clickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			if (current != null) {
				current.setStyleName(Resources.instance.mosStyle().menuItem());
			}
			current = (ToggleButton) event.getSource();
			current.setStyleName(Resources.instance.mosStyle().menuItemDown());
		}
	};

	public MenuBar() {
		initWidget(verticalPanel);
		verticalPanel.setSize("150px", "200px");
		verticalPanel.setSpacing(1);

		Label label = new Label("");
		label.setHeight("40px");
		projects.setStyleName(Resources.instance.mosStyle().menuItem());
		backlog.setStyleName(Resources.instance.mosStyle().menuItem());
		sprint.setStyleName(Resources.instance.mosStyle().menuItem());
		profile.setStyleName(Resources.instance.mosStyle().menuItem());
		diagnose.setStyleName(Resources.instance.mosStyle().menuItem());
		logout.setStyleName(Resources.instance.mosStyle().menuItem());
		teams.setStyleName(Resources.instance.mosStyle().menuItem());
		roles.setStyleName(Resources.instance.mosStyle().menuItem());

		projects.addClickHandler(clickHandler);
		backlog.addClickHandler(clickHandler);
		sprint.addClickHandler(clickHandler);
		profile.addClickHandler(clickHandler);
		diagnose.addClickHandler(clickHandler);
		logout.addClickHandler(clickHandler);
		teams.addClickHandler(clickHandler);
		roles.addClickHandler(clickHandler);
		
		verticalPanel.add(label);
		verticalPanel.add(projects);
		verticalPanel.add(sprint);
		verticalPanel.add(backlog);
		verticalPanel.add(profile);
		verticalPanel.add(diagnose);
		verticalPanel.add(teams);
		verticalPanel.add(roles);
		verticalPanel.add(logout);
	}

	public void addMenuItem(Button mItem) {
		mItem.setStyleName(Resources.instance.mosStyle().menuItem());
		verticalPanel.add(mItem);
	}

	public HasClickHandlers getProjects() {
		return projects;
	}

	public HasClickHandlers getBacklog() {
		return backlog;
	}

	public HasClickHandlers getSprint() {
		return sprint;
	}

	public HasClickHandlers getDiagnose() {
		return diagnose;
	}

	public HasClickHandlers getTeams() {
		return teams;
	}

	public HasClickHandlers getRoles() {
		return roles;
	}

	public HasClickHandlers getLogout() {
		return logout;
	}

	public HasClickHandlers getProfile() {
		return profile;
	}

	public void updateMenuBar() {
		Roles result = MageOfScrum.role;
		if (result == Roles.ADMIN || result == Roles.OWNER || result == Roles.MASTER) {
			roles.setVisible(true);
			teams.setVisible(true);
			projects.setVisible(true);
		} else {
			projects.setVisible(false);
			roles.setVisible(false);
			teams.setVisible(false);
		}
	}

}
