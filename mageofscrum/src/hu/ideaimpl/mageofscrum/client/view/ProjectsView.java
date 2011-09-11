package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.presenter.ProjectPresenter.Display;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProjectsView extends Composite implements Display {

	public ProjectsView() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		
		Label lblNewLabel = new Label("You see now the projects view");
		horizontalPanel.add(lblNewLabel);
	}
	
	@Override
	public Widget asWidget(){
		return this;
	}

}
