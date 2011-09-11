package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.presenter.WelcomePresenter.Display;
import hu.ideaimpl.mageofscrum.client.user.LoginForm;
import hu.ideaimpl.mageofscrum.client.welcome.WlcMenuBar;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WelcomeView extends Composite implements Display{

	private WlcMenuBar wlcMenuBar = new WlcMenuBar();
	private HorizontalPanel contentPanel = new HorizontalPanel();
	private LoginForm loginForm = new LoginForm();

	public WelcomeView() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		horizontalPanel.setSize("100%", "100%");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		horizontalPanel.add(verticalPanel);
		verticalPanel.setSize("", "");
		
		verticalPanel.add(loginForm);
		loginForm.setSize("100%", "100%");
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_1);
		horizontalPanel_1.setSize("100%", "100%");
		
		horizontalPanel_1.add(wlcMenuBar);
		contentPanel.setStyleName("contentPanel");
		
		horizontalPanel_1.add(contentPanel);
		contentPanel.setSize("100%", "100%");
	}

	@Override
	public HasClickHandlers getWelcomeMenuItem() {
		return wlcMenuBar.getWelcomeBtn();
	}

	@Override
	public HasClickHandlers getAboutMenuItem() {
		return wlcMenuBar.getAboutBtn();
	}

	@Override
	public HasClickHandlers getSendFeedbackMenuItem() {
		return wlcMenuBar.getFeedbackBtn();
	}
	
	@Override
	public Widget asWidget(){
		return this;
	}

	@Override
	public HasWidgets getContentPanel() {
		return contentPanel;
	}

	@Override
	public HasClickHandlers getLoginButton() {
		return loginForm.getBtnLogin();
	}

	@Override
	public HasValue<String> getEmail() {
		return loginForm.getEmailTextBox();
	}

	@Override
	public HasValue<String> getPassword() {
		return loginForm.getPasswordTextBox();
	}

	@Override
	public HasText getErrorLbl() {
		return loginForm.getErrorLabel();
	}
	
}
