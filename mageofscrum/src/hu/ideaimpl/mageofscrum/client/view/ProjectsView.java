package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.resources.MosStyle;
import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.resources.TableResource;
import hu.ideaimpl.mageofscrum.client.ui.TitledPanel;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionModel;

public class ProjectsView extends Composite implements HasInitState{

	private Button btnDelete = new Button("delete");
	private Button btnNewButton = new Button("create");
	private Button btnDetails = new Button("details");
	private CellTable<ProjectDO> projectsTable = new CellTable<ProjectDO>(3,TableResource.instance);
	private Label lblError = new Label("");

	public ProjectsView() {
		MosStyle style = Resources.instance.mosStyle();
		style.ensureInjected();

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(5);
		initWidget(verticalPanel);

		HorizontalPanel buttonBar = new HorizontalPanel();
		verticalPanel.add(buttonBar);
		buttonBar.setSpacing(1);
		buttonBar.add(btnNewButton);
		buttonBar.add(btnDelete);

		btnNewButton.setText("create");
		btnNewButton.setStyleName(Resources.instance.mosStyle().commandBtn());
		btnDetails.setStyleName(Resources.instance.mosStyle().commandBtn());
		buttonBar.add(btnDetails);
		buttonBar.add(lblError);
		btnDelete.setStyleName(style.commandBtn());
		lblError.setStyleName(style.errorLbl());
		lblError.setSize("548px", "24px");

		projectsTable.setWidth("560px");
		TitledPanel projectsPanel = new TitledPanel();
		verticalPanel.add(projectsPanel);
		projectsPanel.setText("projects");
		projectsPanel.addContent(projectsTable);
		projectsPanel.setSize("560px", "450px");

		TextColumn<ProjectDO> nameColumn = new TextColumn<ProjectDO>() {
			@Override
			public String getValue(ProjectDO object) {
				return object.getName();
			}
		};
		projectsTable.addColumn(nameColumn, "Name");

		TextColumn<ProjectDO> ownerColumn = new TextColumn<ProjectDO>() {
			@Override
			public String getValue(ProjectDO object) {
				return object.getOwnerName();
			}
		};
		projectsTable.addColumn(ownerColumn, "Owner");

		TextColumn<ProjectDO> teamColumn = new TextColumn<ProjectDO>() {
			@Override
			public String getValue(ProjectDO object) {
				return object.getTeamName();
			}
		};
		projectsTable.addColumn(teamColumn, "Team");
	}

	public HasClickHandlers getDetailsBtn() {
		return btnDetails;
	}
	
	public HasClickHandlers getDeleteBtn() {
		return btnDelete;
	}

	public HasClickHandlers getCreateBtn() {
		return btnNewButton;
	}

	public CellTable<ProjectDO> getProjectsTable() {
		return projectsTable;
	}

	public void setSelectionModel(SelectionModel<ProjectDO> selectionModel) {
		projectsTable.setSelectionModel(selectionModel);
	}

	@Override
	public void initState() {
		projectsTable.setRowData(new ArrayList<ProjectDO>());
		lblError.setText("");
	}

}
