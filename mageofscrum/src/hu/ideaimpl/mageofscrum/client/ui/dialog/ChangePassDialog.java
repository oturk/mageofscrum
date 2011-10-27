package hu.ideaimpl.mageofscrum.client.ui.dialog;

import hu.ideaimpl.mageofscrum.client.ui.DialogPanel;
import hu.ideaimpl.mageofscrum.client.ui.fields.HasValidator;
import hu.ideaimpl.mageofscrum.client.ui.fields.PasswordInputField;
import hu.ideaimpl.mageofscrum.client.ui.forms.HasForm;

import com.google.gwt.user.client.ui.DialogBox;

public class ChangePassDialog extends DialogBox implements HasForm<String>, HasValidator {

	private PasswordInputField password = new PasswordInputField();
	private PasswordInputField confirm = new PasswordInputField();
	
	public ChangePassDialog() {
		setGlassEnabled(true);
		setGlassStyleName("mosDialogGlass");
		setStyleName("");
		
		DialogPanel dialogPanel = new DialogPanel();
		dialogPanel.setText("change password");
		dialogPanel.setSize(300, 200);
		
		password.setText("password:");
		confirm.setText("confirm:");
		dialogPanel.add(password);
		dialogPanel.add(confirm);
		
		setWidget(dialogPanel);
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clearForm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFormData(String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getFormData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValidState(boolean isValid) {
		// TODO Auto-generated method stub
		
	}

	
}
