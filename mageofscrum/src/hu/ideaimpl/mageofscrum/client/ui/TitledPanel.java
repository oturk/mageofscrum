package hu.ideaimpl.mageofscrum.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TitledPanel extends Composite implements HasText {

	private Label lblTitle;
	private ScrollPanel contentPanel = new ScrollPanel();
	private final Style style;
	private VerticalPanel vPanel = new VerticalPanel();

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
		vPanel.setSpacing(1);
		vPanel.setStyleName(style.titledPanel());
//		AbsolutePanel absolutePanel = new AbsolutePanel();
//		absolutePanel.setStyleName(style.titledPanel());
//		initWidget(absolutePanel);
		initWidget(vPanel);

		lblTitle = new Label("");
		lblTitle.setStyleName(style.titledPanelTitle());
		vPanel.add(lblTitle);
		lblTitle.setSize("", "25px");

		vPanel.add(contentPanel);
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
	
	public void setSize(int width, int height){
		vPanel.setSize((width+30)+"px", height+"px");
		lblTitle.setSize((width)+"px", "25px");
		contentPanel.setSize((width+30)+"px", height+"px");
	}

	public void setSize(String width, String height) {
		vPanel.setSize(width, height);
		contentPanel.setSize(width, height);
//		contentPanel.setSize(width, height);
//		super.setSize(width, height);
	}

}
