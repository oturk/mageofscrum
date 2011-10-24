package hu.ideaimpl.mageofscrum.client.ui.inputfields;


import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class PasswordInputField extends Composite implements HasValidator, HasText, HasValue<String> {

	private Label lblTitle = new Label("title:");
	private PasswordTextBox textBox = new PasswordTextBox();
	private boolean required = false;

	public PasswordInputField() {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("272px", "37px");

		lblTitle.setStyleName("loginLbl");
		lblTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblTitle, 0, 6);
		lblTitle.setSize("113px", "24px");

		absolutePanel.add(textBox, 116, 0);
		textBox.setSize("151px", "30px");
	}

	public void setRequired(boolean value) {
		required = value;
	}

	@Override
	public boolean validate() {
		if (required && textBox.getValue().isEmpty()) {
			lblTitle.setStyleName("invalidInputField");
			return false;
		} else {
			lblTitle.setStyleName("inputField");
			return true;
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return null;
	}

	@Override
	public String getValue() {
		return textBox.getValue();
	}

	@Override
	public void setValue(String value) {
		textBox.setValue(value);
	}

	@Override
	public void setValue(String value, boolean fireEvents) {}

	@Override
	public String getText() {
		return lblTitle.getText();
	}

	@Override
	public void setText(String text) {
		lblTitle.setText(text);
	}

}
