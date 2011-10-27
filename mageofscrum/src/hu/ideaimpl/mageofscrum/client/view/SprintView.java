package hu.ideaimpl.mageofscrum.client.view;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.client.resources.MosStyle;
import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.resources.TableResource;
import hu.ideaimpl.mageofscrum.client.ui.TitledPanel;
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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SprintView extends Composite implements HasInitState{
	private CellTable<TaskDO> sprintTable;
	private Button btnReport = new Button("report");
	private Button btnRemove = new Button("remove");
	private Button btnTaskDetails = new Button("task details");
	private CellList<ProjectDO> projectsList;
	private Label errorLbl = new Label("");

	public SprintView() {
		MosStyle style = Resources.instance.mosStyle();
		style.ensureInjected();
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);

		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(1);
		buttonBar.add(btnTaskDetails);
		buttonBar.add(btnReport);
		buttonBar.add(btnRemove);
		buttonBar.add(errorLbl);
		verticalPanel.add(buttonBar);

		btnReport.setStyleName(style.commandBtn());
		btnTaskDetails.setStyleName(style.commandBtn());

		btnReport.setText("report");
		btnRemove.setStyleName(style.commandBtn());

		btnRemove.setText("remove task");

		errorLbl.setStyleName(style.errorLbl());
		errorLbl.setSize("440px", "20px");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		verticalPanel.add(horizontalPanel);

		projectsList = new CellList<ProjectDO>(new AbstractCell<ProjectDO>() {
			@Override
			public void render(Context context, ProjectDO value, SafeHtmlBuilder sb) {
				sb.appendHtmlConstant(value.getName());
			}
		});
		projectsList.setSize("260px", "175px");

		TitledPanel projectsPanel = new TitledPanel();
		horizontalPanel.add(projectsPanel);
		projectsPanel.setText("projects");
		projectsPanel.addContent(projectsList);
		projectsPanel.setSize("260px", "400px");

		sprintTable = new CellTable<TaskDO>(20, TableResource.instance);
		TitledPanel sprintPanel = new TitledPanel();
		horizontalPanel.add(sprintPanel);
		sprintPanel.setText("tasks");
		sprintPanel.addContent(sprintTable);
		sprintPanel.setSize("540px", "400px");
		sprintTable.setWidth("537px");

		TextColumn<TaskDO> nameColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return object.getName();
			}
		};
		sprintTable.addColumn(nameColumn, "name");

		TextColumn<TaskDO> estTimeColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return Integer.toString(object.getEstimateTime());
			}
		};
		sprintTable.addColumn(estTimeColumn, "estimate time");

		TextColumn<TaskDO> priorityColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return Integer.toString(object.getPriority());
			}
		};
		sprintTable.addColumn(priorityColumn, "priority");
		final DateTimeFormat dateFormat = DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_NUM_DAY);
		TextColumn<TaskDO> createdColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return dateFormat.format(object.getCreated());
			}
		};
		sprintTable.addColumn(createdColumn, "created");
	}

	public CellList<ProjectDO> getProjectsList() {
		return projectsList;
	}

	public HasText getErrorLbl() {
		return errorLbl;
	}

	public HasClickHandlers getReportBtn() {
		return btnReport;
	}

	public HasClickHandlers getRemoveBtn() {
		return btnRemove;
	}

	public HasClickHandlers getTaskDetailsBtn() {
		return btnTaskDetails;
	}

	public CellTable<TaskDO> getSprintTable() {
		return sprintTable;
	}

	@Override
	public void initState() {
		sprintTable.setRowData(new ArrayList<TaskDO>());
		projectsList.setRowData(new ArrayList<ProjectDO>());
		errorLbl.setText("");
	}
}
