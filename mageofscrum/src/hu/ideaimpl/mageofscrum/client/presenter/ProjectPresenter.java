package hu.ideaimpl.mageofscrum.client.presenter;

import hu.ideaimpl.mageofscrum.client.view.ProjectsView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ProjectPresenter implements Presenter{
	
	public interface Display{
		Widget asWidget();
	}
	
	public ProjectPresenter(HandlerManager eventBus, Display display) {
		this.eventBus = eventBus;
		this.display = display;
	}

	private final HandlerManager eventBus; 
	private Display display;
	
	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
		// TODO Auto-generated method stub
		
	}

}
