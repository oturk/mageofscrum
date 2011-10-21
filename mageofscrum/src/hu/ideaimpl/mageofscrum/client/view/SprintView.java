package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TaskDO;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SprintView extends Composite {
	private CellTable<TaskDO> sprint;
	private Button btnReport = new Button("report");
	private Button btnRemove = new Button("remove");
	private Button btnTaskDetails = new Button("task details");
//	private Button btnMoveToSprint = new Button("move to sprint");
//	private Button btnStopSprint = new Button("stop sprint");
	private CellList<ProjectDO> projectsList;
	private Label errorLbl = new Label("");

	public SprintView() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		verticalPanel.add(absolutePanel);
		absolutePanel.setHeight("52px");
		btnReport.setStyleName("menuButton");
		btnTaskDetails.setStyleName("menuButton");
		absolutePanel.add(btnTaskDetails,176,0);
//		btnStopSprint.setStyleName("menuButton");
//		absolutePanel.add(btnStopSprint,258,0);
//		btnMoveToSprint.setStyleName("menuButton");
//		absolutePanel.add(btnMoveToSprint,341,0);
		
		btnReport.setText("report");
		absolutePanel.add(btnReport, 88, 0);
		btnRemove.setStyleName("menuButton");
		
		btnRemove.setText("remove task");
		absolutePanel.add(btnRemove, 0, 0);
		
		errorLbl.setStyleName("errorLbl");
		absolutePanel.add(errorLbl, 0, 32);
		errorLbl.setSize("440px", "20px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		verticalPanel.add(absolutePanel_1);
		absolutePanel_1.setHeight("30px");
		
		Label lblProjects = new Label("projects");
		lblProjects.setStyleName("loginHeader");
		absolutePanel_1.add(lblProjects, 0, 0);
		lblProjects.setSize("256px", "30px");
		
		Label lblNewLabel = new Label("tasks");
		lblNewLabel.setStyleName("loginHeader");
		absolutePanel_1.add(lblNewLabel, 263, 0);
		lblNewLabel.setSize("100%", "30px");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		
		projectsList = new CellList<ProjectDO>(new AbstractCell<ProjectDO>(){
			@Override
			public void render(Context context, ProjectDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
			}
		});
		horizontalPanel.add(projectsList);
		projectsList.setSize("260px", "24px");
		
		sprint = new CellTable<TaskDO>();
		horizontalPanel.add(sprint);
		sprint.setWidth("537px");
		
		TextColumn<TaskDO> nameColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return object.getName();
			}
		};
		sprint.addColumn(nameColumn, "name");
		
		TextColumn<TaskDO> estTimeColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return Integer.toString(object.getEstimateTime());
			}
		};
		sprint.addColumn(estTimeColumn, "estimate time");
		
		TextColumn<TaskDO> priorityColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return Integer.toString(object.getPriority());
			}
		};
		sprint.addColumn(priorityColumn, "priority");
		final DateTimeFormat dateFormat = DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_NUM_DAY);
		TextColumn<TaskDO> createdColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return dateFormat.format(object.getCreated());
			}
		};
		sprint.addColumn(createdColumn, "created");
	}
	
	public CellList<ProjectDO> getProjectsList(){
		return projectsList;
	}
	
	public HasText getErrorLbl(){
		return errorLbl;
	}
	
	public HasClickHandlers getReportBtn(){
		return btnReport;
	}
	
	public HasClickHandlers getRemoveBtn(){
		return btnRemove;
	}
	
	public HasClickHandlers getTaskDetailsBtn(){
		return btnTaskDetails;
	}
	
//	public HasClickHandlers getStopSprintBtn(){
//		return btnStopSprint;
//	}
//	
//	public HasClickHandlers getMoveToSprintBtn(){
//		return btnMoveToSprint;
//	}
	
	public CellTable<TaskDO> getSprintTable(){
		return sprint;
	}
}
