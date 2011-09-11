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

	public WlcMenuBar() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
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
		
		welcomeBtn.setStyleName("menuItem");
		verticalPanel.add(welcomeBtn);
		
		aboutBtn.setStyleName("menuItem");
		verticalPanel.add(aboutBtn);
		
		feedbackBtn.setStyleName("menuItem");
		verticalPanel.add(feedbackBtn);
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

}
