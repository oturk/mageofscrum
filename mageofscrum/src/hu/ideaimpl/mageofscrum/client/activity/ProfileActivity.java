package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.view.ProfileView;
import hu.ideaimpl.mageofscrum.shared.UserDataDO;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ProfileActivity extends AbstractActivity {
	private ProfileView profileView = ClientFactory.Util.getClientFactory().getProfileView();;
	
	public ProfileActivity() {
		ManagerService.Util.getService().fetchUserData(new AsyncCallback<UserDataDO>() {
			
			@Override
			public void onSuccess(UserDataDO result) {
				profileView.setUserData(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
		
		bind();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(profileView);
	}
	
	public void bind(){
		profileView.getSaveBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(profileView.validateForm()){
					doOnSaveBtnClicked();
				}
			}
		});
		profileView.getChangePassBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(profileView.validatePassword()){
					doOnChangePassBtnClicked();
				}
			}
		});
	}

	protected void doOnChangePassBtnClicked() {
		ManagerService.Util.getService().changePassword(profileView.getPassword(), new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				System.out.println("success");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	protected void doOnSaveBtnClicked() {
		ManagerService.Util.getService().updateUserData(profileView.getUserData(), new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				System.out.println("success");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

}
