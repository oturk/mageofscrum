package hu.ideaimpl.mageofscrum.client.presenter;

import hu.ideaimpl.mageofscrum.client.event.UserLoggedInEvent;
import hu.ideaimpl.mageofscrum.client.user.LoginServiceAsync;
import hu.ideaimpl.mageofscrum.client.welcome.AboutContent;
import hu.ideaimpl.mageofscrum.client.welcome.SendFeedbackPanel;
import hu.ideaimpl.mageofscrum.client.welcome.WelcomeContent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WelcomePresenter implements Presenter{

	public interface Display{
		HasClickHandlers getWelcomeMenuItem();
		HasClickHandlers getAboutMenuItem();
		HasClickHandlers getSendFeedbackMenuItem();
		HasClickHandlers getLoginButton();
		HasWidgets getContentPanel();
		HasValue<String> getEmail();
		HasValue<String> getPassword();
		HasText getErrorLbl();
		Widget asWidget();
	}
	
	private final LoginServiceAsync loginService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public WelcomePresenter(LoginServiceAsync loginService, HandlerManager eventBus, Display display) {
		this.loginService = loginService;
		this.eventBus = eventBus;
		this.display = display;
	}

	public void bind(){
		display.getWelcomeMenuItem().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				HorizontalPanel content = (HorizontalPanel) display.getContentPanel();
				content.clear();
				content.add(new WelcomeContent());
			}
		});
		display.getAboutMenuItem().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				HorizontalPanel content = (HorizontalPanel) display.getContentPanel();
				content.clear();
				content.add(new AboutContent());
			}
		});
		display.getSendFeedbackMenuItem().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				HorizontalPanel content = (HorizontalPanel) display.getContentPanel();
				content.clear();
				content.add(new SendFeedbackPanel());
			}
		});
		display.getLoginButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginUser(display.getEmail(), display.getPassword());
			}
		});
	}

	protected void loginUser(HasValue<String> email, HasValue<String> password) {
		loginService.getUserSID(email.getValue(), password.getValue(), new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				//TODO SID-t betenni a cookiba
				if(result){
					eventBus.fireEvent(new UserLoggedInEvent());
				}else{
					display.getErrorLbl().setText("Wrong email or password!");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				display.getErrorLbl().setText("Sorry our server is temporarly unavailable!");
			}
		});
	}

	@Override
	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}
	

}
