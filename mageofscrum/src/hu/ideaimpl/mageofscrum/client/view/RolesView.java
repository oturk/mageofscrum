package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.ui.ListToList;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class RolesView extends Composite {

	private CellList<UserDO> usersList;
	private Button btnCreate = new Button("create user");
	private Label errorLbl = new Label("");
	private TextBox userName = new TextBox();
	private TextBox surname = new TextBox();
	private TextBox forename = new TextBox();
	private PasswordTextBox password = new PasswordTextBox();
	private TextBox emailTextBox = new TextBox();
	private PasswordTextBox confirmPassword = new PasswordTextBox();
	private Label confirmRequired = new Label("*");
	private Label passwordRequired = new Label("*");
	private Label usernameRequired = new Label("*");
	private Label forenameRequired = new Label("*");
	private Label surnameRequired = new Label("*");
	private Label emailRequired = new Label("*");
	private Button btnDelete = new Button("delete");
	private CellList<RoleDO> hasRoleList;
	private CellList<RoleDO> otheRoleList;
	private ListToList listToList;
	private Label lblUsername = new Label("username:");
	private Label emailLbl = new Label("email:");
	private Label lblSurname = new Label("surname:");
	private Label lblForename = new Label("forename:");
	private Label lblPassword = new Label("password:");
	private Label lblConfirmPass = new Label("confirm pass:");

	public RolesView() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("850px", "500px");
		
		usersList = new CellList<UserDO>(new AbstractCell<UserDO>(){
			@Override
			public void render(Context context, UserDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getEmail());
			}
		});
		usersList.setStyleName("mosCellList");
		absolutePanel.add(usersList, 0, 66);
		usersList.setSize("200px", "424px");
		
		Label lblNewLabel = new Label("users");
		lblNewLabel.setStyleName("simpleLbl");
		absolutePanel.add(lblNewLabel, 0, 36);
		lblNewLabel.setSize("199px", "30px");
		
		hasRoleList = new CellList<RoleDO>(new AbstractCell<RoleDO>() {
			@Override
			public void render(Context context, RoleDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
			}
		});
		
		otheRoleList = new CellList<RoleDO>(new AbstractCell<RoleDO>() {
			@Override
			public void render(Context context, RoleDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
			}
		});
		listToList = new ListToList(hasRoleList,otheRoleList);
		listToList.setFromLbl("other roles");
		listToList.setToLbl("has role");
		absolutePanel.add(listToList, 250, 229);
		listToList.setHeight(271);
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel.add(absolutePanel_1, 250, 0);
		absolutePanel_1.setSize("571px", "223px");
		
		lblUsername.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		lblUsername.setStyleName("loginLbl");
		absolutePanel_1.add(lblUsername, 0, 18);
		lblUsername.setSize("113px", "24px");
		
		userName.setTabIndex(1);
		absolutePanel_1.add(userName, 112, 13);
		userName.setHeight("30px");
		
		surname.setTabIndex(3);
		absolutePanel_1.add(surname, 112, 94);
		surname.setSize("", "30px");
		
		lblSurname.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		lblSurname.setStyleName("loginLbl");
		absolutePanel_1.add(lblSurname, 0, 100);
		lblSurname.setSize("113px", "24px");
		
		forename.setTabIndex(4);
		absolutePanel_1.add(forename, 112, 136);
		forename.setHeight("30px");
		
		lblForename.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		lblForename.setStyleName("loginLbl");
		absolutePanel_1.add(lblForename, 0, 142);
		lblForename.setSize("113px", "24px");
		
		password.setTabIndex(5);
		absolutePanel_1.add(password, 395, 94);
		password.setHeight("30px");
		
		lblPassword.setStyleName("loginLbl");
		lblPassword.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel_1.add(lblPassword, 283, 100);
		lblPassword.setSize("113px", "24px");
		
		errorLbl.setStyleName("errorLbl");
		absolutePanel_1.add(errorLbl, 269, 184);
		errorLbl.setHeight("24px");
		
		btnCreate.setStyleName("menuButton");
		absolutePanel_1.add(btnCreate, 132, 178);
		btnCreate.setSize("100px", "30px");
		
		lblConfirmPass.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		lblConfirmPass.setStyleName("loginLbl");
		absolutePanel_1.add(lblConfirmPass, 283, 141);
		lblConfirmPass.setSize("113px", "24px");
		
		confirmPassword.setTabIndex(6);
		absolutePanel_1.add(confirmPassword, 395, 135);
		confirmPassword.setHeight("30px");
		
		emailLbl.setStyleName("loginLbl");
		emailLbl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel_1.add(emailLbl, 0, 61);
		emailLbl.setSize("113px", "24px");
		
		emailTextBox.setTabIndex(2);
		absolutePanel_1.add(emailTextBox, 112, 55);
		emailTextBox.setSize("", "30px");
		
		emailRequired.setStyleName("requiredLbl");
		absolutePanel_1.add(emailRequired, 269, 61);
		
		surnameRequired.setStyleName("requiredLbl");
		absolutePanel_1.add(surnameRequired, 269, 104);
		surnameRequired.setSize("8px", "20px");
		
		forenameRequired.setStyleName("requiredLbl");
		absolutePanel_1.add(forenameRequired, 269, 142);
		forenameRequired.setSize("8px", "20px");
		
		usernameRequired.setStyleName("requiredLbl");
		absolutePanel_1.add(usernameRequired, 269, 18);
		usernameRequired.setSize("8px", "20px");
		
		passwordRequired.setStyleName("requiredLbl");
		absolutePanel_1.add(passwordRequired, 552, 100);
		passwordRequired.setSize("8px", "20px");
		
		confirmRequired.setStyleName("requiredLbl");
		absolutePanel_1.add(confirmRequired, 552, 142);
		confirmRequired.setSize("8px", "20px");
		
		btnDelete.setText("delete user");
		btnDelete.setStyleName("menuButton");
		absolutePanel.add(btnDelete, 0, 0);
		btnDelete.setHeight("30px");
		
		usernameRequired.setVisible(false);
		emailRequired.setVisible(false);
		surnameRequired.setVisible(false);
		forenameRequired.setVisible(false);
		passwordRequired.setVisible(false);
		confirmRequired.setVisible(false);
	}
	
	public HasClickHandlers getCreateBtn(){
		return btnCreate;
	}
	
	public HasClickHandlers getDeleteBtn(){
		return btnDelete;
	}
	
	public HasClickHandlers getAddRoleBtn(){
		return listToList.getAddBtn();
	}
	
	public HasClickHandlers getRemoveRoleBtn(){
		return listToList.getRemoveBtn();
	}
	
	public CellList<UserDO> getUsersList(){
		return usersList;
	}
	
	public CellList<RoleDO> getOthersList(){
		return otheRoleList;
	}
	
	public CellList<RoleDO> getHasRolesList(){
		return hasRoleList;
	}
	
	public HasText getErrorLbl(){
		return errorLbl;
	}
	
	public UserDO getNewUser(){
		UserDO user = new UserDO();
		user.setEmail(userName.getValue());
		user.setPassword(password.getValue());
		
		UserDataDO userData = new UserDataDO();
		userData.setEmail(emailTextBox.getValue());
		userData.setSurname(surname.getValue());
		userData.setForename(forename.getValue());
		
		user.setUserData(userData);
		return user;
	}
	
	public boolean validateUserForm(){
		String errorMsg = "";
		boolean result = true;
		if(userName.getValue() == null || userName.getValue().isEmpty()){
			lblUsername.setStyleName("invalidInputField");
			result =  false;
		}else{
			lblUsername.setStyleName("inputField");
		}
		if(emailTextBox.getValue() == null || emailTextBox.getValue().isEmpty()){
			emailLbl.setStyleName("invalidInputField");
			result = false;
		}else{
			emailLbl.setStyleName("inputField");
		}
		if(surname.getValue() == null || surname.getValue().isEmpty()){
			lblSurname.setStyleName("invalidInputField");
			result = false;
		}else{
			lblSurname.setStyleName("inputField");
		}
		if(forename.getValue() == null || forename.getValue().isEmpty()){
			lblForename.setStyleName("invalidInputField");
			result = false;
		}else{
			lblForename.setStyleName("inputField");
		}
		if(password.getValue() == null || password.getValue().isEmpty()){
			lblPassword.setStyleName("invalidInputField");
			result = false;
		}else{
			lblPassword.setStyleName("inputField");
		}
		if(confirmPassword.getValue() == null || confirmPassword.getValue().isEmpty()){
			lblConfirmPass.setStyleName("invalidInputField");
			result = false;
		}else{
			lblConfirmPass.setStyleName("inputField");
		}
		if(result && !password.getValue().isEmpty() && !confirmPassword.getValue().isEmpty() && !confirmPassword.getValue().equals(password.getValue())){
			if(result){
				lblConfirmPass.setStyleName("invalidInputField");
				lblPassword.setStyleName("invalidInputField");
				errorMsg ="password confirmation failed";
			}
			result = false;
		}else if(result){
			lblConfirmPass.setStyleName("inputField");
			lblPassword.setStyleName("inputField");
		}
		if(!result){
			errorLbl.setText(errorMsg.isEmpty() ? "please, fill the required fields" : errorMsg);
		}else{
			errorLbl.setText("");
		}
		return result;
	}
	
	public void clearUserForm(){
		userName.setValue("");
		emailTextBox.setValue("");
		surname.setValue("");
		forename.setValue("");
		password.setValue("");
		confirmPassword.setValue("");
	}
}
