package hu.ideaimpl.mageofscrum.client.ui.menu;

import hu.ideaimpl.mageofscrum.client.resources.Resources;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;

public class BacklogMenuItem extends Composite {

	public BacklogMenuItem() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("menuItemPanel");
		initWidget(absolutePanel);
		absolutePanel.setSize("400px", "72px");
		
		SimplePanel simplePanel = new SimplePanel();
		absolutePanel.add(simplePanel);
		simplePanel.setSize("72px", "72px");
		
		Image image = new Image(Resources.Util.getResources().backlogIcon());
		image.setStyleName("menuIcon");
		simplePanel.setWidget(image);
		image.setSize("48px", "48px");
		
		Button btnCreate = new Button("create");
		btnCreate.setStyleName("menuButton");
		absolutePanel.add(btnCreate, 72, 0);
		
		Button btnDelete = new Button("delete");
		btnDelete.setStyleName("menuButton");
		absolutePanel.add(btnDelete, 136, 0);
		
		Button btnDetails = new Button("details");
		btnDetails.setStyleName("menuButton");
		absolutePanel.add(btnDetails, 197, 0);
		
		ListBox comboBox = new ListBox();
		absolutePanel.add(comboBox, 111, 38);
		comboBox.setSize("246px", "20px");
	}
}
