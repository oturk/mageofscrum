package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.presenter.UsersPresenter.Display;
import hu.ideaimpl.mageofscrum.client.user.UsersDetailsForm;
import hu.ideaimpl.mageofscrum.shared.User;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent.HasSelectionChangedHandlers;
import com.google.gwt.view.client.SingleSelectionModel;

public class UsersView extends Composite implements Display{

	private CellList<User> cellList = new CellList<User>(new AbstractCell<User>(){
		@Override
		public void render(Context context, User value, SafeHtmlBuilder sb) {
			sb.appendEscaped(value.getEmail());
		}
	});
	
	private SingleSelectionModel<User> selectionModel = new SingleSelectionModel<User>();
	private UsersDetailsForm usersDetailsForm = new UsersDetailsForm();

	public UsersView() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		
		cellList.setSelectionModel(selectionModel);
		horizontalPanel.add(cellList);
		
//		horizontalPanel.add(cellList);
		
		HorizontalPanel detailsPanel = new HorizontalPanel();
		horizontalPanel.add(detailsPanel);
		
		detailsPanel.add(usersDetailsForm);
	}

	@Override
	public HasData<User> getList() {
		return cellList;
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
	public User getSelectedRow() {
		return selectionModel.getSelectedObject();
	}
	
}
