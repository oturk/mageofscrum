package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.presenter.WelcomePresenter.Display;
import hu.ideaimpl.mageofscrum.client.ui.Header;
import hu.ideaimpl.mageofscrum.client.user.ForgotPasswordForm;
import hu.ideaimpl.mageofscrum.client.user.LoginForm2;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WelcomeView extends Composite implements Display{

	private final int clientHeight = Window.getClientHeight();
	private LoginForm2 loginForm = new LoginForm2();
	private ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
	private HorizontalPanel formPanel = new HorizontalPanel();

	public WelcomeView() {
		
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setSize("100%", "100%");
		
		Header header = new Header();
		vPanel.add(header);
		
		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		vPanel.add(horizontalPanel_2);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setStyleName("welcomeContent");
		horizontalPanel_2.add(horizontalPanel_1);
		horizontalPanel_1.setSize("700px", "100%");
		
		InlineHTML nlnhtmlNewInlinehtml = new InlineHTML("<style type='text/css'>h2, a{color:#73A0C5;}</style><div id='content'><h2>Scrum project management</h2><p>Scrum is an iterative, incremental framework for project management often"+ 
	"seen in agile software development, a type of software engineering.</p><p>Although the Scrum approach was originally suggested for managing product"+ 
	"development projects, its use has focused on the management of software development projects, and it can be used to run software maintenance teams"+ 
	"or as a general project/program management approach.<a href='http://en.wikipedia.org/wiki/Scrum_(development)'>wiki</a></p>"+
	"<img src='./mageofscrum/images/500px-Scrum_process.png' alt='Scrum process'/></div>");
		horizontalPanel_1.add(nlnhtmlNewInlinehtml);
		nlnhtmlNewInlinehtml.setSize("570px", "");
		
		HorizontalPanel spacerPanel = new HorizontalPanel();
		horizontalPanel_2.add(spacerPanel);
		spacerPanel.setWidth("245px");
		horizontalPanel_2.add(formPanel);
		formPanel.setStyleName("loginPanel");
		
		formPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		formPanel.setSize("250px", Integer.toString(clientHeight)+"px");
		formPanel.add(loginForm);
	}
	
//	@Override
//	public HasClickHandlers getWelcomeMenuItem() {
//		return wlcMenuBar.getWelcomeBtn();
//	}
//
//	@Override
//	public HasClickHandlers getAboutMenuItem() {
//		return wlcMenuBar.getAboutBtn();
//	}
//
//	@Override
//	public HasClickHandlers getSendFeedbackMenuItem() {
//		return wlcMenuBar.getFeedbackBtn();
//	}
	
	@Override
	public Widget asWidget(){
		return this;
	}

//	@Override
//	public HasWidgets getContentPanel() {
//		return contentPanel;
//	}

	@Override
	public HasClickHandlers getLoginButton() {
		return loginForm.getBtnLogin();
	}

	@Override
	public HasValue<String> getEmail() {
		return loginForm.getTextBoxEmail();
	}

	@Override
	public HasValue<String> getPassword() {
		return loginForm.getTextBoxPassword();
	}

	@Override
	public HasText getErrorLbl() {
		return loginForm.getLblError();
	}

	@Override
	public HasValue<Boolean> getRememberMe() {
		return loginForm.getChckbxRememberMe();
	}

	@Override
	public HasClickHandlers getForgetButton() {
		return loginForm.getBtnForgotPassword();
	}

	@Override
	public HasWidgets getFormPanel() {
		return formPanel;
	}

	@Override
	public void showLoginForm() {
		formPanel.clear();
		formPanel.add(loginForm);
	}

	@Override
	public void showForgotPasswordForm() {
		formPanel.clear();
		formPanel.add(forgotPasswordForm);
	}

	@Override
	public HasClickHandlers getForgotSendButton() {
		return forgotPasswordForm.getSendButton();
	}

	@Override
	public HasValue<String> getForgotEmail() {
		// TODO Auto-generated method stub
		return forgotPasswordForm.getForgPassEmail();
	}

	@Override
	public HasText getMsgLbl() {
		return forgotPasswordForm.getMsgLbl();
	}

}
