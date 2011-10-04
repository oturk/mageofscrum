package hu.ideaimpl.mageofscrum.client.ui.menu;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class MenuPanel extends Composite {

	private final ProjectsMenuItem projectsMenuItem = new ProjectsMenuItem();
	private final BacklogMenuItem backlogMenuItem = new BacklogMenuItem();
	private final SprintMenuItem sprintMenuItem = new SprintMenuItem();
	private final ManageMenuItem manageMenuItem = new ManageMenuItem();
	
	public MenuPanel() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("404px", "400px");
		
		absolutePanel.add(projectsMenuItem);
		absolutePanel.add(backlogMenuItem);
		absolutePanel.add(sprintMenuItem);
		absolutePanel.add(manageMenuItem);
	}

}
