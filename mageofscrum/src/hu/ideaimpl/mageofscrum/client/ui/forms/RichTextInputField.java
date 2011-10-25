package hu.ideaimpl.mageofscrum.client.ui.forms;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Label;
import hu.ideaimpl.mageofscrum.client.ui.RichTextToolbar;
import hu.ideaimpl.mageofscrum.client.ui.inputfields.HasValidator;

public class RichTextInputField extends Composite implements HasText, HasValue<String>, HasValidator{

	private RichTextArea richTextArea = new RichTextArea();
	private RichTextToolbar richTextToolbar = new RichTextToolbar(richTextArea);
	private Label title = new Label("description:");
	private boolean required = false;

	public RichTextInputField() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(1);
		initWidget(verticalPanel);
		title.setStyleName("loginLbl");
		
		verticalPanel.add(title);
		
		verticalPanel.add(richTextToolbar);
		
		verticalPanel.add(richTextArea);
		richTextArea.setSize("450px", "200px");
		
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return null;
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public String getValue() {
		return richTextArea.getHTML();
	}

	@Override
	public void setValue(String value) {
		richTextArea.setHTML(value);
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		
	}

	@Override
	public String getText() {
		return title.getText();
	}

	@Override
	public void setText(String text) {
		title.setText(text);
	}

}
