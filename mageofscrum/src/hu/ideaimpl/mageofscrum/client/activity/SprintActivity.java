package hu.ideaimpl.mageofscrum.client.activity;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.ui.TaskDialog;
import hu.ideaimpl.mageofscrum.client.view.SprintView;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TaskDO;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
				if(projectSelectionModel.getSelectedObject() != null && taskSelectionModel.getSelectedObject() != null){
					doOnReportBtnClicked();
				}
			}
		});
		view.getTaskDetailsBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(projectSelectionModel.getSelectedObject() != null && taskSelectionModel.getSelectedObject() != null){
					doOnDetailsBtnClicked();
				}
			}
		});
	}

	private void doOnDetailsBtnClicked() {
		dialog.setFormData(taskSelectionModel.getSelectedObject());
		dialog.center();
		dialog.show();
	}

	private void doOnReportBtnClicked() {
		
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
				projectSelectionModel.setSelected(result.get(0), true);
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
