package hu.ideaimpl.mageofscrum.client.presenter;

import hu.ideaimpl.mageofscrum.client.event.LogoutEvent;
import hu.ideaimpl.mageofscrum.client.security.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.user.UserDataForm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ProjectPresenter implements Presenter {

	public interface Display {
		// Widget getUserMenuList();
		HasClickHandlers getLogoutButton();
		HasClickHandlers getManageAccountButton();
		HasWidgets getContentPanel();
		Widget asWidget();
	}

	public ProjectPresenter(SecurityServiceAsync securityService,
			HandlerManager eventBus, Display display) {
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
		display.getLogoutButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogoutEvent());
			}
		});
		display.getManageAccountButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.getContentPanel().clear();
				display.getContentPanel().add(new UserDataForm());
			}
		});
	}

	private void fillUserMenuList() {
		// final ListBox listBox = (ListBox) display.getUserMenuList();
		// securityService.getEmail(new AsyncCallback<String>() {
		//
		// @Override
		// public void onSuccess(String result) {
		// if(result != null){
		// listBox.addItem(result);
		// listBox.addItem("profile");
		// listBox.addItem("logout");
		// }
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// //TODO ha a kérés nem sikerül akkor dobjon egy login event-et
		// }
		// });

	}

}
