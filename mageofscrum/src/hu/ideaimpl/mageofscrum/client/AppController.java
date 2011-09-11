package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.event.UserLoggedInEvent;
import hu.ideaimpl.mageofscrum.client.event.UserLoggedInHandler;
import hu.ideaimpl.mageofscrum.client.presenter.Presenter;
import hu.ideaimpl.mageofscrum.client.presenter.ProjectPresenter;
import hu.ideaimpl.mageofscrum.client.presenter.WelcomePresenter;
import hu.ideaimpl.mageofscrum.client.user.LoginServiceAsync;
import hu.ideaimpl.mageofscrum.client.view.ProjectsView;
import hu.ideaimpl.mageofscrum.client.view.WelcomeView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final LoginServiceAsync loginService;
	private final HandlerManager eventBus;
	private HasWidgets container;
	
	public AppController(LoginServiceAsync loginService, HandlerManager eventBus) {
		this.loginService = loginService;
		this.eventBus = eventBus;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);
		
		eventBus.addHandler(UserLoggedInEvent.TYPE, new UserLoggedInHandler() {
			
			@Override
			public void onUserLoggedIn(UserLoggedInEvent event) {
				doOnUserLoggedIn();
			}
		});
	}

	protected void doOnUserLoggedIn() {
		History.newItem("projects");
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		
		if(UserInfo.userName.isEmpty()){
			if("".equals(History.getToken())){
				History.newItem("welcome");
			}else{
				History.fireCurrentHistoryState();
			}
		}
		
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null){
			Presenter presenter = null;
			if("welcome".equals(token)){
				presenter = new WelcomePresenter(loginService, eventBus, new WelcomeView());
			}else if("projects".equals(token)){
				presenter = new ProjectPresenter(eventBus, new ProjectsView());
			}
			else{
				presenter = new WelcomePresenter(loginService, eventBus, new WelcomeView());
			}
			if(presenter != null){
				presenter.go(container);
			}
		}
	}

}
