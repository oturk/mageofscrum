package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.presenter.Presenter;
import hu.ideaimpl.mageofscrum.client.presenter.ProjectPresenter;
import hu.ideaimpl.mageofscrum.client.security.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.view.ProjectsView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private HasWidgets container;
	private final HandlerManager eventBus;
	private final SecurityServiceAsync securityService;
	
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
		String token = event.getValue();
		if(token != null){
			Presenter presenter = null;
			if ("projects".equals(token)) {
				presenter = new ProjectPresenter(securityService, eventBus,
						new ProjectsView());
			}
			if (presenter != null) {
				presenter.go(container);
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
