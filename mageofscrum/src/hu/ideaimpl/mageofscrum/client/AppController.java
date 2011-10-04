package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.event.LogoutEvent;
import hu.ideaimpl.mageofscrum.client.event.LogoutHandler;
import hu.ideaimpl.mageofscrum.client.event.UserLoggedInEvent;
import hu.ideaimpl.mageofscrum.client.event.UserLoggedInHandler;
import hu.ideaimpl.mageofscrum.client.presenter.MainPresenter;
import hu.ideaimpl.mageofscrum.client.presenter.Presenter;
import hu.ideaimpl.mageofscrum.client.presenter.UsersPresenter;
import hu.ideaimpl.mageofscrum.client.service.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.view.UsersView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private HasWidgets container;
	private final ClientFactory clientFactory;
	private final HandlerManager eventBus;
	private final SecurityServiceAsync securityService;
	private MainPresenter mainPresenter = null;
	
	public AppController(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
//		this.securityService = securityService;
		this.eventBus = clientFactory.getEventBus();
		this.securityService = clientFactory.getSecurityService();
		bind();
	}
	
	private void bind(){
		History.addValueChangeHandler(this);
		
		eventBus.addHandler(UserLoggedInEvent.TYPE, new UserLoggedInHandler() {
			public void onUserLoggedIn(UserLoggedInEvent event) {
				doOnUserLoggedIn();
			}
		});
		eventBus.addHandler(LogoutEvent.TYPE, new LogoutHandler() {
			public void onLoggedOut(LogoutEvent event) {
				doOnUserLogout();
			}
		});
	}
	
	private void doOnUserLogout() {
		securityService.logoutUser(new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				History.newItem("login");
			}

			@Override
			public void onFailure(Throwable caught) {
				History.newItem("error");
			}
		});
	}

	protected void doOnUserLoggedIn() {
		History.newItem("projects");
//		AppController appController = new AppController(securityService, eventBus);
//		appController.go(container);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if(token != null){
			if ("login".equals(token) || "forgotPassword".equals(token)) {
				beforeLogin(token);
			}else{
				afterLogin(token);
			}
		}
	}
	
	private void afterLogin(String token){
		mainPresenter = new MainPresenter(clientFactory.getMainView(), eventBus);
		mainPresenter.go(container);
		
		Presenter presenter = null;
		if ("projects".equals(token)) {
			clientFactory.getContentPanel().clear();
//			presenter = new ProjectPresenter(securityService, eventBus,
//					new ProjectsView());
		}else if("backlog".equals(token)){
			
		}else if("sprint".equals(token)){
			
		}else if("users".equals(token)){
			presenter = new UsersPresenter(new UsersView());
//			presenter.go(clientFactory.getContentPanel());
		}else if("teams".equals(token)){
			
		}
		if (presenter != null) {
			presenter.go(clientFactory.getContentPanel());
		}
	}
	
	private void beforeLogin(String token){
		Presenter presenter = null;
		if("login".equals(token)){
//			presenter = new WelcomePresenter(securityService, eventBus,
//					new WelcomeView(), LoginFormType.LOGIN);
//			presenter.go(container);
		}else if("forgotPassword".equals(token)){
//			presenter = new WelcomePresenter(securityService, eventBus,
//					new WelcomeView(),LoginFormType.FORGOT);
//			presenter.go(container);
		}
		if(presenter != null){
			presenter.go(container);
		}
	}
	
	private void admitUser(){
		securityService.isAuthenticated(new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				String token = History.getToken();
				if(result && token != null && !token.isEmpty()){
					History.fireCurrentHistoryState();
				}else if(result && (token == null || token.isEmpty())){
					History.newItem("projects");
				}else{
					History.newItem("login");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				History.newItem("error");
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		admitUser();
	}

}
