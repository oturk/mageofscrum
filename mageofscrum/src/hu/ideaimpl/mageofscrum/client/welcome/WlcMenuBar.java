package hu.ideaimpl.mageofscrum.client.welcome;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class WlcMenuBar extends Composite {

	private Button welcomeBtn = new Button("Welcome");
	private Button aboutBtn = new Button("About");
	private Button feedbackBtn = new Button("Send feedback");
	private Button projects = new Button("Projects");
	private Button backlog = new Button("Backlog");
	private Button sprint = new Button("Sprint");
	private Button users = new Button("Users");
	private Button teams = new Button("Teams");
	private Button logout = new Button("Logout");
	private AbsolutePanel verticalPanel = new AbsolutePanel();

	public WlcMenuBar() {
		verticalPanel.setStyleName("menuBar");
		
		initWidget(verticalPanel);
		verticalPanel.setSize("150px", "400px");
		Label lblUserName = new Label("Logged in user");
		lblUserName.setStyleName("menuBarHeader");
		lblUserName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(lblUserName);
		lblUserName.setHeight("30px");
		
		Label label = new Label("");
		verticalPanel.add(label);
		label.setHeight("40px");
		addMenuItem(welcomeBtn);
		addMenuItem(aboutBtn);
		addMenuItem(feedbackBtn);
		addMenuItem(projects);
		addMenuItem(backlog);
		addMenuItem(sprint);
		addMenuItem(users);
		addMenuItem(teams);
		addMenuItem(logout);
		setCanShowWelcomBar();
	}
	
	public void setCanShowWelcomBar(){
		welcomeBtn.setVisible(true);
		aboutBtn.setVisible(true);
		feedbackBtn.setVisible(true);
		projects.setVisible(false);
		backlog.setVisible(false);
		sprint.setVisible(false);
		users.setVisible(false);
		teams.setVisible(false);
		logout.setVisible(false);
	}
	
	public void setCanShowMainBar(){
		welcomeBtn.setVisible(false);
		aboutBtn.setVisible(false);
		feedbackBtn.setVisible(false);
		projects.setVisible(true);
		backlog.setVisible(true);
		sprint.setVisible(true);
		users.setVisible(true);
		teams.setVisible(false);
		logout.setVisible(true);
	}
	
	public void setCanShowAdminBar(){
		welcomeBtn.setVisible(false);
		aboutBtn.setVisible(false);
		feedbackBtn.setVisible(false);
		projects.setVisible(true);
		backlog.setVisible(true);
		sprint.setVisible(true);
		users.setVisible(true);
		teams.setVisible(true);
		logout.setVisible(true);
	}
	
	public void setCanShowAll(){
		welcomeBtn.setVisible(true);
		aboutBtn.setVisible(true);
		feedbackBtn.setVisible(true);
		projects.setVisible(true);
		backlog.setVisible(true);
		sprint.setVisible(true);
		users.setVisible(true);
		teams.setVisible(true);
		logout.setVisible(true);
	}
	
	public void addMenuItem(Button mItem){
		mItem.setStyleName("menuItem");
//		mItem.setSize("120px", "30px");
		verticalPanel.add(mItem);
	}

	public HasClickHandlers getWelcomeBtn() {
		return welcomeBtn;
	}

	public HasClickHandlers getAboutBtn() {
		return aboutBtn;
	}

	public HasClickHandlers getFeedbackBtn() {
		return feedbackBtn;
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

	public HasClickHandlers getUsers() {
		return users;
	}

	public HasClickHandlers getTeams() {
		return teams;
	}

	public HasClickHandlers getLogout() {
		return logout;
	}

	
}
