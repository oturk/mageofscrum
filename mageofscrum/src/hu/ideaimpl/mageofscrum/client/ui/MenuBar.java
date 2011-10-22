package hu.ideaimpl.mageofscrum.client.ui;

import hu.ideaimpl.mageofscrum.client.MageOfScrum;
import hu.ideaimpl.mageofscrum.shared.Roles;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuBar extends Composite {

	private Button projects = new Button("projects");
	private Button backlog = new Button("backlog");
	private Button sprint = new Button("sprint");
	private Button profile = new Button("profile");
	private Button diagnose = new Button("diagnose");
	private Button logout = new Button("logout");
	private Button teams = new Button("teams");
	private Button roles = new Button("users & roles");
	private VerticalPanel verticalPanel = new VerticalPanel();

	public MenuBar() {
		verticalPanel.setStyleName("menuBar");
		initWidget(verticalPanel);
		verticalPanel.setSize("150px", "200px");
		verticalPanel.setSpacing(1);

		Label label = new Label("");
		label.setHeight("40px");
		projects.setStyleName("menuItem");
		backlog.setStyleName("menuItem");
		sprint.setStyleName("menuItem");
		profile.setStyleName("menuItem");
		diagnose.setStyleName("menuItem");
		logout.setStyleName("menuItem");
		teams.setStyleName("menuItem");
		roles.setStyleName("menuItem");

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
		mItem.setStyleName("menuItem");
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
