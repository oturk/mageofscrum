package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.user.LoginService;
import hu.ideaimpl.mageofscrum.client.user.LoginServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class MageOfScrum implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController controller = new AppController(loginService, eventBus);
		controller.go(RootLayoutPanel.get());
	}

}

