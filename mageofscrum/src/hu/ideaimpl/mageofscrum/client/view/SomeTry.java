package hu.ideaimpl.mageofscrum.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class SomeTry extends Composite {

	public SomeTry() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("100%", "100%");
		
		AbsolutePanel headerPanel = new AbsolutePanel();
		headerPanel.setStyleName("styleTry");
		absolutePanel.add(headerPanel);
		headerPanel.setSize("95%", "100px");
		
		AbsolutePanel containerPanel = new AbsolutePanel();
		containerPanel.setStyleName("contentPanel");
		absolutePanel.add(containerPanel);
		containerPanel.setSize("100%", "600px");
	}

}
