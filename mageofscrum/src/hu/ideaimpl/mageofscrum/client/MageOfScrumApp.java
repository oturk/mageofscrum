package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.mvp.AppActivityMapper;
import hu.ideaimpl.mageofscrum.client.mvp.AppPlaceHistoryMapper;
import hu.ideaimpl.mageofscrum.client.place.BacklogPlace;
import hu.ideaimpl.mageofscrum.client.place.ProfilePlace;
import hu.ideaimpl.mageofscrum.client.place.ProjectPlace;
import hu.ideaimpl.mageofscrum.client.place.RolePlace;
import hu.ideaimpl.mageofscrum.client.place.SprintPlace;
import hu.ideaimpl.mageofscrum.client.place.TeamPlace;
import hu.ideaimpl.mageofscrum.client.service.SecurityService;
import hu.ideaimpl.mageofscrum.client.ui.MenuBar;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;


public class MageOfScrumApp {
	
	private PlaceHistoryHandler historyHandler;
	private PlaceController placeController;
	private MageOfScrumShell mageOfScrumShell;
	private MenuBar menuBar = ClientFactory.Util.getClientFactory().getMenuBar();
	
	public MageOfScrumApp(MageOfScrumShell mageOfScrumShell) {
		this.mageOfScrumShell = mageOfScrumShell;
		bind();
	}
	
	private void bind(){
		menuBar.getLogout().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnUserLogout();
			}
		});
		menuBar.getTeams().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				placeController.goTo(new TeamPlace());
			}
		});
		menuBar.getRoles().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				placeController.goTo(new RolePlace());
			}
		});
		menuBar.getProfile().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				placeController.goTo(new ProfilePlace());
			}
		});
		menuBar.getProjects().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				placeController.goTo(new ProjectPlace());
			}
		});
		menuBar.getBacklog().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				placeController.goTo(new BacklogPlace());
			}
		});
		menuBar.getSprint().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				placeController.goTo(new SprintPlace());
			}
		});
	}
	
	public void run(HasWidgets container){
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEBus();
		placeController = clientFactory.getPlaceController();

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(mageOfScrumShell.getContentPanel());

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
		historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, new TeamPlace());

//		bind();
		
		container.clear();
		container.add(mageOfScrumShell);
		historyHandler.handleCurrentHistory();
	}
	
	private void doOnUserLogout() {
		SecurityService.Util.getService().logoutUser(new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
//				Window.Location.reload();
				Window.Location.replace("/mageofscrum/mageofscrum.html");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Something goes wrong!");
			}
		});
	}

}
