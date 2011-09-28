package hu.ideaimpl.mageofscrum.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class MageOfScrum implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
//		RootLayoutPanel container = RootLayoutPanel.get();
//		SecurityServiceAsync securityService = GWT.create(SecurityService.class);
//		HandlerManager eventBus = new HandlerManager(null);
//		LoginController controller = new LoginController(clientFactory);
//		controller.go(clientFactory.getContainer());
		
		AppController appController = new AppController(clientFactory);
		appController.go(clientFactory.getContainer());
	}
	
}

