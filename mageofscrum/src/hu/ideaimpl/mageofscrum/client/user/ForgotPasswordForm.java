package hu.ideaimpl.mageofscrum.client.user;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.InlineHyperlink;

public class ForgotPasswordForm extends Composite {

	private Button sendButton = new Button("send");
	private TextBox forgPassEmail = new TextBox();
	private Label msgLbl = new Label("");

	public ForgotPasswordForm() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("310px", "276px");
		
		Label lblForgotYourPassword = new Label("Forgot your password?");
		lblForgotYourPassword.setStyleName("loginHeader");
		absolutePanel.add(lblForgotYourPassword, 0, 25);
		lblForgotYourPassword.setSize("310px", "30px");
		
		Label lblEmail = new Label("email:");
		lblEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		lblEmail.setStyleName("loginLbl");
		absolutePanel.add(lblEmail, 0, 78);
		lblEmail.setSize("107px", "24px");
		
		absolutePanel.add(forgPassEmail, 106, 72);
		forgPassEmail.setSize("190px", "30px");
		
		sendButton.setStyleName("loginBtn");
		absolutePanel.add(sendButton, 94, 128);
		sendButton.setSize("120px", "30px");
		
		absolutePanel.add(msgLbl, 0, 221);
		msgLbl.setSize("310px", "30px");
		
		InlineHyperlink lnkGoBackToLogin = new InlineHyperlink("Go back to login", false, "login");
		absolutePanel.add(lnkGoBackToLogin, 10, 181);
	}

	public Button getSendButton() {
		return sendButton;
	}

	public TextBox getForgPassEmail() {
		return forgPassEmail;
	}

	public Label getMsgLbl() {
		return msgLbl;
	}
}
