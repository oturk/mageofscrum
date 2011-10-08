package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class ProfileView extends Composite {

	private Label lblEmail = new Label("email:");
	private TextBox email = new TextBox();
	private TextBox surname = new TextBox();
	private Label lblSurname = new Label("surname:");
	private TextBox forename = new TextBox();
	private Label lblForename = new Label("forename:");
	private PasswordTextBox password = new PasswordTextBox();
	private Label lblPassword = new Label("password:");
	private PasswordTextBox confirmPass = new PasswordTextBox();
	private Label lblConfirmPass = new Label("confirm pass:");
	private Button btnSave = new Button("save");
	private Button btnChangePassword = new Button("change password");
	private Label lblFormTitle = new Label("change personal data");
	private final Label lblChangePassword = new Label("change password");
	private Label lblSaveError = new Label("");
	private Label lblPasswordError = new Label("");

	public ProfileView() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setHeight("505px");
		
		lblEmail.setStyleName("inputField");
		lblEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblEmail, 0, 98);
		lblEmail.setSize("113px", "24px");
		
		absolutePanel.add(email, 115, 92);
		email.setSize("151px", "30px");
		
		absolutePanel.add(surname, 115, 140);
		surname.setSize("151px", "30px");
		
		lblSurname.setStyleName("inputField");
		lblSurname.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblSurname, 0, 146);
		lblSurname.setSize("113px", "24px");
		
		absolutePanel.add(forename, 115, 188);
		forename.setSize("151px", "30px");
		
		lblForename.setStyleName("inputField");
		lblForename.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblForename, 0, 194);
		lblForename.setSize("113px", "24px");
		
		absolutePanel.add(password, 115, 330);
		password.setSize("151px", "30px");
		
		lblPassword.setStyleName("inputField");
		lblPassword.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblPassword, 0, 336);
		lblPassword.setSize("113px", "24px");
		
		absolutePanel.add(confirmPass, 115, 378);
		confirmPass.setSize("151px", "30px");
		
		lblConfirmPass.setStyleName("inputField");
		lblConfirmPass.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblConfirmPass, 0, 384);
		lblConfirmPass.setSize("113px", "24px");
		
		btnSave.setStyleName("menuButton");
		absolutePanel.add(btnSave, 167, 230);
		
		btnChangePassword.setStyleName("menuButton");
		absolutePanel.add(btnChangePassword, 125, 420);
		
		lblFormTitle.setStyleName("formTitle");
		absolutePanel.add(lblFormTitle, 0, 20);
		lblFormTitle.setSize("263px", "30px");
		lblChangePassword.setStyleName("formTitle");
		
		absolutePanel.add(lblChangePassword, 0, 258);
		lblChangePassword.setSize("263px", "30px");
		
		lblSaveError.setStyleName("errorLbl");
		absolutePanel.add(lblSaveError, 0, 56);
		lblSaveError.setSize("263px", "30px");
		
		lblPasswordError.setStyleName("errorLbl");
		absolutePanel.add(lblPasswordError, 0, 294);
		lblPasswordError.setSize("265px", "30px");
	}
	
	public void setUserData(UserDataDO data){
		email.setValue(data.getEmail());
		surname.setValue(data.getSurname());
		forename.setValue(data.getForename());
	}
	
	public UserDataDO getUserData(){
		UserDataDO data = new UserDataDO();
		data.setEmail(email.getValue());
		data.setSurname(surname.getValue());
		data.setForename(forename.getValue());
		
		return data;
	}
	
	public String getPassword(){
		return password.getValue();
	}
	
	public HasClickHandlers getSaveBtn(){
		return btnSave;
	}
	
	public HasClickHandlers getChangePassBtn(){
		return btnChangePassword;
	}
	
	public boolean validateForm(){
		boolean result = true;
		String errorMsg = "";
		if(email.getValue().isEmpty()){
			lblEmail.setStyleName("invalidInputField");
			result = false;
		}else{
			lblEmail.setStyleName("inputField");
		}
		if(surname.getValue().isEmpty()){
			lblSurname.setStyleName("invalidInputField");
			result = false;
		}else{
			lblSurname.setStyleName("inputField");
		}
		if(forename.getValue().isEmpty()){
			lblForename.setStyleName("invalidInputField");
		}else{
			lblForename.setStyleName("inputField");
		}
		if(!result){
			errorMsg = "please, fill required fields";
		}
		lblSaveError.setText(errorMsg);
		return result;
	}
	
	public boolean validatePassword(){
		boolean result = true;
		String errorMsg = "";
		if(password.getValue().isEmpty()){
			lblPassword.setStyleName("invalidInputField");
			result = false;
		}else{
			lblPassword.setStyleName("inputField");
		}
		if(confirmPass.getValue().isEmpty()){
			lblConfirmPass.setStyleName("invalidInputField");
			result = false;
		}else{
			lblConfirmPass.setStyleName("inputField");
		}
		if(!password.getValue().isEmpty() && !confirmPass.getValue().isEmpty() && !password.getValue().equals(confirmPass.getValue())){
			if(result){
				lblPassword.setStyleName("invalidInputField");
				lblConfirmPass.setStyleName("invalidInputField");
				errorMsg ="password confirmation failed";
			}
			result = false;
		}else if(result){
			lblPassword.setStyleName("inputField");
			lblConfirmPass.setStyleName("inputField");
		}
		if(!result && errorMsg.isEmpty()){
			errorMsg = "please, fill required fields";
		}
		lblPasswordError.setText(errorMsg);
		return result;
	}
}
