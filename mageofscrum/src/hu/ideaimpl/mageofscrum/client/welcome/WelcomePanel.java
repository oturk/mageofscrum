package hu.ideaimpl.mageofscrum.client.welcome;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class WelcomePanel extends Composite {

	public WelcomePanel() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		
		HorizontalPanel menuBar = new HorizontalPanel();
		horizontalPanel.add(menuBar);
		
		HTMLPanel contentPanel = new HTMLPanel("New HTML");
		horizontalPanel.add(contentPanel);
	}

}
