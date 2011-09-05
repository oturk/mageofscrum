package hu.ideaimpl.mageofscrum.client.welcome;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;

public class MenuBar extends Composite {

	public MenuBar() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setHeight("121px");
		
		Button btnNewButton = new Button("Welcome");
		btnNewButton.setStyleName("menuItem");
		verticalPanel.add(btnNewButton);
		
		Button btnNewButton_1 = new Button("About");
		btnNewButton_1.setStyleName("menuItem");
		verticalPanel.add(btnNewButton_1);
		
		Button btnNewButton_4 = new Button("Send feedback");
		btnNewButton_4.setStyleName("menuItem");
		verticalPanel.add(btnNewButton_4);
	}

}
