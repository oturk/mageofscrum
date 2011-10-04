package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.mvp.*;
import hu.ideaimpl.mageofscrum.client.place.*;
import hu.ideaimpl.mageofscrum.client.service.SecurityService;
import hu.ideaimpl.mageofscrum.shared.Errors;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class MageOfScrum implements EntryPoint {
	public static final String DEFAULT_LINK = "A37FD56CD2398CFA";
	
	private PlaceHistoryHandler historyHandler;

	@Override
	public void onModuleLoad() {
//		ClientFactory clientFactory = GWT.create(ClientFactory.class);
////		RootLayoutPanel container = RootLayoutPanel.get();
////		SecurityServiceAsync securityService = GWT.create(SecurityService.class);
////		HandlerManager eventBus = new HandlerManager(null);
////		LoginController controller = new LoginController(clientFactory);
////		controller.go(clientFactory.getContainer());
//		
//		AppController appController = new AppController(clientFactory);
//		appController.go(clientFactory.getContainer());
//		RootLayoutPanel.get().add(new MageOfScrumShell());
//		placeAndActivity();
		checkAuthentication();
	}
	
	private void checkAuthentication() {
		SecurityService.Util.getService().isAuthenticated(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					ClientFactory.Util.getClientFactory().getMageOfScrumApp().run(RootLayoutPanel.get());
				} else {
					RootLayoutPanel.get().add(new SecurityShell());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
//				placeController.goTo(new ErrorPlace(Errors.SERVICE_UNAVAILABLE));
				Window.alert("Something goes wrong!");
			}
		});
	}
	
	private void placeAndActivity(){
		SimplePanel appWidget = new SimplePanel();
		Place defaultPlace = new WelcomePlace(); 
		
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEBus();
		final PlaceController placeController = clientFactory.getPlaceController();

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(appWidget);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
		historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootLayoutPanel.get().add(appWidget);
		// Goes to place represented on URL or default place
		SecurityService.Util.getService().isAuthenticated(new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(result){
					historyHandler.handleCurrentHistory();
				}else{
					placeController.goTo(new WelcomePlace());
//					placeController.goTo(new ErrorPlace(Errors.SERVICE_UNAVAILABLE));
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				placeController.goTo(new ErrorPlace(Errors.SERVICE_UNAVAILABLE));
			}
		});
	}
	
}

