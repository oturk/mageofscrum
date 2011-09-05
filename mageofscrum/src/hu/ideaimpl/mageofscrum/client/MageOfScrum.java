package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.user.LoginForm;
import hu.ideaimpl.mageofscrum.client.user.LoginForm;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class MageOfScrum implements EntryPoint {
	private static final String loggedInUser = null;
	private LoginForm loginForm = new LoginForm();
	
	@Override
	public void onModuleLoad() {
		final Label label = new Label("Before login");
		RootLayoutPanel root = RootLayoutPanel.get();
		if(loggedInUser == null){
			root.add(loginForm);
		}else{
			label.setText("You are logged in "+loggedInUser);
			root.add(label);
		}
	}

}

