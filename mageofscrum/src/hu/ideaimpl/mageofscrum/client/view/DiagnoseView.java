package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.ui.charts.BurndownChart;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.SprintDO;

import java.util.Map;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.corechart.Options;

public class DiagnoseView extends Composite {

	private AbsolutePanel absolutePanel = new AbsolutePanel();
	private CellList<ProjectDO> projectsList;
	private CellList<SprintDO> sprintsList;
	private VerticalPanel chartsPanel = new VerticalPanel();

	public DiagnoseView() {
		initWidget(absolutePanel);
		absolutePanel.setHeight("501px");
		
		projectsList = new CellList<ProjectDO>(new AbstractCell<ProjectDO>(){
			@Override
			public void render(Context context, ProjectDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
			}
		});
		absolutePanel.add(projectsList, 0, 40);
		projectsList.setSize("220px", "204px");
		
		Label lblProjects = new Label("projects");
		lblProjects.setStyleName("loginHeader");
		absolutePanel.add(lblProjects, 0, 0);
		lblProjects.setSize("220px", "30px");
		
		Label lblSprints = new Label("sprints");
		lblSprints.setStyleName("loginHeader");
		absolutePanel.add(lblSprints, 0, 250);
		lblSprints.setSize("220px", "30px");
		
		sprintsList = new CellList<SprintDO>(new AbstractCell<SprintDO>(){
			@Override
			public void render(Context context, SprintDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getInterval());
			}
		});
		absolutePanel.add(sprintsList, 0, 286);
		sprintsList.setSize("220px", "204px");
		
		absolutePanel.add(chartsPanel, 226, 40);
		chartsPanel.setSize("700px", "450px");
			
	}
	
	public void createChart(Map<String, Integer> data){
		chartsPanel.add(new BurndownChart(data));
//		Runnable onLoadCallback = new Runnable() {
//			
//			@Override
//			public void run() {
//				Options ops = LineChart.createOptions();
//				ops.setWidth(400);
//				ops.setHeight(200);
//				ops.setTitle("Burndown Chart");
//				LineChart lineChart = new LineChart(createTable(), ops);
//				
//				chartsPanel.add(lineChart);
//			}
//		};
//		
//		VisualizationUtils.loadVisualizationApi(onLoadCallback, LineChart.PACKAGE, Table.PACKAGE);
	}
	
	public CellList<ProjectDO> getProjectsList(){
		return projectsList;
	}
	
	public CellList<SprintDO> getSprintsList(){
		return sprintsList;
	}
	
	private Options createOptions(){
		Options op = Options.create();
		op.setWidth(500);
		op.setHeight(300);
		op.setTitle("My first pie chart");
		return op;
	}
	
	private AbstractDataTable createTable(){
		DataTable table = DataTable.create();
		table.addColumn(ColumnType.STRING, "Task");
		table.addColumn(ColumnType.NUMBER, "Hours per day");
		table.addRows(2);
		table.setValue(0, 0, "Work");
		table.setValue(0, 1, 14);
		table.setValue(1, 0, "Rest");
		table.setValue(1, 1, 10);
		return table;
	}
}
