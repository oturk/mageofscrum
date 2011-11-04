package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.resources.MosStyle;
import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.TitledPanel;
import hu.ideaimpl.mageofscrum.client.ui.fields.InputField;
import hu.ideaimpl.mageofscrum.client.ui.fields.PasswordInputField;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfileView extends Composite {

	private InputField email = new InputField();
	private InputField surname = new InputField();
	private InputField forename = new InputField();
	private PasswordInputField password = new PasswordInputField();
	private PasswordInputField confirmPass = new PasswordInputField();
	private Button btnSave = new Button("save");
	private Button btnChangePassword = new Button("change password");
	private Label lblSaveError = new Label("");
	private Label lblPasswordError = new Label("");

	public ProfileView() {
		MosStyle style = Resources.instance.mosStyle();
		style.ensureInjected();
		
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		
		VerticalPanel userPanel = new VerticalPanel();
		userPanel.setSpacing(1);
		lblSaveError.setStyleName(style.errorLbl());
		userPanel.add(lblSaveError);
		lblSaveError.setSize("263px", "30px");
		
		email.setText("email:");
		email.setRequired(true);
		surname.setRequired(true);
		surname.setText("surname:");
		forename.setRequired(true);
		forename.setText("forename:");
		password.setRequired(true);
		password.setText("password:");
		confirmPass.setRequired(true);
		confirmPass.setText("confirm password:");
		userPanel.add(email);
		userPanel.add(surname);
		userPanel.add(forename);
		btnSave.setStyleName(style.commandBtn());
		userPanel.add(btnSave);
		
		TitledPanel userTitledPanel = new TitledPanel();
		vPanel.add(userTitledPanel);
		userTitledPanel.setText("personal data");
		userTitledPanel.addContent(userPanel);
		userTitledPanel.setSize("280px", "220px");
		
		VerticalPanel passwordPanel = new VerticalPanel();
		passwordPanel.setSpacing(1);
		
		lblPasswordError.setStyleName(style.errorLbl());
		passwordPanel.add(lblPasswordError);
		lblPasswordError.setSize("265px", "30px");
		
		passwordPanel.add(password);
		
		passwordPanel.add(confirmPass);
		btnChangePassword.setStyleName(style.commandBtn());
		passwordPanel.add(btnChangePassword);
		
		TitledPanel passTitledPanel = new TitledPanel();
		vPanel.add(passTitledPanel);
		passTitledPanel.setText("change password");
		passTitledPanel.addContent(passwordPanel);
		passTitledPanel.setSize("280px", "200px");
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
		if(!email.validate()){
			result = false;
		}
		if(!surname.validate()){
			result = false;
		}
		if(!forename.validate()){
			result = false;
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
		if(!password.validate()){
			result = false;
		}
		if(!confirmPass.validate()){
			result = false;
		}
		if(!password.getValue().isEmpty() && !confirmPass.getValue().isEmpty() && !password.getValue().equals(confirmPass.getValue())){
			if(result){
				password.setValidState(false);
				confirmPass.setValidState(false);
				errorMsg ="password confirmation failed";
			}
			result = false;
		}else if(result){
			password.setValidState(true);
			password.setValidState(true);
		}
		if(!result && errorMsg.isEmpty()){
			errorMsg = "please, fill required fields";
		}
		lblPasswordError.setText(errorMsg);
		return result;
	}
}
