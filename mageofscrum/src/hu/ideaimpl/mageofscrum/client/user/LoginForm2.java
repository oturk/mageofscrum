package hu.ideaimpl.mageofscrum.client.user;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class LoginForm2 extends Composite {

	private TextBox textBoxEmail = new TextBox();
	private PasswordTextBox textBoxPassword = new PasswordTextBox();
	private CheckBox chckbxRememberMe = new CheckBox("remember me");
	private Button btnLogin = new Button("login");
	private Button btnForgotPassword = new Button("forgot password");
	private Label lblError = new Label("");

	public LoginForm2() {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("loginForm");
		initWidget(absolutePanel);
		absolutePanel.setSize("310px", "267px");

		Label lblEmail = new Label("email:");
		lblEmail.setStyleName("loginLbl");
		lblEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblEmail, 0, 73);
		lblEmail.setSize("113px", "24px");

		absolutePanel.add(textBoxEmail, 113, 67);
		textBoxEmail.setSize("183px", "30px");

		Label lblPassword = new Label("password:");
		lblPassword.setStyleName("loginLbl");
		lblPassword.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblPassword, 0, 118);
		lblPassword.setSize("113px", "24px");

		absolutePanel.add(textBoxPassword, 113, 112);
		textBoxPassword.setSize("183px", "30px");

		absolutePanel.add(chckbxRememberMe, 10, 161);
		chckbxRememberMe.setHeight("30px");

		btnLogin.setStyleName("loginBtn");
		absolutePanel.add(btnLogin, 30, 197);
		btnLogin.setSize("120px", "30px");

		btnForgotPassword.setStyleName("loginBtn");
		absolutePanel.add(btnForgotPassword, 169, 197);
		btnForgotPassword.setSize("120px", "30px");

		Label lblLogin = new Label("Mage of Scrum login");
		lblLogin.setStyleName("loginHeader");
		absolutePanel.add(lblLogin, 0, 25);
		lblLogin.setSize("310px", "30px");

		lblError.setStyleName("errorLbl");
		absolutePanel.add(lblError, 0, 233);
		lblError.setSize("305px", "30px");
	}

	public TextBox getTextBoxEmail() {
		return textBoxEmail;
	}

	public PasswordTextBox getTextBoxPassword() {
		return textBoxPassword;
	}

	public CheckBox getChckbxRememberMe() {
		return chckbxRememberMe;
	}

	public Button getBtnLogin() {
		return btnLogin;
	}

	public Button getBtnForgotPassword() {
		return btnForgotPassword;
	}

	public Label getLblError() {
		return lblError;
	}

}
