package hu.ideaimpl.mageofscrum.client.user;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UsersDetailsForm extends Composite {

	private TextBox textBoxEmail = new TextBox();
	private TextBox fullNameTextBox = new TextBox();
	private TextBox addressTextBox = new TextBox();

	public UsersDetailsForm() {
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		
		Label lblUsersData = new Label("User's data");
		lblUsersData.setStyleName("loginHeader");
		vPanel.add(lblUsersData);
		lblUsersData.setHeight("30px");
		
		FormPanel formPanel = new FormPanel();
		vPanel.add(formPanel);
		
		FlexTable flexTable = new FlexTable();
		formPanel.setWidget(flexTable);
		flexTable.setWidth("400px");
		
		Label lblEmail = new Label("email:");
		flexTable.setWidget(1, 0, lblEmail);
		
		flexTable.setWidget(1, 1, textBoxEmail);
		
		Label lblSurename = new Label("full name:");
		flexTable.setWidget(2, 0, lblSurename);
		
		flexTable.setWidget(2, 1, fullNameTextBox);
		
		Label lblForename = new Label("address:");
		flexTable.setWidget(3, 0, lblForename);
		
		flexTable.setWidget(3, 1, addressTextBox);
	}

	public HasValue<String> getEmailTextBox(){
		return textBoxEmail;
	}
	
	public HasValue<String> getAddressTextBox(){
		return addressTextBox;
	}
	
	public HasValue<String> getFullNameTextBox(){
		return fullNameTextBox;
	}
}
