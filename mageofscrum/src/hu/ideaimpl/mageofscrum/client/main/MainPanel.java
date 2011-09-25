package hu.ideaimpl.mageofscrum.client.main;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import hu.ideaimpl.mageofscrum.client.ui.MenuBar;
import hu.ideaimpl.mageofscrum.client.user.LoginForm;

public class MainPanel extends Composite {

	public MainPanel() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		horizontalPanel.setSize("100%", "100%");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		horizontalPanel.add(verticalPanel);
		
		LoginForm loginForm = new LoginForm();
		verticalPanel.add(loginForm);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_1);
		
		MenuBar wlcMenuBar = new MenuBar();
		horizontalPanel_1.add(wlcMenuBar);
		
		HTMLPanel panel = new HTMLPanel("New HTML");
		horizontalPanel_1.add(panel);
		panel.setSize("100%", "100%");
	}

}
