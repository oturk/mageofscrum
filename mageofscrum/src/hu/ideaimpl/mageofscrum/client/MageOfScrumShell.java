package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.Header;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;

public class MageOfScrumShell extends Composite{

	private SimplePanel contentPanel = new SimplePanel();
	
	public MageOfScrumShell() {
		
		SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
		initWidget(splitLayoutPanel);
		
		Header header = new Header(Resources.Util.getResources().mageofscrumLogo());
		splitLayoutPanel.addNorth(header, 100);
		
		splitLayoutPanel.addWest(ClientFactory.Util.getClientFactory().getMenuBar(), 150);
		contentPanel.setStyleName("contentPanel");
		splitLayoutPanel.add(contentPanel);
	}

	public SimplePanel getContentPanel() {
		return contentPanel;
	}

	public HasClickHandlers getProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public HasClickHandlers getBacklog() {
		// TODO Auto-generated method stub
		return null;
	}

	public HasClickHandlers getSprint() {
		// TODO Auto-generated method stub
		return null;
	}

	public HasClickHandlers getProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	public HasClickHandlers getLogout() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
