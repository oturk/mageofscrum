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

public class BacklogView extends Composite implements HasInitState{
	private CellTable<TaskDO> backlogTable;
	private Button btnCreate = new Button("create");
	private Button btnDelete = new Button("delete");
	private Button btnMoveToSprint = new Button("move to sprint");
	private Button btnStartSprint = new Button("start sprint");
	private Button btnStopSprint = new Button("stop sprint");
	private CellList<ProjectDO> projectsList;
	private Label errorLbl = new Label("");

	public BacklogView() {
		MosStyle style = Resources.instance.mosStyle();
		style.ensureInjected();

		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);

		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(1);
		buttonBar.add(btnCreate);
		buttonBar.add(btnDelete);
		buttonBar.add(btnMoveToSprint);
		buttonBar.add(btnStartSprint);
		buttonBar.add(btnStopSprint);
		buttonBar.add(errorLbl);
		btnCreate.setStyleName(style.commandBtn());
		btnStartSprint.setStyleName(style.commandBtn());
		btnStopSprint.setStyleName(style.commandBtn());
		btnMoveToSprint.setStyleName(style.commandBtn());
		btnDelete.setStyleName(style.commandBtn());
		errorLbl.setStyleName(style.errorLbl());
		errorLbl.setHeight("24px");

		verticalPanel.add(buttonBar);

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		verticalPanel.add(horizontalPanel);

		projectsList = new CellList<ProjectDO>(new AbstractCell<ProjectDO>() {
			@Override
			public void render(Context context, ProjectDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
			}
		});
		projectsList.setSize("260px", "400px");

		TitledPanel projectsPanel = new TitledPanel();
		horizontalPanel.add(projectsPanel);
		projectsPanel.setText("projects");
		projectsPanel.addContent(projectsList);
		projectsPanel.setSize("260px", "400px");

		backlogTable = new CellTable<TaskDO>(20, TableResource.instance);
		backlogTable.setWidth("537px");

		TitledPanel sprintPanel = new TitledPanel();
		horizontalPanel.add(sprintPanel);
		sprintPanel.setText("tasks");
		sprintPanel.addContent(backlogTable);
		sprintPanel.setSize("540px", "400px");

		TextColumn<TaskDO> nameColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return object.getName();
			}
		};
		backlogTable.addColumn(nameColumn, "name");

		TextColumn<TaskDO> estTimeColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return Integer.toString(object.getEstimateTime());
			}
		};
		backlogTable.addColumn(estTimeColumn, "estimate time");

		TextColumn<TaskDO> priorityColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return Integer.toString(object.getPriority());
			}
		};
		backlogTable.addColumn(priorityColumn, "priority");
		final DateTimeFormat dateFormat = DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_NUM_DAY);
		TextColumn<TaskDO> createdColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return dateFormat.format(object.getCreated());
			}
		};
		backlogTable.addColumn(createdColumn, "created");
	}

	public CellList<ProjectDO> getProjectsList() {
		return projectsList;
	}

	public HasText getErrorLbl() {
		return errorLbl;
	}

	public HasClickHandlers getCreateBtn() {
		return btnCreate;
	}

	public HasClickHandlers getDeleteBtn() {
		return btnDelete;
	}

	public HasClickHandlers getStartSprintBtn() {
		return btnStartSprint;
	}

	public HasClickHandlers getStopSprintBtn() {
		return btnStopSprint;
	}

	public HasClickHandlers getMoveToSprintBtn() {
		return btnMoveToSprint;
	}

	public CellTable<TaskDO> getBacklogTable() {
		return backlogTable;
	}

	@Override
	public void initState() {
		backlogTable.setRowData(new ArrayList<TaskDO>());
		projectsList.setRowData(new ArrayList<ProjectDO>());
		errorLbl.setText("");
	}
}
