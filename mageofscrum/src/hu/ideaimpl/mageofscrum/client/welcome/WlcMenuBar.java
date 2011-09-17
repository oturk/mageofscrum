package hu.ideaimpl.mageofscrum.client.welcome;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class WlcMenuBar extends Composite {

	private Button welcomeBtn = new Button("Welcome");
	private Button aboutBtn = new Button("About");
	private Button feedbackBtn = new Button("Send feedback");
	private Button projects = new Button("Projects");
	private Button backlog = new Button("Backlog");
	private Button sprint = new Button("Sprint");
	private Button manageAcc = new Button("Manage account");
	private Button manageUsers = new Button("Manage users");
	private Button logout = new Button("Logout");
	private VerticalPanel verticalPanel = new VerticalPanel();

	public WlcMenuBar() {
		
		initWidget(verticalPanel);
		verticalPanel.setHeight("100%");
		
		Image image = new Image("mageofscrum/images/mageofscrum-logo.png");
		verticalPanel.add(image);
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		verticalPanel.add(verticalPanel_1);
		verticalPanel_1.setSize("", "50px");
		
		Label lblNewLabel = new Label("");
		verticalPanel_1.add(lblNewLabel);
		lblNewLabel.setHeight("50px");
		
		addMenuItem(welcomeBtn);
		addMenuItem(aboutBtn);
		addMenuItem(feedbackBtn);
		addMenuItem(projects);
		addMenuItem(backlog);
		addMenuItem(sprint);
		addMenuItem(manageAcc);
		addMenuItem(manageUsers);
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
		manageAcc.setVisible(false);
		manageUsers.setVisible(false);
		logout.setVisible(false);
	}
	
	public void setCanShowMainBar(){
		welcomeBtn.setVisible(false);
		aboutBtn.setVisible(false);
		feedbackBtn.setVisible(false);
		projects.setVisible(true);
		backlog.setVisible(true);
		sprint.setVisible(true);
		manageAcc.setVisible(true);
		manageUsers.setVisible(false);
		logout.setVisible(true);
	}
	
	public void setCanShowAdminBar(){
		welcomeBtn.setVisible(false);
		aboutBtn.setVisible(false);
		feedbackBtn.setVisible(false);
		projects.setVisible(true);
		backlog.setVisible(true);
		sprint.setVisible(true);
		manageAcc.setVisible(true);
		manageUsers.setVisible(true);
		logout.setVisible(true);
	}
	
	public void addMenuItem(Button mItem){
		mItem.setStyleName("menuItem");
		verticalPanel.add(mItem);
	}

	public Button getWelcomeBtn() {
		return welcomeBtn;
	}

	public Button getAboutBtn() {
		return aboutBtn;
	}

	public Button getFeedbackBtn() {
		return feedbackBtn;
	}

	public Button getProjects() {
		return projects;
	}

	public Button getBacklog() {
		return backlog;
	}

	public Button getSprint() {
		return sprint;
	}

	public Button getManageAcc() {
		return manageAcc;
	}

	public Button getManageUsers() {
		return manageUsers;
	}

	public Button getLogout() {
		return logout;
	}

	
}
