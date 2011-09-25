package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.presenter.MainPresenter.Display;
import hu.ideaimpl.mageofscrum.client.ui.MenuBar;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Image;

public class MainView extends Composite implements Display{
	private final int clientWidth = Window.getClientWidth();
	private final int clientHeight = Window.getClientHeight();
	private final AbsolutePanel headerPanel = new AbsolutePanel();
	private final HorizontalPanel horizontalPanel = new HorizontalPanel();
	private final MenuBar wlcMenuBar = new MenuBar();
	private final AbsolutePanel contentPanel = new AbsolutePanel();
	private final Image image = new Image("mageofscrum/images/logo.png");

	public MainView() {
		
		AbsolutePanel absolutPanel = new AbsolutePanel();
		initWidget(absolutPanel);
		absolutPanel.setSize("100%", "100%");
		headerPanel.setStyleName("headerPanel");
		
		absolutPanel.add(headerPanel);
		headerPanel.setSize(Integer.toString(clientWidth-20)+"px", "100px");
		
		headerPanel.add(image, 0, 21);
		
		absolutPanel.add(horizontalPanel);
		horizontalPanel.setSize("", "");
		
		horizontalPanel.add(wlcMenuBar);
		contentPanel.setStyleName("contentPanel");
//		contentPanel.setStylePrimaryName("contentPanel");
		
		horizontalPanel.add(contentPanel);
		contentPanel.setSize(Integer.toString(clientWidth)+"px", Integer.toString(clientHeight)+"px");
	}
	
	@Override
	public MenuBar getWlcMenuBar() {
		return wlcMenuBar;
	}

	@Override
	public Widget asWidget(){
		return this;
	}
	
	@Override
	public HasWidgets getContentPanel(){
		return contentPanel;
	}

	@Override
	public void showAdminMenuBar() {
		wlcMenuBar.showAdminMenuBar();
	}

	@Override
	public void showUserMenuBar() {
		wlcMenuBar.showUserMenuBar();
	}
}
