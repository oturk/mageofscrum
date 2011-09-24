package hu.ideaimpl.mageofscrum.client.presenter;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.client.service.UserService;
import hu.ideaimpl.mageofscrum.client.service.UserServiceAsync;
import hu.ideaimpl.mageofscrum.shared.User;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.HasSelectionChangedHandlers;
import com.google.gwt.view.client.SelectionModel;

public class UsersPresenter implements Presenter{

	public interface Display{
		Widget asWidget();
		HasData<User> getList();
		HasSelectionChangedHandlers getSelectionList();
		User getSelectedRow();
	}
	
	private final Display display;
	private final UserServiceAsync service = UserService.Util.getService();
	
	public UsersPresenter(Display display) {
		this.display = display;
		renderUsers();
	}
	
	private void renderUsers() {
		AsyncDataProvider<User> provider = new AsyncDataProvider<User>() {
			@Override
			protected void onRangeChanged(HasData<User> display) {
				final Range range = display.getVisibleRange();
				service.requestRows(range, new AsyncCallback<ArrayList<User>>() {
					public void onSuccess(ArrayList<User> result) {
						updateRowData(range.getStart(), result);
					}
					
					public void onFailure(Throwable caught) {
						
					}
				});
			}
		};
		
		provider.addDataDisplay(display.getList());
	}

	private void bind(){
		display.getSelectionList().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				User user = display.getSelectedRow();
				if(user != null){
					loadUserDetails();
				}
			}
		});
		
	}

	protected void loadUserDetails() {
		
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

}
