package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.place.ErrorPlace;
import hu.ideaimpl.mageofscrum.client.service.SecurityService;
import hu.ideaimpl.mageofscrum.client.view.WelcomeView;
import hu.ideaimpl.mageofscrum.shared.Errors;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class WelcomeActivity extends AbstractActivity {

	public interface Display {
		HasClickHandlers getLoginButton();
		HasClickHandlers getForgetButton();
		HasClickHandlers getForgotSendButton();
		HasWidgets getFormPanel();
		HasValue<String> getEmail();
		HasValue<String> getPassword();
		HasValue<String> getForgotEmail();
		HasText getErrorLbl();
		HasText getMsgLbl();
		HasValue<Boolean> getRememberMe();
		void showLoginForm();
		void showForgotPasswordForm();
		Widget asWidget();
	}
	
	private final ClientFactory clientFactory;
	private final Display display;
	
	public WelcomeActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.display = clientFactory.getWelcomeView();
		bind();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		WelcomeView welcomeView = clientFactory.getWelcomeView();
		panel.setWidget(welcomeView);
	}
	
	private void bind() {
		display.getLoginButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginUser(display.getEmail().getValue(), display.getPassword().getValue(), display.getRememberMe().getValue());
			}
		});
		//
		// display.getForgetButton().addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// History.newItem("forgotPassword");
		// }
		//
		// });
		// display.getForgotSendButton().addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// forgotPassword();
		// }
		// });
	}

	protected void loginUser(String email, String password, Boolean rememberMe) {
		SecurityService.Util.getService().loginUser(email, password, rememberMe, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(result){
					clientFactory.getPlaceController().goTo(new ErrorPlace(Errors.UNKNOWN));
				}else{
					display.getErrorLbl().setText("Incorrect email or password!");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				clientFactory.getPlaceController().goTo(new ErrorPlace(Errors.SERVICE_UNAVAILABLE));
			}
		});
	}

}
