package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.presenter.UsersPresenter.Display;
import hu.ideaimpl.mageofscrum.shared.UserDetails;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent.HasSelectionChangedHandlers;
import com.google.gwt.view.client.SingleSelectionModel;

public class UsersView extends Composite implements Display{
	
	private SingleSelectionModel<UserDetails> selectionModel = new SingleSelectionModel<UserDetails>();
	private final CellTable<UserDetails> cellTable = new CellTable<UserDetails>();
	private Button btnCreate = new Button("create user");
	private Button btnDelete = new Button("delete user");

	public UsersView() {
		
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setWidth("700px");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		vPanel.add(absolutePanel);
		absolutePanel.setSize("700px", "40px");
		
		btnCreate.setStyleName("menuItem");
		absolutePanel.add(btnCreate, 0, 10);
		btnCreate.setSize("100px", "30px");
		
		btnDelete.setStyleName("menuItem");
		absolutePanel.add(btnDelete, 106, 10);
		btnDelete.setSize("100px", "30px");
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		vPanel.add(horizontalPanel_1);
		horizontalPanel_1.add(cellTable);
		
		cellTable.setSelectionModel(selectionModel);
		cellTable.setSize("1000px", "100%");
		
		TextColumn<UserDetails> nicknameColumn = new TextColumn<UserDetails>() {
			@Override
			public String getValue(UserDetails object) {
				return object.getNickname();
			}
		};
		cellTable.addColumn(nicknameColumn, "nickname");
		
		TextColumn<UserDetails> nameColumn = new TextColumn<UserDetails>() {
			@Override
			public String getValue(UserDetails object) {
				return object.getFullName();
			}
		};
		cellTable.addColumn(nameColumn, "name");
		
		TextColumn<UserDetails> emailColumn = new TextColumn<UserDetails>() {
			@Override
			public String getValue(UserDetails object) {
				return object.getEmail();
			}
		};
		cellTable.addColumn(emailColumn, "email");
		
		TextColumn<UserDetails> addressColumn = new TextColumn<UserDetails>() {
			@Override
			public String getValue(UserDetails object) {
				return object.getAddress();
			}
		};
		cellTable.addColumn(addressColumn, "address");
		
		TextColumn<UserDetails> teamsColumn = new TextColumn<UserDetails>() {
			@Override
			public String getValue(UserDetails object) {
				return object.teamsToString();
			}
		};
		cellTable.addColumn(teamsColumn, "teams");
		cellTable.setColumnWidth(teamsColumn, "150px");
		
		TextColumn<UserDetails> rolesColumn = new TextColumn<UserDetails>() {
			@Override
			public String getValue(UserDetails object) {
				return object.rolesToString();
			}
		};
		cellTable.addColumn(rolesColumn, "roles");
		
	}

	@Override
	public Widget asWidget(){
		return this;
	}

	@Override
	public HasSelectionChangedHandlers getSelectionList() {
		return selectionModel;
	}

	@Override
	public UserDetails getSelectedRow() {
		return selectionModel.getSelectedObject();
	}

	@Override
	public HasData<UserDetails> getTable() {
		return cellTable;
	}
	
	@Override
	public HasClickHandlers getCreateBtn(){
		return btnCreate;
	}
	
	@Override
	public HasClickHandlers getDeleteBtn(){
		return btnDelete;
	}
}
