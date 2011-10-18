package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.service.SecurityService;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class MageOfScrum implements EntryPoint {
	public static final String DEFAULT_LINK = "A37FD56CD2398CFA";
	
	@Override
	public void onModuleLoad() {
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
				Window.alert("Something goes wrong!");
			}
		});
	}
	
}

