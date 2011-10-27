package hu.ideaimpl.mageofscrum.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TitledPanel extends Composite implements HasText {

	private Label lblTitle;
	private SimplePanel contentPanel = new SimplePanel();
	private final Style style;

	public interface Resources extends ClientBundle {
		@Source("TitledPanel.css")
		Style titledPanelStyle();
	}

	public interface Style extends CssResource {
		String DEFAULT_CSS = "hu/ideaimpl/mageofscrum/client/ui/TitledPanel.css";

		String titledPanel();

		String titledPanelTitle();
	}

	public TitledPanel() {
		Resources res = GWT.create(Resources.class);
		style = res.titledPanelStyle();
		style.ensureInjected();
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName(style.titledPanel());
		initWidget(absolutePanel);

		lblTitle = new Label("");
		lblTitle.setStyleName(style.titledPanelTitle());
		absolutePanel.add(lblTitle);
		lblTitle.setSize("", "25px");

		absolutePanel.add(contentPanel);
	}

	public void addContent(Widget widget) {
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
//		contentPanel.setSize(width, height);
		super.setSize(width, height);
	}

}
