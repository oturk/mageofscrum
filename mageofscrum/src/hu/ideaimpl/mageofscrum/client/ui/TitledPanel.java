package hu.ideaimpl.mageofscrum.client.ui;

import java.util.Iterator;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TitledPanel extends Composite implements HasText {

	private Label lblTitle;
	private SimplePanel contentPanel = new SimplePanel();

	public TitledPanel() {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("titledPanel");
		initWidget(absolutePanel);

		lblTitle = new Label("");
		lblTitle.setStyleName("titledPanelTitle");
		absolutePanel.add(lblTitle);
		lblTitle.setSize("", "25px");

		absolutePanel.add(contentPanel);
//		contentPanel.setSize("100%", "100%");
	}
	
	public void addContent(Widget widget){
		contentPanel.add(widget);
	}

	@Override
	public String getText() {
		return lblTitle.getText();
	}

	@Override
	public void setText(String text) {
		lblTitle.setText(text);
	}

	@Override
	public void setSize(String width, String height) {
		contentPanel.setSize(width, height);
		super.setSize(width, height);
	}

}
