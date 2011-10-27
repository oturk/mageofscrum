package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.ui.TitledPanel;
import hu.ideaimpl.mageofscrum.client.ui.charts.BurndownChart;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.SprintDO;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DiagnoseView extends Composite implements HasInitState{

	private CellList<ProjectDO> projectsList;
	private CellList<SprintDO> sprintsList;
	private VerticalPanel chartsPanel = new VerticalPanel();

	public DiagnoseView() {
		HorizontalPanel hPanel = new HorizontalPanel();
		initWidget(hPanel);

		projectsList = new CellList<ProjectDO>(new AbstractCell<ProjectDO>() {
			@Override
			public void render(Context context, ProjectDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
			}
		});

		VerticalPanel listPanel = new VerticalPanel();
		listPanel.setSpacing(5);

		projectsList.setSize("220px", "200px");

		TitledPanel projectsPanel = new TitledPanel();
		listPanel.add(projectsPanel);
		projectsPanel.setText("projects");
		projectsPanel.addContent(projectsList);
		projectsPanel.setSize("220px", "230px");
		
		sprintsList = new CellList<SprintDO>(new AbstractCell<SprintDO>() {
			@Override
			public void render(Context context, SprintDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getInterval());
			}
		});
		TitledPanel sprintPanel = new TitledPanel();
		listPanel.add(sprintPanel);
		sprintPanel.setText("sprints");
		sprintPanel.addContent(sprintsList);
		sprintPanel.setSize("220px", "230px");
		
		hPanel.add(listPanel);
		sprintsList.setSize("220px", "200px");

		hPanel.add(chartsPanel);
		chartsPanel.setSize("700px", "450px");

	}

	public void createChart(Map<String, Integer> data) {
		chartsPanel.add(new BurndownChart(data));
	}

	public CellList<ProjectDO> getProjectsList() {
		return projectsList;
	}

	public CellList<SprintDO> getSprintsList() {
		return sprintsList;
	}

	@Override
	public void initState() {
		projectsList.setRowData(new ArrayList<ProjectDO>());
		sprintsList.setRowData(new ArrayList<SprintDO>());
		chartsPanel.clear();
	}

}
