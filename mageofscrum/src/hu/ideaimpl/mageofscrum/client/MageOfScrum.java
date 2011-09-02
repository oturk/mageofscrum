package hu.ideaimpl.mageofscrum.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class MageOfScrum implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootLayoutPanel root = RootLayoutPanel.get();
		Label label = new Label("Some text");
		root.add(label);
		root.add(new Label("Hello git clone again"));
	}

}
