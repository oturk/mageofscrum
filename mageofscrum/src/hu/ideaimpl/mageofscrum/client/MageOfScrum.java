package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.security.SecurityService;
import hu.ideaimpl.mageofscrum.client.security.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.view.SomeTry;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class MageOfScrum implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		RootLayoutPanel container = RootLayoutPanel.get();
		SecurityServiceAsync securityService = GWT.create(SecurityService.class);
		HandlerManager eventBus = new HandlerManager(null);
		LoginController controller = new LoginController(securityService, eventBus);
		controller.go(container);
		
//		styleTries(container);
	}
	
	private void styleTries(HasWidgets container){
		SomeTry tries = new SomeTry();
		container.add(tries);
	}

}

