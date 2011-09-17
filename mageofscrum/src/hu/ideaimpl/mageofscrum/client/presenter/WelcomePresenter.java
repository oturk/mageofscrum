package hu.ideaimpl.mageofscrum.client.presenter;

import hu.ideaimpl.mageofscrum.client.event.UserLoggedInEvent;
import hu.ideaimpl.mageofscrum.client.security.SecurityServiceAsync;
import hu.ideaimpl.mageofscrum.client.user.LoginFormType;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class WelcomePresenter implements Presenter {

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

	private final SecurityServiceAsync securityService;
	private final HandlerManager eventBus;
	private final Display display;
	private final LoginFormType type;

	public WelcomePresenter(SecurityServiceAsync securityService,
			HandlerManager eventBus, Display display, LoginFormType type) {
		this.securityService = securityService;
		this.eventBus = eventBus;
		this.display = display;
		this.type = type;
	}

	public void bind() {
		// display.getWelcomeMenuItem().addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// HorizontalPanel content = (HorizontalPanel)
		// display.getContentPanel();
		// content.clear();
		// content.add(new WelcomeContent());
		// }
		// });
		// display.getAboutMenuItem().addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// HorizontalPanel content = (HorizontalPanel)
		// display.getContentPanel();
		// content.clear();
		// content.add(new AboutContent());
		// }
		// });
		// display.getSendFeedbackMenuItem().addClickHandler(new ClickHandler()
		// {
		// public void onClick(ClickEvent event) {
		// HorizontalPanel content = (HorizontalPanel)
		// display.getContentPanel();
		// content.clear();
		// content.add(new SendFeedbackPanel());
		// }
		// });
		display.getLoginButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginUser(display.getEmail().getValue(), display.getPassword()
						.getValue(), display.getRememberMe().getValue());
			}
		});

		display.getForgetButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				History.newItem("forgotPassword");
			}

		});
		display.getForgotSendButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				forgotPassword();
			}
		});
	}

	protected void forgotPassword() {
		securityService.forgotPassword(display.getForgotEmail().getValue(), new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				String msg = "We send your login info to your e-mail!";
				if (!result) {
					msg = "Sorry this isn't a valid e-mail!";
				}
				display.getMsgLbl().setText(msg);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				//TODO error event-et készíteni
			}
		});
	}

	protected void loginUser(String email, String password, boolean rememberMe) {
		securityService.loginUser(email, password, rememberMe,
				new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						// TODO SID-t betenni a cookiba
						if (result) {
							eventBus.fireEvent(new UserLoggedInEvent());
						} else {
							display.getErrorLbl().setText(
									"Wrong email or password!");
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						display.getErrorLbl().setText(
								"Sorry our server is temporarly unavailable!");
					}
				});
	}

	@Override
	public void go(final HasWidgets container) {
		bind();
		container.clear();
		if (type == LoginFormType.LOGIN) {
			display.showLoginForm();
		} else {
			display.showForgotPasswordForm();
		}
		container.add(display.asWidget());
	}

}
