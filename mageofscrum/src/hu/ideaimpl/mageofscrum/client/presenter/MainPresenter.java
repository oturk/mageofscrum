package hu.ideaimpl.mageofscrum.client.presenter;

import hu.ideaimpl.mageofscrum.client.event.LogoutEvent;
import hu.ideaimpl.mageofscrum.client.security.SecurityService;
import hu.ideaimpl.mageofscrum.client.ui.MenuBar;
import hu.ideaimpl.mageofscrum.shared.Roles;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MainPresenter implements Presenter {

	public interface Display {
		Widget asWidget();
		HasWidgets getContentPanel();
		MenuBar getWlcMenuBar();
		void showAdminMenuBar();
		void showUserMenuBar();
	}
	
	private Display display;
	private final HandlerManager eventBus;

	public MainPresenter(Display display, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
		SecurityService.Util.getService().getRole(new AsyncCallback<Roles>() {
			public void onSuccess(Roles role) {
				if(role == Roles.ADMIN){
					MainPresenter.this.display.showAdminMenuBar();
				}else if(role == Roles.OWNER){
					MainPresenter.this.display.showAdminMenuBar();
				}else if(role == Roles.MASTER){
					MainPresenter.this.display.showUserMenuBar();
				}else if(role == Roles.USER){
					MainPresenter.this.display.showUserMenuBar();
				}else if(role == Roles.UNKNOWN){
					MainPresenter.this.display.showUserMenuBar();
				}
			}
			
			public void onFailure(Throwable caught) {
				//TODO show error page
			}
		});
	}

	private void bind() {
		bindMenuBar();
	}
	
	private void bindMenuBar(){
		display.getWlcMenuBar().getProjects().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("projects");
			}
		});
		display.getWlcMenuBar().getBacklog().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("backlog");
			}
		});
		display.getWlcMenuBar().getSprint().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("sprint");
			}
		});
		display.getWlcMenuBar().getUsers().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("users");
			}
		});
		display.getWlcMenuBar().getTeams().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("teams");
			}
		});
		display.getWlcMenuBar().getLogout().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogoutEvent());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}
	
	public HasWidgets getContentPanel(){
		return display.getContentPanel();
	}

}
