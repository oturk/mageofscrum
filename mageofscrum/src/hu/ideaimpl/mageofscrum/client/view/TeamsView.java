package hu.ideaimpl.mageofscrum.client.view;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.ListToList;
import hu.ideaimpl.mageofscrum.client.ui.TitledPanel;
import hu.ideaimpl.mageofscrum.client.ui.fields.InputField;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TeamsView extends Composite implements HasInitState{

	private CellList<TeamDO> teamList;
	private ListToList<UserDO> listToList;
	private Button btnCreate = new Button("create");
	private Button btnDelete = new Button("delete");
	private InputField teamName = new InputField();
	private AbstractCell<UserDO> listCell = new AbstractCell<UserDO>() {
		
		@Override
		public void render(Context context, UserDO value, SafeHtmlBuilder sb) {
			sb.appendEscaped(value.getUsername());
		}
	};

	public TeamsView() {
		HorizontalPanel hPanel = new HorizontalPanel();
		initWidget(hPanel);
		
		teamList = new CellList<TeamDO>(new AbstractCell<TeamDO>() {
			@Override
			public void render(Context context, TeamDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
			}
		});
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(1);
		vPanel.add(teamName);
		
		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(1);
		buttonBar.add(btnCreate);
		buttonBar.add(btnDelete);
		
		vPanel.add(buttonBar);
		
		TitledPanel teamsPanel = new TitledPanel();
		vPanel.add(teamsPanel);
		teamsPanel.setText("teams");
		teamsPanel.addContent(teamList);
		teamsPanel.setSize("200px", "400px");
		vPanel.add(teamsPanel);
		
		hPanel.add(vPanel);
		
		teamList.setSize("200px", "396px");
		
		listToList = new ListToList<UserDO>(listCell);
		hPanel.add(listToList);
		listToList.setToLbl("team members");
		listToList.setFromLbl("other users");
		
		Label lblNewLabel = new Label("teams");
		lblNewLabel.setStyleName("simpleLbl");
		lblNewLabel.setSize("199px", "30px");
		
		btnCreate.setStyleName(Resources.instance.mosStyle().commandBtn());
		btnCreate.setWidth("90px");
		
		btnDelete.setStyleName(Resources.instance.mosStyle().commandBtn());
		btnDelete.setWidth("90px");
		
		teamName.setText("team:");
	}
	
	public CellList<TeamDO> getTeamsList(){
		return teamList;
	}
	
	public HasClickHandlers getAddBtn(){
		return listToList.getAddBtn();
	}
	
	public HasClickHandlers getRemoveBtn(){
		return listToList.getRemoveBtn();
	}
	
	public HasClickHandlers getCreateBtn(){
		return btnCreate;
	}
	
	public HasClickHandlers getDeleteBtn(){
		return btnDelete;
	}
	
	public HasValue<String> getTeamName(){
		return teamName;
	}
	
	public CellList<UserDO> getFromList(){
		return listToList.getFromList();
	}
	
	public CellList<UserDO> getToList(){
		return listToList.getToList();
	}

	@Override
	public void initState() {
		teamList.setRowData(new ArrayList<TeamDO>());
		listToList.getFromList().setRowData(new ArrayList<UserDO>());
		listToList.getToList().setRowData(new ArrayList<UserDO>());
		teamName.setValue("");
	}
	
}
