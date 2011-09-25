package hu.ideaimpl.mageofscrum.client.ui;

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

public class MenuBar extends Composite {

//	private Button welcomeBtn = new Button("Welcome");
//	private Button aboutBtn = new Button("About");
//	private Button feedbackBtn = new Button("Send feedback");
	private Button projects = new Button("projects");
	private Button backlog = new Button("backlog");
	private Button sprint = new Button("sprint");
	private Button profile = new Button("profile");
	private Button logout = new Button("logout");
	private Button teams = new Button("teams");
	private Button roles = new Button("roles");
	private Button users = new Button("users");
	private AbsolutePanel verticalPanel = new AbsolutePanel();
	private final Label lblManage = new Label("manage");

	public MenuBar() {
		verticalPanel.setStyleName("menuBar");
		
		initWidget(verticalPanel);
		verticalPanel.setSize("150px", "400px");
//		Label lblUserName = new Label("Logged in user");
//		lblUserName.setStyleName("menuBarHeader");
//		lblUserName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		verticalPanel.add(lblUserName);
//		lblUserName.setHeight("30px");
		
		Label label = new Label("");
		verticalPanel.add(label);
		label.setHeight("40px");
//		addMenuItem(welcomeBtn);
//		addMenuItem(aboutBtn);
//		addMenuItem(feedbackBtn);
//		addMenuItem(projects);
		projects.setStyleName("menuItem");
		verticalPanel.add(projects);
//		addMenuItem(backlog);
		backlog.setStyleName("menuItem");
		verticalPanel.add(backlog);
//		addMenuItem(sprint);
		sprint.setStyleName("menuItem");
		verticalPanel.add(sprint);
//		addMenuItem(profile);
		profile.setStyleName("menuItem");
		verticalPanel.add(profile);
//		addMenuItem(logout);
		logout.setStyleName("menuItem");
		verticalPanel.add(logout);
		lblManage.setStyleName("menuSection");
		
		verticalPanel.add(lblManage,0,200);
		lblManage.setSize("150px", "30px");
//		setCanShowWelcomBar();
//		addMenuItem(teams);
		teams.setStyleName("menuItem");
		verticalPanel.add(teams,0,236);
//		addMenuItem(users);
		roles.setStyleName("menuItem");
		verticalPanel.add(roles,0,267);
		users.setStyleName("menuItem");
		verticalPanel.add(users,0,298);
		showAll();
	}
	
	public void setCanShowWelcomBar(){
//		welcomeBtn.setVisible(true);
//		aboutBtn.setVisible(true);
//		feedbackBtn.setVisible(true);
		projects.setVisible(false);
		backlog.setVisible(false);
		sprint.setVisible(false);
		users.setVisible(false);
		teams.setVisible(false);
		profile.setVisible(true);
		logout.setVisible(false);
	}
	
	public void showUserMenuBar(){
//		welcomeBtn.setVisible(false);
//		aboutBtn.setVisible(false);
//		feedbackBtn.setVisible(false);
		projects.setVisible(true);
		backlog.setVisible(true);
		sprint.setVisible(true);
		profile.setVisible(true);
		logout.setVisible(true);
		lblManage.setVisible(false);
		users.setVisible(false);
		teams.setVisible(false);
		roles.setVisible(false);
	}
	
	public void showAdminMenuBar(){
//		welcomeBtn.setVisible(false);
//		aboutBtn.setVisible(false);
//		feedbackBtn.setVisible(false);
		projects.setVisible(true);
		backlog.setVisible(true);
		sprint.setVisible(true);
		profile.setVisible(true);
		logout.setVisible(true);
		lblManage.setVisible(true);
		users.setVisible(true);
		teams.setVisible(true);
		roles.setVisible(true);
	}
	
	public void showAll(){
//		welcomeBtn.setVisible(true);
//		aboutBtn.setVisible(true);
//		feedbackBtn.setVisible(true);
		projects.setVisible(true);
		backlog.setVisible(true);
		sprint.setVisible(true);
		profile.setVisible(true);
		logout.setVisible(true);
		lblManage.setVisible(true);
		users.setVisible(true);
		teams.setVisible(true);
		roles.setVisible(true);
	}
	
	public void addMenuItem(Button mItem){
		mItem.setStyleName("menuItem");
		verticalPanel.add(mItem);
	}

//	public HasClickHandlers getWelcomeBtn() {
//		return welcomeBtn;
//	}
//
//	public HasClickHandlers getAboutBtn() {
//		return aboutBtn;
//	}
//
//	public HasClickHandlers getFeedbackBtn() {
//		return feedbackBtn;
//	}

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
