package hu.ideaimpl.mageofscrum.client.user;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;

public class UserToolbar extends Composite {
	private int width = Window.getClientWidth();
	private Command command = new Command() {
		
		@Override
		public void execute() {
			System.out.println("menu clicked!");
		}
	};
	
	public UserToolbar() {
		
		HorizontalPanel menuPanel = new HorizontalPanel();
		menuPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		menuPanel.setStyleName("loginPanel");
		initWidget(menuPanel);
		menuPanel.setSize("150px", "30px");
		
		MenuBar subMenu = new MenuBar(false);
		subMenu.addItem("logout", command);
		subMenu.setSize("150px", "30px");
		
		MenuBar menuBar = new MenuBar(false);
		menuBar.addItem("menu", subMenu);
		menuPanel.add(menuBar);
	}
	
//	public Widget getUserMenuList(){
//		return userMenuList;
//	}

}
