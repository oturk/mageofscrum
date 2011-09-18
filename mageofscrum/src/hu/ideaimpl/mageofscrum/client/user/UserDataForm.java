package hu.ideaimpl.mageofscrum.client.user;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UserDataForm extends Composite {

	public UserDataForm() {
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
		
		TextBox textBoxEmail = new TextBox();
		flexTable.setWidget(1, 1, textBoxEmail);
		
		Label lblSurename = new Label("surename:");
		flexTable.setWidget(2, 0, lblSurename);
		
		TextBox surenameTextBox = new TextBox();
		flexTable.setWidget(2, 1, surenameTextBox);
		
		Label lblForename = new Label("forename:");
		flexTable.setWidget(3, 0, lblForename);
		
		TextBox forenameTextBox = new TextBox();
		flexTable.setWidget(3, 1, forenameTextBox);
		
		Label lblPassword = new Label("password:");
		flexTable.setWidget(4, 0, lblPassword);
		
		PasswordTextBox textBoxPassword = new PasswordTextBox();
		flexTable.setWidget(4, 1, textBoxPassword);
		
		Label lblConfirmPassword = new Label("confirm password:");
		flexTable.setWidget(5, 0, lblConfirmPassword);
		
		PasswordTextBox passwordConfirmTextBox = new PasswordTextBox();
		flexTable.setWidget(5, 1, passwordConfirmTextBox);
		
		Label lblZipCode = new Label("zip code:");
		flexTable.setWidget(6, 0, lblZipCode);
		
		ListBox zipCodeComboBox = new ListBox();
		flexTable.setWidget(6, 1, zipCodeComboBox);
		
		Label lblCity = new Label("city:");
		flexTable.setWidget(7, 0, lblCity);
		
		ListBox comboBox = new ListBox();
		flexTable.setWidget(7, 1, comboBox);
		
		Label lblAddress = new Label("address:");
		flexTable.setWidget(8, 0, lblAddress);
		
		TextBox addressTextBox = new TextBox();
		flexTable.setWidget(8, 1, addressTextBox);
		
		Label lblPhone = new Label("phone:");
		flexTable.setWidget(9, 0, lblPhone);
		
		TextBox phoneTextBox = new TextBox();
		flexTable.setWidget(9, 1, phoneTextBox);
	}

}
