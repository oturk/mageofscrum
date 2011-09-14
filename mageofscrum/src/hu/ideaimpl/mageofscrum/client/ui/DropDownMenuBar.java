package hu.ideaimpl.mageofscrum.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;

public class DropDownMenuBar extends Composite {

	private MenuBar menuBar = new MenuBar(true);

	public DropDownMenuBar() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		horizontalPanel.setSize(Integer.toString(Window.getClientWidth())+"px", "40");
		
		horizontalPanel.add(menuBar);
		menuBar.setSize("150", "40");
		
		MenuItem mntmLogout = new MenuItem("logout", false, (Command) null);
		menuBar.addItem(mntmLogout);
	}
	
	public void addMenuItem(MenuItem menuItem){
		menuBar.addItem(menuItem);
	}

}
