package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.ui.ReportData;
import hu.ideaimpl.mageofscrum.client.ui.forms.ReportDialog;
import hu.ideaimpl.mageofscrum.client.ui.forms.TaskDialog;
import hu.ideaimpl.mageofscrum.client.view.SprintView;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TaskDO;

import java.util.ArrayList;

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

public class SprintActivity extends AbstractActivity {
	public SprintView view = ClientFactory.Util.getClientFactory().getSprintView();
	private SingleSelectionModel<ProjectDO> projectSelectionModel = new SingleSelectionModel<ProjectDO>();
	private SingleSelectionModel<TaskDO> taskSelectionModel = new SingleSelectionModel<TaskDO>();
	private ArrayList<ProjectDO> projects = new ArrayList<ProjectDO>();
	private ArrayList<TaskDO> tasks = new ArrayList<TaskDO>();
	private static TaskDialog dialog = TaskDialog.getDetailsDialog();
	private static ReportDialog reportDialog = new ReportDialog();

	public SprintActivity() {
		initProjectList();
		view.getProjectsList().setSelectionModel(projectSelectionModel);
		view.getSprintTable().setSelectionModel(taskSelectionModel);

		bind();
	}

	private void bind() {
		projectSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				doOnSelectionChanged();
			}
		});
		view.getReportBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (projectSelectionModel.getSelectedObject() != null && taskSelectionModel.getSelectedObject() != null) {
					doOnReportBtnClicked();
				}
			}
		});
		view.getTaskDetailsBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (projectSelectionModel.getSelectedObject() != null && taskSelectionModel.getSelectedObject() != null) {
					doOnDetailsBtnClicked();
				}
			}
		});
		view.getRemoveBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (taskSelectionModel.getSelectedObject() != null) {
					doOnRemoveBtnClicked();
				}
			}
		});
		reportDialog.getReportBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (reportDialog.validate()) {
					doOnReportDialogBtnClicked();
					reportDialog.hide();
				}
			}
		});
		view.getSprintTable().addDomHandler(new DoubleClickHandler() {

			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				dialog.setFormData(taskSelectionModel.getSelectedObject());
				dialog.center();
				dialog.show();
			}
		}, DoubleClickEvent.getType());
	}

	protected void doOnRemoveBtnClicked() {
		Long projectId = projectSelectionModel.getSelectedObject().getId();
		final TaskDO task = taskSelectionModel.getSelectedObject();

		ManagerService.Util.getService().removeTaskFromSprint(projectId, task.getId(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				tasks.remove(task);
				view.getSprintTable().setRowData(tasks);
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	protected void doOnReportDialogBtnClicked() {
		Long taskId = taskSelectionModel.getSelectedObject().getId();
		Long projectId = projectSelectionModel.getSelectedObject().getId();
		ReportData data = reportDialog.getFormData();
		ManagerService.Util.getService().reportToTask(projectId, taskId, data.getTime(), data.getDate(), data.getDesc(), new AsyncCallback<TaskDO>() {

			@Override
			public void onSuccess(TaskDO result) {
				TaskDO tmp = null;
				for (TaskDO t : tasks) {
					if (t.getId() == result.getId()) {
						tmp = t;
						break;
					}
				}
				if (tmp != null) {
					tasks.remove(tmp);
					tasks.add(result);
					view.getSprintTable().setRowData(tasks);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	private void doOnDetailsBtnClicked() {
		dialog.setFormData(taskSelectionModel.getSelectedObject());
		dialog.center();
		dialog.show();
	}

	private void doOnReportBtnClicked() {
		reportDialog.clearForm();
		reportDialog.center();
		reportDialog.show();
	}

	private void doOnSelectionChanged() {
		Long projId = projectSelectionModel.getSelectedObject().getId();
		ManagerService.Util.getService().fetchSprintTask(projId, new AsyncCallback<ArrayList<TaskDO>>() {

			@Override
			public void onSuccess(ArrayList<TaskDO> result) {
				tasks = result;
				view.getSprintTable().setRowData(tasks);
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	private void initProjectList() {
		ManagerService.Util.getService().listTeamsProjects(new AsyncCallback<ArrayList<ProjectDO>>() {

			@Override
			public void onSuccess(ArrayList<ProjectDO> result) {
				projects = result;
				view.getProjectsList().setRowData(projects);
				if (result.size() > 0) {
					projectSelectionModel.setSelected(result.get(0), true);
				}else{
					tasks = new ArrayList<TaskDO>();
					view.getSprintTable().setRowData(tasks);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
