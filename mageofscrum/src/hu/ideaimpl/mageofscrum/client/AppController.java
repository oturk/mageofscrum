package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.presenter.MainPresenter;
import hu.ideaimpl.mageofscrum.client.presenter.Presenter;
import hu.ideaimpl.mageofscrum.client.presenter.UsersPresenter;
import hu.ideaimpl.mageofscrum.client.security.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.view.MainView;
import hu.ideaimpl.mageofscrum.client.view.UsersView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private HasWidgets container;
	private final HandlerManager eventBus;
	private final SecurityServiceAsync securityService;
	private MainPresenter mainPresenter = null;
	
	public AppController(SecurityServiceAsync securityService, HandlerManager eventBus) {
		this.securityService = securityService;
		this.eventBus = eventBus;
		bind();
	}
	
	private void bind(){
		History.addValueChangeHandler(this);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		if (mainPresenter == null) {
			mainPresenter = new MainPresenter(new MainView(), eventBus);
			mainPresenter.go(container);
		}
		String token = event.getValue();
		if(token != null){
			Presenter presenter = null;
			if ("projects".equals(token)) {
//				presenter = new ProjectPresenter(securityService, eventBus,
//						new ProjectsView());
			}else if("backlog".equals(token)){
				
			}else if("sprint".equals(token)){
				
			}else if("users".equals(token)){
				presenter = new UsersPresenter(new UsersView());
			}else if("teams".equals(token)){
				
			}
			if (presenter != null) {
				presenter.go(mainPresenter.getContentPanel());
			}
		}
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		History.newItem("projects");
//		this.container.clear();
//		this.container.add(new Label("projects"));
	}

}
