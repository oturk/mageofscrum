package hu.ideaimpl.mageofscrum.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class CopyrightPanel extends Composite {

	public CopyrightPanel() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setStyleName("loginPanel");
		initWidget(horizontalPanel);
		horizontalPanel.setSize("100%", "25px");
		
		Label lblNewLabel = new Label("copyright Ott\u00F3 T\u00FCrk");
		horizontalPanel.add(lblNewLabel);
	}

}
