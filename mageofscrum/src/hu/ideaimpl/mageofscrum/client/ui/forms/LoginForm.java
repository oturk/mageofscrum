package hu.ideaimpl.mageofscrum.client.ui.forms;

import hu.ideaimpl.mageofscrum.client.resources.MosStyle;
import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.TitledPanel;
import hu.ideaimpl.mageofscrum.client.ui.inputfields.HasValidator;
import hu.ideaimpl.mageofscrum.client.ui.inputfields.InputField;
import hu.ideaimpl.mageofscrum.client.ui.inputfields.PasswordInputField;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginForm extends Composite implements HasValidator{

	private InputField username = new InputField();
	private PasswordInputField password = new PasswordInputField();
	private CheckBox chckbxRememberMe = new CheckBox("remember me");
	private Button btnLogin = new Button("login");
	private Label lblError = new Label("");

	public LoginForm() {
		MosStyle style = Resources.instance.mosStyle();
		style.ensureInjected();
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		VerticalPanel contentPanel = new VerticalPanel();
		contentPanel.setSpacing(2);
		contentPanel.setSize("300px", "220px");
		username.setText("username:");
		username.setRequired(true);
		contentPanel.add(username);
		password.setText("password:");
		password.setRequired(true);
		contentPanel.add(password);
		contentPanel.add(chckbxRememberMe);
		btnLogin.setStyleName(style.commandBtn());
		contentPanel.add(btnLogin);
		btnLogin.setSize("120px", "30px");
		
		TitledPanel loginPanel = new TitledPanel();
		verticalPanel.add(loginPanel);
		loginPanel.setText("login");
		loginPanel.setSize("300px", "250px");
		loginPanel.addContent(contentPanel);


		lblError.setStyleName("errorLbl");
		verticalPanel.add(lblError);
		lblError.setSize("305px", "30px");
	}

	public String getUsername() {
		return username.getValue();
	}

	public String getPassword() {
		return password.getValue();
	}

	public Boolean getChckbxRememberMe() {
		return chckbxRememberMe.getValue();
	}

	public HasClickHandlers getBtnLogin() {
		return btnLogin;
	}

	public HasText getLblError() {
		return lblError;
	}

	@Override
	public boolean validate() {
		boolean result = true;
		String msg = "";
		if(!username.validate()){
			result = false;
		}
		if(!password.validate()){
			result = false;
		}
		if(!result){
			msg = "Fill required fields!";
		}
		lblError.setText(msg);
		return result;
	}

}
