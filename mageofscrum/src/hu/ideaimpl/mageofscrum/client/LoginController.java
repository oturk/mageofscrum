package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.event.LogoutEvent;
import hu.ideaimpl.mageofscrum.client.event.LogoutHandler;
import hu.ideaimpl.mageofscrum.client.event.UserLoggedInEvent;
import hu.ideaimpl.mageofscrum.client.event.UserLoggedInHandler;
import hu.ideaimpl.mageofscrum.client.presenter.Presenter;
import hu.ideaimpl.mageofscrum.client.presenter.WelcomePresenter;
import hu.ideaimpl.mageofscrum.client.service.SecurityService;
import hu.ideaimpl.mageofscrum.client.service.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.user.LoginFormType;
import hu.ideaimpl.mageofscrum.client.view.WelcomeView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class LoginController implements Presenter, ValueChangeHandler<String> {
	private final SecurityServiceAsync securityService = SecurityService.Util.getService();
	private final HandlerManager eventBus;
	private HasWidgets container;

	public LoginController(ClientFactory clientFactory) {
		this.eventBus = clientFactory.getEventBus();
		bind();
	}

	private void bind() {
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
//		History.newItem("projects");
//		AppController appController = new AppController(securityService, eventBus);
//		appController.go(container);
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		securityService.isAuthenticated(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					doOnUserLoggedIn();
				} else {
					History.newItem("login");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				History.newItem("welcome");
			}
		});
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			Presenter presenter = null;
			if ("login".equals(token)) {
//				presenter = new WelcomePresenter(securityService, eventBus,
//						new WelcomeView(), LoginFormType.LOGIN);
			}else if("forgotPassword".equals(token)){
//				presenter = new WelcomePresenter(securityService, eventBus,
//						new WelcomeView(),LoginFormType.FORGOT);
			}
//			else if ("projects".equals(token)) {
//				presenter = new ProjectPresenter(securityService, eventBus,
//						new ProjectsView());
//				AppController appController = new AppController(securityService, eventBus);
//				appController.go(container);
//			}
//			else {
//				presenter = new WelcomePresenter(securityService, eventBus,
//						new WelcomeView(),LoginFormType.LOGIN);
//			}
			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

}
