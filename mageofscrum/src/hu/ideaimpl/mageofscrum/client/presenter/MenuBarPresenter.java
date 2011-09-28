package hu.ideaimpl.mageofscrum.client.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasWidgets;

public class MenuBarPresenter implements Presenter {
	
	public interface Display{
		HasClickHandlers getProjectsBtn();
		HasClickHandlers getBacklogBtn();
		HasClickHandlers getSprintBtn();
		HasClickHandlers getChartsBtn();
		HasClickHandlers getLogoutBtn();
		HasClickHandlers getTeamsBtn();
		HasClickHandlers getRolesBtn();
		HasClickHandlers getUsersBtn();
	}

	public MenuBarPresenter() {
	}
	
	private void bind(){
		
	}

	@Override
	public void go(HasWidgets container) {

	}

}
