package hu.ideaimpl.mageofscrum.client.view;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.client.resources.ListResource;
import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.ListToList;
import hu.ideaimpl.mageofscrum.client.ui.TitledPanel;
import hu.ideaimpl.mageofscrum.client.ui.forms.NewUserForm;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RolesView extends Composite implements HasInitState{

	private CellList<UserDO> usersList;
	private Button btnDelete = new Button("delete");
	private ListToList<RoleDO> listToList;
	private NewUserForm userForm = new NewUserForm();
	private AbstractCell<RoleDO> listCell = new AbstractCell<RoleDO>() {

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, RoleDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
		}
	};

	public RolesView() {
		HorizontalPanel hPanel = new HorizontalPanel();
		initWidget(hPanel);
		
		usersList = new CellList<UserDO>(new AbstractCell<UserDO>(){
			@Override
			public void render(Context context, UserDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getUsername());
			}
		}, ListResource.instance);
		
		VerticalPanel leftPanel = new VerticalPanel();
		leftPanel.add(btnDelete);
		
		TitledPanel usersPanel = new TitledPanel();
		leftPanel.add(usersPanel);
		usersPanel.setText("users");
		usersPanel.addContent(usersList);
		usersPanel.setSize("200px", "400px");
		leftPanel.add(usersPanel);
		
		hPanel.add(leftPanel);
		usersList.setSize("180px", "360px");
		
		VerticalPanel rightPanel = new VerticalPanel();
		listToList = new ListToList<RoleDO>(listCell);
		rightPanel.add(userForm);
		rightPanel.add(listToList);
		hPanel.add(rightPanel);
		listToList.setToLbl("other roles");
		listToList.setFromLbl("has role");
		listToList.setHeight(271);
		btnDelete.setText("delete user");
		btnDelete.setStyleName(Resources.instance.mosStyle().commandBtn());
		btnDelete.setHeight("30px");
	}
	
	public HasClickHandlers getCreateBtn(){
		return userForm.getAddUserBtn();
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
	
	public HasClickHandlers getChangePassBtn(){
		return userForm.getChangePassBtn();
	}
	
	public CellList<RoleDO> getToList(){
		return listToList.getToList();
	}
	
	public CellList<RoleDO> getFromList(){
		return listToList.getFromList();
	}
	
	public HasText getErrorLbl(){
		return userForm.getErrorLbl();
	}
	
	public UserDO getNewUser(){
		return userForm.getFormData();
	}
	
	public boolean validateUserForm(){
		return userForm.validate();
	}
	
	public boolean validatePassword(){
		return userForm.validatePassword();
	}
	
	public void clearUserForm(){
		userForm.clearForm();
	}

	@Override
	public void initState() {
		usersList.setRowData(new ArrayList<UserDO>());
		listToList.getFromList().setRowData(new ArrayList<RoleDO>());
		listToList.getToList().setRowData(new ArrayList<RoleDO>());
		userForm.clearForm();
	}
}
