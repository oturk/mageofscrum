package hu.ideaimpl.mageofscrum.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Label;
import hu.ideaimpl.mageofscrum.client.ui.ListToList;
import hu.ideaimpl.mageofscrum.shared.RoleDO;

public class RolesView extends Composite {

	public RolesView() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("850px", "500px");
		
		CellList<Object> cellList = new CellList<Object>(new AbstractCell<Object>(){
			@Override
			public void render(Context context, Object value, SafeHtmlBuilder sb) {
				// TODO
			}
		});
		cellList.setStyleName("mosCellList");
		absolutePanel.add(cellList, 0, 30);
		cellList.setSize("200px", "466px");
		
		Label lblNewLabel = new Label("roles");
		lblNewLabel.setStyleName("simpleLbl");
		absolutePanel.add(lblNewLabel, 0, 0);
		lblNewLabel.setSize("199px", "30px");
		
		CellList<RoleDO> toList = new CellList<RoleDO>(new AbstractCell<RoleDO>() {
			@Override
			public void render(Context context, RoleDO value, SafeHtmlBuilder sb) {
				// TODO
			}
		});
		
		CellList<RoleDO> fromList = new CellList<RoleDO>(new AbstractCell<RoleDO>() {
			@Override
			public void render(Context context, RoleDO value, SafeHtmlBuilder sb) {
				// TODO
			}
		});
		
		ListToList listToList = new ListToList(fromList, toList);
		listToList.setFromLbl("has role");
		listToList.setToLbl("other users");
		absolutePanel.add(listToList, 250, 0);
	}
}
