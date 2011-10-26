package hu.ideaimpl.mageofscrum.client.ui.fields;


import hu.ideaimpl.mageofscrum.client.resources.Resources;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class InputField extends Composite implements HasValidator, HasText, HasValue<String>, Focusable, HasEnabled{

	private Label lblTitle = new Label("title:");
	private TextBox textBox = new TextBox();
	private boolean required = false;

	public InputField() {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("273px", "37px");

		lblTitle.setStyleName(Resources.instance.mosStyle().inputFieldTitle());
		lblTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblTitle, 0, 6);
		lblTitle.setSize("113px", "24px");

		absolutePanel.add(textBox, 116, 0);
		textBox.setSize("151px", "30px");
	}

	@Override
	public String getValue() {
		return textBox.getValue();
	}

	public void setValue(String value) {
		textBox.setValue(value);
	}

	public void setRequired(boolean value) {
		required = value;
	}

	@Override
	public boolean validate() {
		if (required && textBox.getValue().isEmpty()) {
			lblTitle.setStyleName(Resources.instance.mosStyle().invalidInputField());
			return false;
		} else {
			lblTitle.setStyleName(Resources.instance.mosStyle().inputField());
			return true;
		}
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
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTabIndex() {
		return textBox.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		textBox.setAccessKey(key);
	}

	@Override
	public void setFocus(final boolean focused) {
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			public void execute() {
				textBox.setFocus(focused);
			}
		});
	}

	@Override
	public void setTabIndex(int index) {
		textBox.setTabIndex(index);
	}

	@Override
	public boolean isEnabled() {
		return textBox.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		textBox.setEnabled(enabled);
	}

	@Override
	public void setValidState(boolean isValid) {
		if(!isValid){
			lblTitle.setStyleName(Resources.instance.mosStyle().invalidInputField());
		}else{
			lblTitle.setStyleName(Resources.instance.mosStyle().inputField());
		}
	}

}
