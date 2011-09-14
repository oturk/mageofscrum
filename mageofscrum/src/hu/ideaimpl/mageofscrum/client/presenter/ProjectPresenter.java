package hu.ideaimpl.mageofscrum.client.presenter;

import hu.ideaimpl.mageofscrum.client.security.SecurityServiceAsync;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class ProjectPresenter implements Presenter{
	
	public interface Display{
//		Widget getUserMenuList();
		Widget asWidget();
	}
	
	public ProjectPresenter(SecurityServiceAsync securityService, HandlerManager eventBus, Display display) {
		this.securityService = securityService;
		this.eventBus = eventBus;
		this.display = display;
	}

	private final SecurityServiceAsync securityService;
	private final HandlerManager eventBus; 
	private Display display;
	
	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
		fillUserMenuList();
		
	}

	private void fillUserMenuList() {
//		final ListBox listBox = (ListBox) display.getUserMenuList();
//		securityService.getEmail(new AsyncCallback<String>() {
//			
//			@Override
//			public void onSuccess(String result) {
//				if(result != null){
//					listBox.addItem(result);
//					listBox.addItem("profile");
//					listBox.addItem("logout");
//				}
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				//TODO ha a kérés nem sikerül akkor dobjon egy login event-et
//			}
//		});
		
	}

}
