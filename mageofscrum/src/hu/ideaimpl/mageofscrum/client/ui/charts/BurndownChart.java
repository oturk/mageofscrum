package hu.ideaimpl.mageofscrum.client.ui.charts;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.Table;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;

public class BurndownChart extends Composite {

	private SimplePanel panel = new SimplePanel();
	private Map<String, Integer> data;
	private DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd");
	
	
	public BurndownChart(Map<String, Integer> data) {
		this.data = data;
		initWidget(panel);
		
		Runnable onLoadCallback = new Runnable() {

			@Override
			public void run() {
				Options ops = LineChart.createOptions();
				ops.setWidth(700);
				ops.setHeight(450);
				ops.setTitle("Burndown Chart");
				LineChart lineChart = new LineChart(createTable(), ops);

				panel.add(lineChart);
			}
		};

		VisualizationUtils.loadVisualizationApi(onLoadCallback, LineChart.PACKAGE, Table.PACKAGE);
	}
	
	private Comparator<String> keyComparator = new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			Date date1 = dtf.parse(o1);
			Date date2 = dtf.parse(o2);
			return date1.compareTo(date2);
		}
	};
	
	private AbstractDataTable createTable() {
		DataTable table = DataTable.create();
		table.addColumn(ColumnType.STRING, "days");
		table.addColumn(ColumnType.NUMBER, "burndown");
		
		table.addRows(data.size());
		String[] keys = new String[data.size()];
		keys = data.keySet().toArray(keys);
		Arrays.sort(keys, keyComparator);
		int value = data.get(keys[0]);
		table.setValue(0, 0, keys[0].substring(5));
		table.setValue(0, 1, value);
		for(int i = 1; i< data.size(); i++){
			value += data.get(keys[i]);
			table.setValue(i, 0, keys[i].substring(5));
			table.setValue(i, 1, value);
		}
		
		return table;
	}
	
}

