package hu.ideaimpl.mageofscrum.client.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

public class Header extends Composite {
	private final int clientWidth = Window.getClientWidth();
	
	public Header() {
		AbsolutePanel headerPanel = new AbsolutePanel();
		Image image = new Image("mageofscrum/images/logo.png");
		initWidget(headerPanel);
		headerPanel.setStyleName("headerPanel");
		
		headerPanel.setSize(Integer.toString(clientWidth-20)+"px", "100px");
		
		headerPanel.add(image, 0, 21);
	}

}
