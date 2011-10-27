package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.ui.dialog.ErrorDialog;
import hu.ideaimpl.mageofscrum.client.view.RolesView;
import hu.ideaimpl.mageofscrum.shared.RoleDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;
import java.util.Set;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;

public class RoleActivity extends AbstractActivity {

	private RolesView view;
	private MultiSelectionModel<UserDO> usersSelection = new MultiSelectionModel<UserDO>();
	private MultiSelectionModel<RoleDO> hasRoleSelection = new MultiSelectionModel<RoleDO>();
	private MultiSelectionModel<RoleDO> otherRoleSelection = new MultiSelectionModel<RoleDO>();
	private ArrayList<UserDO> users = new ArrayList<UserDO>();
	private ArrayList<RoleDO> usersRoles = new ArrayList<RoleDO>();
	private ArrayList<RoleDO> otherRoles = new ArrayList<RoleDO>();

	public RoleActivity(ClientFactory clientFactory) {
		view = clientFactory.getRoleView();
		view.getUsersList().setSelectionModel(usersSelection);
		view.getFromList().setSelectionModel(hasRoleSelection);
		view.getToList().setSelectionModel(otherRoleSelection);
		bind();
	}

	private void bind() {
		view.getCreateBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (view.validateUserForm()) {
					doOnCreateBtnClicked();
				}
			}
		});
		view.getDeleteBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (usersSelection.getSelectedSet().size() > 0) {
					doOnDeleteBtnClicked();
				}
			}
		});
		view.getAddRoleBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (otherRoleSelection.getSelectedSet().size() > 0 && usersSelection.getSelectedSet().size() == 1) {
					doOnAddBtnClicked();
				}
			}
		});
		view.getRemoveRoleBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (hasRoleSelection.getSelectedSet().size() > 0 && usersSelection.getSelectedSet().size() == 1) {
					doOnRemoveBtnClicked();
				}
			}
		});
		view.getChangePassBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
					public void execute() {
						if (usersSelection.getSelectedSet() == null || usersSelection.getSelectedSet().size() < 1) {
							view.getErrorLbl().setText("select a user first");
						}else{
							if ((usersSelection.getSelectedSet() != null || usersSelection.getSelectedSet().size() == 1) && view.validatePassword()) {
								doOnChangePassBtnClicked();
							}
						}
					}
				});
			}
		});
		usersSelection.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				doOnSelectionChanged();
			}
		});
	}

	protected void doOnChangePassBtnClicked() {
		Long userId = usersSelection.getSelectedSet().iterator().next().getId();
		UserDO user = view.getNewUser();
		ManagerService.Util.getService().changeUserPassword(userId, user.getPassword(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				view.clearUserForm();
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});
	}

	protected void doOnSelectionChanged() {
		if (usersSelection.getSelectedSet().size() == 1) {
			String userId = usersSelection.getSelectedSet().iterator().next().getUsername();
			ManagerService.Util.getService().fetchUsersRoles(userId, new AsyncCallback<ArrayList<RoleDO>>() {

				@Override
				public void onSuccess(ArrayList<RoleDO> result) {
					usersRoles = result;
					view.getFromList().setRowData(usersRoles);
				}

				@Override
				public void onFailure(Throwable caught) {
					ErrorDialog.show("Server error", caught.getMessage());

				}
			});
			ManagerService.Util.getService().fetchOtherRoles(userId, new AsyncCallback<ArrayList<RoleDO>>() {

				@Override
				public void onSuccess(ArrayList<RoleDO> result) {
					otherRoles = result;
					view.getToList().setRowData(otherRoles);
				}

				@Override
				public void onFailure(Throwable caught) {
					ErrorDialog.show("Server error", caught.getMessage());
				}
			});
		}
	}

	protected void doOnAddBtnClicked() {
		Set<RoleDO> selected = otherRoleSelection.getSelectedSet();
		ArrayList<Long> roles = new ArrayList<Long>();
		for (RoleDO role : selected) {
			roles.add(role.getId());
		}

		final ArrayList<RoleDO> newRoles = new ArrayList<RoleDO>();
		for (RoleDO role : selected) {
			newRoles.add(role);
		}

		String userId = usersSelection.getSelectedSet().iterator().next().getUsername();
		ManagerService.Util.getService().addRolesToUser(userId, roles, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				usersRoles.addAll(newRoles);
				otherRoles.removeAll(newRoles);
				view.getToList().setRowData(otherRoles);
				view.getFromList().setRowData(usersRoles);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});

	}

	protected void doOnRemoveBtnClicked() {
		String userId = usersSelection.getSelectedSet().iterator().next().getUsername();

		Set<RoleDO> selected = hasRoleSelection.getSelectedSet();
		ArrayList<Long> roles = new ArrayList<Long>();
		for (RoleDO role : selected) {
			roles.add(role.getId());
		}

		final ArrayList<RoleDO> deleteRoles = new ArrayList<RoleDO>();
		for (RoleDO role : selected) {
			deleteRoles.add(role);
		}

		ManagerService.Util.getService().removeRolesFromUser(userId, roles, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				usersRoles.removeAll(deleteRoles);
				otherRoles.addAll(deleteRoles);
				view.getToList().setRowData(otherRoles);
				view.getFromList().setRowData(usersRoles);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});

	}

	private void doOnDeleteBtnClicked() {
		ArrayList<String> delete = new ArrayList<String>();
		for (UserDO user : usersSelection.getSelectedSet()) {
			delete.add(user.getUsername());
		}
		ManagerService.Util.getService().deleteUsers(delete, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				for (UserDO selected : usersSelection.getSelectedSet()) {
					users.remove(selected);
					view.getUsersList().setRowData(users);
					usersRoles.clear();
					otherRoles.clear();
					view.getFromList().setRowData(usersRoles);
					view.getToList().setRowData(otherRoles);
					usersSelection.clear();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});
	}

	private void doOnCreateBtnClicked() {
		ManagerService.Util.getService().addUser(view.getNewUser(), new AsyncCallback<UserDO>() {

			@Override
			public void onSuccess(UserDO result) {
				if (result != null) {
					users.add(result);
					view.getUsersList().setRowData(users);
					view.clearUserForm();
				} else {
					view.getErrorLbl().setText("user already exist");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		fetchUsers();
	}

	private void fetchUsers() {
		ManagerService.Util.getService().fetchUsers(new AsyncCallback<ArrayList<UserDO>>() {

			@Override
			public void onSuccess(ArrayList<UserDO> result) {
				users = result;
				view.getUsersList().setRowData(users);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});
	}

}
