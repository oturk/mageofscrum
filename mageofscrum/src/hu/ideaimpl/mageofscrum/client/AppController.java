package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.event.LogoutEvent;
import hu.ideaimpl.mageofscrum.client.event.LogoutHandler;
import hu.ideaimpl.mageofscrum.client.event.UserLoggedInEvent;
import hu.ideaimpl.mageofscrum.client.event.UserLoggedInHandler;
import hu.ideaimpl.mageofscrum.client.presenter.Presenter;
import hu.ideaimpl.mageofscrum.client.presenter.ProjectPresenter;
import hu.ideaimpl.mageofscrum.client.presenter.WelcomePresenter;
import hu.ideaimpl.mageofscrum.client.security.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.user.LoginFormType;
import hu.ideaimpl.mageofscrum.client.view.ProjectsView;
import hu.ideaimpl.mageofscrum.client.view.WelcomeView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final SecurityServiceAsync securityService;
	private final HandlerManager eventBus;
	private HasWidgets container;

	public AppController(SecurityServiceAsync securityService,
			HandlerManager eventBus) {
		this.securityService = securityService;
		this.eventBus = eventBus;
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
		History.newItem("projects");
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		securityService.isAuthenticated(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					History.newItem("projects");
				} else {
					History.newItem("login");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				History.newItem("welcome");
			}
		});

		// if(UserInfo.userName.isEmpty()){
		// if("".equals(History.getToken())){
		// History.newItem("welcome");
		// }else{
		// History.fireCurrentHistoryState();
		// }
		// }

	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;
			if ("login".equals(token)) {
				presenter = new WelcomePresenter(securityService, eventBus,
						new WelcomeView(), LoginFormType.LOGIN);
			} else if ("projects".equals(token)) {
				presenter = new ProjectPresenter(securityService, eventBus,
						new ProjectsView());
			}else if("forgotPassword".equals(token)){
				presenter = new WelcomePresenter(securityService, eventBus,
						new WelcomeView(),LoginFormType.FORGOT);
			}
			else {
				presenter = new WelcomePresenter(securityService, eventBus,
						new WelcomeView(),LoginFormType.LOGIN);
			}
			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

}
