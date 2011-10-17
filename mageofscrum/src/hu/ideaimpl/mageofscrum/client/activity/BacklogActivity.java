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
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class BacklogActivity extends AbstractActivity {
	private BacklogView view = ClientFactory.Util.getClientFactory().getBacklogView();
	private TaskDialog dialog = new TaskDialog();
	private ArrayList<ProjectDO> projects = new ArrayList<ProjectDO>();
	private SingleSelectionModel<ProjectDO> projectSelectionModel = new SingleSelectionModel<ProjectDO>();
	
	public BacklogActivity() {
		initProjectList();
		view.getProjectsList().setSelectionModel(projectSelectionModel);
		
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
		view.getCreateBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialog.clearForm();
				dialog.center();
				dialog.show();
			}
		});
		dialog.getSaveBtn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(dialog.validateForm()){
					doOnSaveBtnClicked();
				}
			}
		});
		projectSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				doOnSelectionChanged();
			}
		});
	}

	protected void doOnSelectionChanged() {
		Long projId = projectSelectionModel.getSelectedObject().getId();
		ManagerService.Util.getService().fetchTasks(projId, new AsyncCallback<ArrayList<TaskDO>>() {
			
			@Override
			public void onSuccess(ArrayList<TaskDO> result) {
				view.getBacklogTable().setRowData(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed");
			}
		});
	}

	private void doOnSaveBtnClicked() {
		
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
