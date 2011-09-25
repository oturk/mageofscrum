package hu.ideaimpl.mageofscrum.client.presenter;

import hu.ideaimpl.mageofscrum.client.service.UserService;
import hu.ideaimpl.mageofscrum.client.service.UserServiceAsync;
import hu.ideaimpl.mageofscrum.shared.UserDetails;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.HasSelectionChangedHandlers;

public class UsersPresenter implements Presenter{

	public interface Display{
		Widget asWidget();
		HasData<UserDetails> getTable();
		HasSelectionChangedHandlers getSelectionList();
		HasClickHandlers getCreateBtn();
		HasClickHandlers getDeleteBtn();
		UserDetails getSelectedRow();
	}
	
	private final Display display;
	private final UserServiceAsync service = UserService.Util.getService();
	
	public UsersPresenter(Display display) {
		this.display = display;
		renderUsers();
	}
	
	private void renderUsers() {
		AsyncDataProvider<UserDetails> provider = new AsyncDataProvider<UserDetails>() {
			@Override
			protected void onRangeChanged(HasData<UserDetails> display) {
				final Range range = display.getVisibleRange();

				service.requestRows(range.getStart(), new AsyncCallback<ArrayList<UserDetails>>() {
					public void onSuccess(ArrayList<UserDetails> result) {
						updateRowData(range.getStart(), result);
					}
					
					public void onFailure(Throwable caught) {
						
					}
				});
			}
		};
		
		provider.addDataDisplay(display.getTable());
	}

	private void bind(){
		display.getSelectionList().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				UserDetails user = display.getSelectedRow();
				if(user != null){
					loadUserDetails();
				}
			}
		});
		display.getCreateBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnCreateUser();
			}
		});
		display.getDeleteBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnUserDelete();
			}
		});
	}

	protected void doOnUserDelete() {
		display.getSelectedRow();
	}

	protected void doOnCreateUser() {
		
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
