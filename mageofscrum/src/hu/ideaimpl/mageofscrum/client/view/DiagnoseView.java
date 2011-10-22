package hu.ideaimpl.mageofscrum.client.view;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart.PieOptions;

public class DiagnoseView extends Composite {

	private VerticalPanel absolutePanel = new VerticalPanel();

	public DiagnoseView() {
		initWidget(absolutePanel);
		absolutePanel.add(new Label("diagnose"));
		
//		Runnable onLoadCallback = new Runnable() {
//			
//			@Override
//			public void run() {
//				
//				
//				PieOptions ops = PieChart.createPieOptions();
//				ops.setWidth(500);
//				ops.setHeight(300);
//				ops.setTitle("my first chart");
//				PieChart pieChart = new PieChart(createTable(), ops);
//				
//				absolutePanel.add(pieChart);
//			}
//		};
//		
//		VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
	}
	
//	private Options createOptions(){
//		Options op = Options.create();
//		op.setWidth(500);
//		op.setHeight(300);
//		op.setTitle("My first pie chart");
//		return op;
//	}
//	
//	private AbstractDataTable createTable(){
//		DataTable table = DataTable.create();
//		table.addColumn(ColumnType.STRING, "Task");
//		table.addColumn(ColumnType.NUMBER, "Hours per day");
//		table.addRows(2);
//		table.setValue(0, 0, "Work");
//		table.setValue(0, 1, 14);
//		table.setValue(0, 1, "Rest");
//		table.setValue(1, 1, 10);
//		return table;
//	}

}
