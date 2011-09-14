package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.presenter.ProjectPresenter.Display;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.VerticalPanel;
import hu.ideaimpl.mageofscrum.client.user.UserToolbar;

public class ProjectsView extends Composite implements Display {

	private UserToolbar userToolbar = new UserToolbar();

	public ProjectsView() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		verticalPanel.add(userToolbar);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		
		Label lblNewLabel = new Label("You see now the projects view");
		horizontalPanel.add(lblNewLabel);
	}
	
	@Override
	public Widget asWidget(){
		return this;
	}

//	@Override
//	public Widget getUserMenuList() {
//		return userToolbar.getUserMenuList();
//	}

}
