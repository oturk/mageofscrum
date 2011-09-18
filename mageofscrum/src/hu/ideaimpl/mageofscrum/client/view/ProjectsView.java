package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.presenter.ProjectPresenter.Display;
import hu.ideaimpl.mageofscrum.client.welcome.WlcMenuBar;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProjectsView extends Composite implements Display {

	private WlcMenuBar wlcMenuBar;
	private HorizontalPanel contentPanel = new HorizontalPanel();

	public ProjectsView() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		
		wlcMenuBar = new WlcMenuBar();
		wlcMenuBar.setCanShowMainBar();
		horizontalPanel.add(wlcMenuBar);
		
		horizontalPanel.add(contentPanel);
		contentPanel.setSize("100%", "100%");
	}
	
	@Override
	public Widget asWidget(){
		return this;
	}

	@Override
	public HasClickHandlers getLogoutButton() {
		return wlcMenuBar.getLogout();
	}

	@Override
	public HasClickHandlers getManageAccountButton() {
		return wlcMenuBar.getManageAcc();
	}
	
	@Override
	public HasWidgets getContentPanel(){
		return contentPanel;
	}

//	@Override
//	public Widget getUserMenuList() {
//		return userToolbar.getUserMenuList();
//	}

}
