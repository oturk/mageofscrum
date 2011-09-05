package hu.ideaimpl.mageofscrum.client.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class LoginForm extends Composite {
	private static final int FORM_SIZE = 500;
	private int width = Window.getClientWidth() - FORM_SIZE;
	
	public LoginForm() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setStyleName("loginPanel");
		initWidget(horizontalPanel);
		horizontalPanel.setSize("100%", "30");
		
		final HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		horizontalPanel.add(horizontalPanel_2);
		horizontalPanel_2.setSize(Integer.toString(width)+"px", "30");
		
		final Label errorLabel = new Label("");
		errorLabel.setStyleName("errorLbl");
		horizontalPanel_2.add(errorLabel);
		errorLabel.setSize(Integer.toString(width)+"px", "30");
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		horizontalPanel.add(horizontalPanel_1);
		horizontalPanel_1.setSize("450", "30");
		
		Label lblEmail = new Label("email:");
		lblEmail.setStyleName("loginText");
		horizontalPanel_1.add(lblEmail);
		
		final TextBox emailTextBox = new TextBox();
		horizontalPanel_1.add(emailTextBox);
		
		Label lblPassword = new Label("password:");
		lblPassword.setStyleName("loginText");
		horizontalPanel_1.add(lblPassword);
		
		PasswordTextBox passwordTextBox = new PasswordTextBox();
		horizontalPanel_1.add(passwordTextBox);
		
		Button btnLogin = new Button("login");
		btnLogin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				LoginServiceAsync service = GWT.create(LoginService.class);
				service.getPassword(emailTextBox.getText(), new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						errorLabel.setText("You're successfully logged in!");
					}
					
					@Override
					public void onFailure(Throwable caught) {
						errorLabel.setText("Sorry, server error!");
					}
				});
			}
		});
		btnLogin.setStyleName("loginButton");
		horizontalPanel_1.add(btnLogin);
		btnLogin.setHeight("26px");
		
		Window.addResizeHandler(new ResizeHandler() {
			
			@Override
			public void onResize(ResizeEvent event) {
				width = event.getWidth() - FORM_SIZE;
				if(width > 0){
				String widthInString = Integer.toString(width)+"px";
				horizontalPanel_2.setWidth(widthInString);
				errorLabel.setWidth(widthInString);
				}
			}
		});
	}

}
