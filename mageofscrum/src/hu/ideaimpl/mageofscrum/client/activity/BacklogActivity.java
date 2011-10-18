package hu.ideaimpl.mageofscrum.client.activity;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.ui.TaskDialog;
import hu.ideaimpl.mageofscrum.client.view.BacklogView;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TaskDO;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class BacklogActivity extends AbstractActivity {
	private BacklogView view = ClientFactory.Util.getClientFactory().getBacklogView();
	private TaskDialog dialog = new TaskDialog();
	private ArrayList<ProjectDO> projects = new ArrayList<ProjectDO>();
	private ArrayList<TaskDO> tasks = new ArrayList<TaskDO>();
	private SingleSelectionModel<ProjectDO> projectSelectionModel = new SingleSelectionModel<ProjectDO>();
	private SingleSelectionModel<TaskDO> taskSelectionModel = new SingleSelectionModel<TaskDO>();
	private TaskDO selectedTask = null;
	private boolean running = false;
	
	public BacklogActivity() {
		System.out.println("activityConstructor");
		initProjectList();
		view.getProjectsList().setSelectionModel(projectSelectionModel);
		view.getBacklogTable().setSelectionModel(taskSelectionModel);
		
		bind();
	}
	
	private void initProjectList() {
		ManagerService.Util.getService().listTeamsProjects(new AsyncCallback<ArrayList<ProjectDO>>() {
			
			@Override
			public void onSuccess(ArrayList<ProjectDO> result) {
				projects = result;
				view.getProjectsList().setRowData(projects);
				projectSelectionModel.setSelected(result.get(0), true);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	private void bind(){
		view.getDeleteBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (taskSelectionModel.getSelectedObject() != null) {
					doOnDeleteBtnClicked();
				}
			}
		});
		view.getCreateBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialog.clearForm();
				dialog.center();
				dialog.show();
			}
		});
		dialog.getSaveBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(projectSelectionModel.getSelectedObject() != null && dialog.validateForm()){
					doOnSaveBtnClicked();
					dialog.hide();
				}
			}
		});
		projectSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				doOnSelectionChanged();
			}
		});
		view.getBacklogTable().addDomHandler(new DoubleClickHandler() {
			
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				dialog.setFormData(taskSelectionModel.getSelectedObject());
				dialog.center();
				dialog.show();
			}
		}, DoubleClickEvent.getType());
	}

	protected void doOnDeleteBtnClicked() {
		final TaskDO task = taskSelectionModel.getSelectedObject();
		ManagerService.Util.getService().deleteTask(task.getId(), new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				tasks.remove(task);
				view.getBacklogTable().setRowData(tasks);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	protected void doOnSelectionChanged() {
		Long projId = projectSelectionModel.getSelectedObject().getId();
		ManagerService.Util.getService().fetchTasks(projId, new AsyncCallback<ArrayList<TaskDO>>() {
			
			@Override
			public void onSuccess(ArrayList<TaskDO> result) {
				tasks = result;
				view.getBacklogTable().setRowData(tasks);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	private void doOnSaveBtnClicked() {
		Long projId = projectSelectionModel.getSelectedObject().getId();
		ManagerService.Util.getService().addTask(projId, dialog.getFormData(), new AsyncCallback<TaskDO>() {
			
			@Override
			public void onSuccess(TaskDO result) {
				TaskDO tmpTask = null;
				for (TaskDO t : tasks) {
					if (t.getId() == result.getId()) {
						tmpTask = t;
						break;
					}
				}
				if (tmpTask != null) {
					tasks.remove(tmpTask);
				}
				tasks.add(result);
				view.getBacklogTable().setRowData(tasks);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		running = true;
		System.out.println("activityStart");
		panel.setWidget(view);
	}
	
	@Override
	public void onStop() {
		System.out.println("activityOnStop");
		super.onStop();
	}
	
	@Override
	public String mayStop() {
		return null;
	}
	
	@Override
	public void onCancel() {
		onStop();
	}

}
