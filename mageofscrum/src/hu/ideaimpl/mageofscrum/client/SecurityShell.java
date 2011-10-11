package hu.ideaimpl.mageofscrum.client;

import hu.ideaimpl.mageofscrum.client.service.SecurityService;
import hu.ideaimpl.mageofscrum.client.ui.Header;
import hu.ideaimpl.mageofscrum.client.user.LoginForm2;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;

public class SecurityShell extends Composite {

	private LoginForm2 loginForm = ClientFactory.Util.getClientFactory().getLoginForm();

	public SecurityShell() {
		SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
		initWidget(splitLayoutPanel);
		
		Header header = ClientFactory.Util.getClientFactory().getHeader();
		splitLayoutPanel.addNorth(header, 100);
		
		HTMLPanel welcomeContent = new HTMLPanel("<style type='text/css'>h2, a{color:#73A0C5;}</style><div id='content'><h2>Scrum project management</h2><p>Scrum is an iterative, incremental framework for project management often"+ 
				"seen in agile software development, a type of software engineering.</p><p>Although the Scrum approach was originally suggested for managing product"+ 
				"development projects, its use has focused on the management of software development projects, and it can be used to run software maintenance teams"+ 
				"or as a general project/program management approach.<a href='http://en.wikipedia.org/wiki/Scrum_(development)'>wiki</a></p>"+
				"<img src='./mageofscrum/images/500px-Scrum_process.png' alt='Scrum process'/></div>");
		welcomeContent.setWidth("570px");
		
		splitLayoutPanel.addWest(loginForm, 300);
		
		splitLayoutPanel.add(welcomeContent);
		
		bind();
	}
	
	private void bind(){
		loginForm.getBtnLogin().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginUser(loginForm.getTextBoxEmail().getValue(), loginForm.getTextBoxPassword().getValue(), loginForm.getChckbxRememberMe().getValue());
			}
		});
	}
	
	private void loginUser(String email, String password, boolean rememberMe) {
		SecurityService.Util.getService().loginUser(email, password, rememberMe, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					ClientFactory.Util.getClientFactory().getMageOfScrumApp().run(RootLayoutPanel.get());
				} else {
					loginForm.getLblError().setText("Wrong email or password!");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				loginForm.getLblError().setText("Sorry our server is temporarly unavailable!");
			}
		});
	}
	

}