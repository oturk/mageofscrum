package hu.ideaimpl.mageofscrum.client.ui;

import hu.ideaimpl.mageofscrum.client.resources.Resources;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DialogPanel extends VerticalPanel implements HasText{
	
	Label lblHeader = new Label("title");

	public DialogPanel() {
		setStylePrimaryName(Resources.instance.mosStyle().dialogPanel());
		
//		HorizontalPanel spacer = new HorizontalPanel();
//		spacer.add(lblHeader);
		
//		lblHeader.setWidth("100%");
//		lblHeader.setHeight("25px");
		lblHeader.setStyleName(Resources.instance.mosStyle().dialogHeaderText());
		add(lblHeader);
	}
	
	public void setSize(int width, int height) {
		lblHeader.setSize((width - 4)+"px", "25px");
		super.setSize(width+"px", height+"px");
	}

	@Override
	public String getText() {
		return lblHeader.getText();
	}

	@Override
	public void setText(String text) {
		lblHeader.setText(text);
	}

}
