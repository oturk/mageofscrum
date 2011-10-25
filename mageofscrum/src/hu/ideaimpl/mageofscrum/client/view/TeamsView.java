package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.ui.ListToList;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.HasSelectionChangedHandlers;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import hu.ideaimpl.mageofscrum.client.ui.inputfields.InputField;

public class TeamsView extends Composite {

	private CellList<TeamDO> teamList;
	private SingleSelectionModel<TeamDO> teamSelectionModel = new SingleSelectionModel<TeamDO>();
	private MultiSelectionModel<UserDO> memebersSelectionModel = new MultiSelectionModel<UserDO>();
	private MultiSelectionModel<UserDO> othersSelectionModel = new MultiSelectionModel<UserDO>();
	private CellList<UserDO> otherUsersList;
	private CellList<UserDO> teamMembersList;
	private ListToList listToList;
	private Button btnCreate = new Button("create");
	private Button btnDelete = new Button("delete");
	private InputField teamName = new InputField();

	public TeamsView() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("850px", "500px");
		teamList = new CellList<TeamDO>(new AbstractCell<TeamDO>() {
			@Override
			public void render(Context context, TeamDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
			}
		});
		teamList.setSelectionModel(teamSelectionModel);
		teamList.setStyleName("mosCellList");
		absolutePanel.add(teamList, 0, 100);
		teamList.setSize("200px", "396px");
		
		otherUsersList = new CellList<UserDO>(new AbstractCell<UserDO>() {
			@Override
			public void render(Context context, UserDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getUsername());
			}
		});
		otherUsersList.setSelectionModel(othersSelectionModel);
		
		teamMembersList = new CellList<UserDO>(new AbstractCell<UserDO>() {
			@Override
			public void render(Context context, UserDO value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getUsername());
			}
		});
		teamMembersList.setHeight("400px");
		teamMembersList.setSelectionModel(memebersSelectionModel);
		
		listToList = new ListToList(teamMembersList, otherUsersList);
		listToList.setFromLbl("team members");
		listToList.setToLbl("other users");
		absolutePanel.add(listToList, 279, 0);
		
		Label lblNewLabel = new Label("teams");
		lblNewLabel.setStyleName("simpleLbl");
		absolutePanel.add(lblNewLabel, 0, 70);
		lblNewLabel.setSize("199px", "30px");
		
		absolutePanel.add(btnCreate, 0, 38);
		btnCreate.setWidth("90px");
		
		absolutePanel.add(btnDelete, 114, 38);
		btnDelete.setWidth("90px");
		
		teamName.setText("team:");
		absolutePanel.add(teamName, 0, 0);
	}
	
	public CellList<TeamDO> getTeamList(){
		return teamList;
	}
	
	public HasSelectionChangedHandlers getTeamSelection(){
		return teamSelectionModel;
	}
	
	public Long getSelectedObject(){
		return teamSelectionModel.getSelectedObject().getId();
	}
	
	public TeamDO getSelectedTeam(){
		return teamSelectionModel.getSelectedObject();
	}
	
	public Set<UserDO> getSelectedMembers(){
		return memebersSelectionModel.getSelectedSet();
	}
	
	public Set<UserDO> getSelectedOthers(){
		return othersSelectionModel.getSelectedSet();
	}
	
	public void setTeams(List<TeamDO> members){
		teamList.setRowData(members);
	}
	
	public void setTeamMembers(List<UserDO> members){
		teamMembersList.setRowData(members);
	}
	
	public void setOtherUsers(List<UserDO> notMembers){
		otherUsersList.setRowData(notMembers);
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
	
	public void clearListToList(){
		otherUsersList.setRowData(new ArrayList<UserDO>());
		teamMembersList.setRowData(new ArrayList<UserDO>());
	}
}
