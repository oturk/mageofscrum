package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.resources.ListResource;
import hu.ideaimpl.mageofscrum.client.service.SecurityService;
import hu.ideaimpl.mageofscrum.client.ui.dialog.ErrorDialog;
import hu.ideaimpl.mageofscrum.shared.Roles;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class MageOfScrum implements EntryPoint {
	public static final String DEFAULT_LINK = "A37FD56CD2398CFA";
//	public static Roles role = Roles.USER;
	public static List<Roles> roles = new ArrayList<Roles>();
	@Override
	public void onModuleLoad() {
		checkAuthentication();
		ListResource.instance.cellListStyle().ensureInjected();
	}
	
	private void checkAuthentication() {
		SecurityService.Util.getService().isAuthenticated(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				updateRoles(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("authentication error", caught.getMessage());
//				Window.alert("Something goes wrong!");
			}
		});
	}
	
	protected void updateRoles(final boolean value) {
		SecurityService.Util.getService().getRoles(new AsyncCallback<ArrayList<Roles>>() {

			@Override
			public void onSuccess(ArrayList<Roles> result) {
				roles = result;
				if (value) {
					ClientFactory.Util.getClientFactory().getMageOfScrumApp().run(RootLayoutPanel.get());
				} else {
					RootLayoutPanel.get().add(new SecurityShell());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				roles = new ArrayList<Roles>();
				roles.add(Roles.USER);
				if (value) {
					ClientFactory.Util.getClientFactory().getMageOfScrumApp().run(RootLayoutPanel.get());
				} else {
					RootLayoutPanel.get().add(new SecurityShell());
				}
			}
		});
	}
	
	public static boolean hasRole(Roles role){
		for(Roles r : roles){
			if(r == role){
				return true;
			}
		}
		return false;
	}
	
}

