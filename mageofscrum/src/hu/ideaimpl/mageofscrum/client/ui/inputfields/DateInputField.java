package hu.ideaimpl.mageofscrum.client.ui.inputfields;

import hu.ideaimpl.mageofscrum.client.ui.HasValidator;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.DateBox;

public class DateInputField extends Composite implements HasValidator, HasText {

	private Label lblTitle = new Label("title:");
	private DateBox textBox = new DateBox();
	private boolean required = false;

	public DateInputField() {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("390px", "37px");
		
		lblTitle.setStyleName("loginLbl");
		lblTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblTitle, 0, 6);
		lblTitle.setSize("113px", "24px");

		absolutePanel.add(textBox, 116, 0);
		textBox.setSize("151px", "30px");
		
		DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_NUM_DAY);
		textBox.setFormat(new DateBox.DefaultFormat(format));
	}

	public Date getValue() {
		return textBox.getValue();
	}

	public void setValue(Date value) {
		textBox.setValue(value);
	}

	public void setRequired(boolean value) {
		required = value;
	}

	@Override
	public boolean validate() {
		if (required && textBox.getValue() == null) {
			lblTitle.setStyleName("invalidInputField");
			return false;
		} else {
			lblTitle.setStyleName("inputField");
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
}
