package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.Header;
import hu.ideaimpl.mageofscrum.shared.Errors;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ErrorView extends Composite {
//	private Errors error;
	private HTMLPanel message;
	private VerticalPanel verticalPanel = new VerticalPanel();
	
	public ErrorView() {
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		Header header = new Header(Resources.Util.getResources().errorLogo());
		
//		AbsolutePanel headerPanel = new AbsolutePanel();
//		headerPanel.setStyleName("headerPanel");
		verticalPanel.add(header);
//		headerPanel.setSize("100%", "100px");
		
//		Image image = new Image(Resources.Util.getResources().errorLogo());
//		headerPanel.add(image,0,21);
//		image.setSize("500px", "60px");
		
//		message = new HTMLPanel("<h1>We're sorry!</h1><p>"+error.getMsg()+"</p>");
//		verticalPanel.add(message);
//		message.setSize("100%", "300px");
	}

	public void setError(Errors error) {
		message = new HTMLPanel("<h2>We're sorry!</h2><h3>"+error.getMsg()+"</h3>");
		verticalPanel.add(message);
		message.setSize("100%", "300px");
	}
	
}
