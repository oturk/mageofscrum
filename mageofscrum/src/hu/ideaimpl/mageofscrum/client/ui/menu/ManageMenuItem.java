package hu.ideaimpl.mageofscrum.client.ui.menu;

import hu.ideaimpl.mageofscrum.client.resources.Resources;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;

public class ManageMenuItem extends Composite {

	public ManageMenuItem() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("menuItemPanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("400px", "72px");
		
		SimplePanel simplePanel = new SimplePanel();
		absolutePanel.add(simplePanel);
		simplePanel.setSize("72px", "72px");
		
		Image image = new Image(Resources.Util.getResources().manageIcon());
		image.setStyleName("menuIcon");
		simplePanel.setWidget(image);
		image.setSize("48px", "48px");
		
		Button btnCreate = new Button("create");
		btnCreate.setText("add user");
		btnCreate.setStyleName("menuButton");
		absolutePanel.add(btnCreate, 72, 0);
		
		Button btnDelete = new Button("delete");
		btnDelete.setText("add team");
		btnDelete.setStyleName("menuButton");
		absolutePanel.add(btnDelete, 136, 0);
		
		Button btnDetails = new Button("details");
		btnDetails.setText("users list");
		btnDetails.setStyleName("menuButton");
		absolutePanel.add(btnDetails, 73, 28);
		
		Button btnTeamsList = new Button("teams list");
		btnTeamsList.setStyleName("menuButton");
		absolutePanel.add(btnTeamsList, 146, 28);
		
		Button btnRolesList = new Button("roles list");
		btnRolesList.setStyleName("menuButton");
		absolutePanel.add(btnRolesList, 224, 28);
	}
}
