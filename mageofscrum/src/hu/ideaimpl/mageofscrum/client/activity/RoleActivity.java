package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.view.RolesView;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;
import java.util.Set;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;

public class RoleActivity extends AbstractActivity {

	private final ClientFactory clientFactory;
	private RolesView roleView;
	private MultiSelectionModel<UserDO> usersSelection = new MultiSelectionModel<UserDO>();
	private ArrayList<UserDO> users = new ArrayList<UserDO>();
	private ArrayList<RoleDO> usersRoles = new ArrayList<RoleDO>();
	private ArrayList<RoleDO> otherRoles = new ArrayList<RoleDO>();
	
	public RoleActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		roleView = clientFactory.getRoleView();
		roleView.getUsersList().setSelectionModel(usersSelection);
		bind();
	}
	
	private void bind(){
		roleView.getCreateBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(roleView.validateUserForm()){
					doOnCreateBtnClicked();
				}
			}
		});
		roleView.getDeleteBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(usersSelection.getSelectedSet().size() > 0){
					doOnDeleteBtnClicked();
				}
			}
		});
		roleView.getAddRoleBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnAddBtnClicked();
			}
		});
		roleView.getRemoveRoleBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doOnRemoveBtnClicked();
			}
		});
		usersSelection.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				doOnSelectionChanged();
			}
		});
	}

	protected void doOnSelectionChanged() {
		if(usersSelection.getSelectedSet().size() == 1){
			String userId = usersSelection.getSelectedSet().iterator().next().getEmail();
			ManagerService.Util.getService().fetchUsersRoles(userId, new AsyncCallback<ArrayList<RoleDO>>() {
				
				@Override
				public void onSuccess(ArrayList<RoleDO> result) {
					usersRoles = result;
					roleView.getHasRolesList().setRowData(usersRoles);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					System.out.println("failed");
					
				}
			});
			ManagerService.Util.getService().fetchOtherRoles(userId, new AsyncCallback<ArrayList<RoleDO>>() {
				
				@Override
				public void onSuccess(ArrayList<RoleDO> result) {
					otherRoles = result;
					roleView.getOthersList().setRowData(otherRoles);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					System.out.println("failed");
				}
			});
		}
	}

	protected void doOnAddBtnClicked() {
		// TODO Auto-generated method stub
		
	}

	protected void doOnRemoveBtnClicked() {
		// TODO Auto-generated method stub
		
	}

	private void doOnDeleteBtnClicked() {
		ArrayList<String> delete = new ArrayList<String>();
		for(UserDO user : usersSelection.getSelectedSet()){
			delete.add(user.getEmail());
		}
		ManagerService.Util.getService().deleteUsers(delete, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				for(UserDO selected : usersSelection.getSelectedSet()){
					users.remove(selected);
					roleView.getUsersList().setRowData(users);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	private void doOnCreateBtnClicked() {
		ManagerService.Util.getService().addUser(roleView.getNewUser(), new AsyncCallback<UserDO>() {
			
			@Override
			public void onSuccess(UserDO result) {
				users.add(result);
				roleView.getUsersList().setRowData(users);
				roleView.clearUserForm();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(roleView);
		ManagerService.Util.getService().fetchUsers(new AsyncCallback<ArrayList<UserDO>>() {
			
			@Override
			public void onSuccess(ArrayList<UserDO> result) {
				users = result;
				roleView.getUsersList().setRowData(users);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

}
