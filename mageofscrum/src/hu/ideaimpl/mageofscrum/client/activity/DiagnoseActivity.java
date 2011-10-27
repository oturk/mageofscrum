package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.service.ManagerService;
import hu.ideaimpl.mageofscrum.client.ui.dialog.ErrorDialog;
import hu.ideaimpl.mageofscrum.client.view.DiagnoseView;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.SprintDO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class DiagnoseActivity extends AbstractActivity {
	private DiagnoseView view = ClientFactory.Util.getClientFactory().getDiagnoseView();
	private SingleSelectionModel<ProjectDO> projectSelectionModel = new SingleSelectionModel<ProjectDO>();
	private SingleSelectionModel<SprintDO> sprintSelectionModel = new SingleSelectionModel<SprintDO>();
	private List<SprintDO> sprints = new ArrayList<SprintDO>();

	public DiagnoseActivity() {
		fillProjectsList();
		view.getProjectsList().setSelectionModel(projectSelectionModel);
		view.getSprintsList().setSelectionModel(sprintSelectionModel);
		bind();
	}

	private void bind() {
		projectSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				fillSprintsList();
			}
		});
		sprintSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				loadCharts();
			}
		});
	}

	private void loadCharts() {
		Long srintId = sprintSelectionModel.getSelectedObject().getId();

		ManagerService.Util.getService().getSprintHistory(srintId, new AsyncCallback<Map<String, Integer>>() {

			@Override
			public void onSuccess(Map<String, Integer> result) {
				view.createChart(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});
	}

	private void fillSprintsList() {
		Long projectId = projectSelectionModel.getSelectedObject().getId();
		ManagerService.Util.getService().fetchSprints(projectId, new AsyncCallback<ArrayList<SprintDO>>() {

			@Override
			public void onSuccess(ArrayList<SprintDO> result) {
				sprints = result;
				view.getSprintsList().setRowData(sprints);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorDialog.show("Server error", caught.getMessage());
			}
		});
	}

	private void fillProjectsList() {
		ManagerService.Util.getService().listTeamsProjects(new AsyncCallback<ArrayList<ProjectDO>>() {

			@Override
			public void onSuccess(ArrayList<ProjectDO> result) {
				view.getProjectsList().setRowData(result);
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
	}

}
