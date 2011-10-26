package hu.ideaimpl.mageofscrum.client.ui.forms;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.fields.HasValidator;
import hu.ideaimpl.mageofscrum.client.ui.fields.InputField;
import hu.ideaimpl.mageofscrum.client.ui.fields.PasswordInputField;
import hu.ideaimpl.mageofscrum.shared.UserDO;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NewUserForm extends Composite implements HasForm<UserDO>, HasValidator {
	private InputField username = new InputField();
	private InputField email = new InputField();
	private InputField surname = new InputField();
	private InputField forename = new InputField();
	private PasswordInputField password = new PasswordInputField();
	private PasswordInputField confirm = new PasswordInputField();
	private Button btnAdd = new Button("add user");
	private Button btnChange = new Button("change password");
	private Label errorLbl = new Label();
	private String errorMsg = "";

	public NewUserForm() {
		HorizontalPanel hPanel = new HorizontalPanel();
		initWidget(hPanel);
		hPanel.setSpacing(10);

		username.setText("username:");
		username.setRequired(true);
		email.setText("email:");
		email.setRequired(true);
		surname.setText("surname:");
		surname.setRequired(true);
		forename.setText("forename:");
		forename.setRequired(true);
		password.setText("password:");
		password.setRequired(true);
		confirm.setText("confirm password:");
		confirm.setRequired(true);
		errorLbl.setStyleName(Resources.instance.mosStyle().errorLbl());

		VerticalPanel leftPanel = new VerticalPanel();
		leftPanel.setSpacing(1);
		leftPanel.add(username);
		leftPanel.add(email);
		leftPanel.add(surname);
		leftPanel.add(forename);

		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(1);
		btnAdd.setStyleName(Resources.instance.mosStyle().commandBtn());
		btnChange.setStyleName(Resources.instance.mosStyle().commandBtn());
		buttonBar.add(btnAdd);
		buttonBar.add(btnChange);
		leftPanel.add(buttonBar);

		VerticalPanel rightPanel = new VerticalPanel();
		rightPanel.setSpacing(1);
		rightPanel.add(password);
		rightPanel.add(confirm);
		rightPanel.add(errorLbl);

		hPanel.add(leftPanel);
		hPanel.add(rightPanel);
	}

	@Override
	public boolean validate() {
		boolean result = true;
		errorMsg = "";
		if (!username.validate()) {
			result = false;
		}
		if (!email.validate()) {
			result = false;
		}
		if (!surname.validate()) {
			result = false;
		}
		if (!forename.validate()) {
			result = false;
		}
		result = validatePassword();
		if (!result) {
			errorMsg = "fill required fields";
		}
		errorLbl.setText(errorMsg);

		return result;
	}

	public boolean validatePassword() {
		boolean result = true;
		errorMsg = "";
		if (!password.validate()) {
			result = false;
		}
		if (!confirm.validate()) {
			result = false;
		}
		if (!result) {
			errorMsg = "fill required fields";
		}
		if (!password.getValue().isEmpty() && !confirm.getValue().isEmpty()) {
			if (!password.getValue().equals(confirm.getValue())) {
				errorMsg = "password confimation failed";
				result = false;
			}
		}
		errorLbl.setText(errorMsg);
		return result;
	}

	public UserDO getValue() {
		return null;
	}

	@Override
	public void clearForm() {
		username.setValue("");
		username.setValidState(true);
		email.setValue("");
		email.setValidState(true);
		surname.setValue("");
		surname.setValidState(true);
		forename.setValue("");
		forename.setValidState(true);
		password.setValue("");
		password.setValidState(true);
		confirm.setValue("");
		confirm.setValidState(true);
	}

	@Override
	public void setFormData(UserDO data) {

	}

	@Override
	public UserDO getFormData() {
		UserDO user = new UserDO();
		user.setUsername(username.getValue());
		user.setPassword(password.getValue());
		UserDataDO userData = new UserDataDO();
		userData.setSurname(surname.getValue());
		userData.setForename(forename.getValue());
		userData.setEmail(email.getValue());
		user.setUserData(userData);
		return user;
	}

	public HasClickHandlers getAddUserBtn() {
		return btnAdd;
	}

	public HasClickHandlers getChangePassBtn() {
		return btnChange;
	}

	public HasText getErrorLbl() {
		return errorLbl;
	}

	@Override
	public void setValidState(boolean isValid) {

	}

}
