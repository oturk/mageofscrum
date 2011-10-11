package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionModel;

public class ProjectsView extends Composite{

	private Button btnCreate = new Button("save");
	private Button btnDelete = new Button("delete");
	private Button btnClearForm = new Button("clear form");
	private CellTable<ProjectDO> cellTable = new CellTable<ProjectDO>();
	private ListBox owner = new ListBox();
	private ListBox team = new ListBox();
	private TextArea description = new TextArea();
	private TextBox name = new TextBox();
	private ProjectDO project;
	private Label lblName = new Label("name:");
	private Label lblOwner = new Label("owner:");
	private Label lblDescription = new Label("description:");
	private Label lblTeam = new Label("team:");
	private Label lblError = new Label("");

	public ProjectsView() {
		
		VerticalPanel contentPanel = new VerticalPanel();
		initWidget(contentPanel);
		
		AbsolutePanel projectForm = new AbsolutePanel();
		contentPanel.add(projectForm);
		projectForm.setSize("702px", "290px");
		
		lblName.setStyleName("loginLbl");
		lblName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		projectForm.add(lblName, 10, 56);
		lblName.setSize("151px", "24px");
		
		projectForm.add(name, 10, 86);
		name.setSize("147px", "30px");
		
		lblOwner.setStyleName("loginLbl");
		lblOwner.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		projectForm.add(lblOwner, 10, 128);
		lblOwner.setSize("151px", "24px");
		
		lblDescription.setStyleName("loginLbl");
		projectForm.add(lblDescription, 181, 56);
		lblDescription.setSize("377px", "24px");
		
		projectForm.add(description, 179, 86);
		description.setSize("373px", "142px");
		
		owner.setStyleName("comboField");
		projectForm.add(owner, 10, 158);
		owner.setSize("151px", "36px");
		
		lblTeam.setStyleName("loginLbl");
		lblTeam.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		projectForm.add(lblTeam, 10, 200);
		lblTeam.setSize("151px", "24px");
		
		team.setStyleName("comboField");
		projectForm.add(team, 10, 230);
		team.setSize("151px", "36px");
		
		btnCreate.setStyleName("menuButton");
		projectForm.add(btnCreate, 181, 240);
		
		btnDelete.setStyleName("menuButton");
		projectForm.add(btnDelete, 233, 240);
		
		btnClearForm.setStyleName("menuButton");
		projectForm.add(btnClearForm, 285, 240);
		
		lblError.setStyleName("errorLbl");
		projectForm.add(lblError, 10, 10);
		lblError.setSize("548px", "24px");
		
		SimplePanel projectTable = new SimplePanel();
		contentPanel.add(projectTable);
		
		projectTable.setWidget(cellTable);
		cellTable.setSize("100%", "100%");
		
		TextColumn<ProjectDO> nameColumn = new TextColumn<ProjectDO>() {
			@Override
			public String getValue(ProjectDO object) {
				return object.getName();
			}
		};
		cellTable.addColumn(nameColumn, "Name");
		
		TextColumn<ProjectDO> ownerColumn = new TextColumn<ProjectDO>() {
			@Override
			public String getValue(ProjectDO object) {
				return object.getOwnerName();
			}
		};
		cellTable.addColumn(ownerColumn, "Owner");
		
		TextColumn<ProjectDO> teamColumn = new TextColumn<ProjectDO>() {
			@Override
			public String getValue(ProjectDO object) {
				return object.getTeamName();
			}
		};
		cellTable.addColumn(teamColumn, "Team");
	}
	
	public HasClickHandlers getSaveBtn(){
		return btnCreate;
	}
	
	public HasClickHandlers getDeleteBtn(){
		return btnDelete;
	}
	
	public HasClickHandlers getClearBtn(){
		return btnClearForm;
	}
	
	public void setSelectionModel(SelectionModel<ProjectDO> selectionModel){
		cellTable.setSelectionModel(selectionModel);
	}
	
	public void setTableData(List<ProjectDO> data){
		cellTable.setRowData(data);
	}
	
	public void setOwnerCombo(List<UserDO> users){
		for(UserDO user : users){
			owner.addItem(user.getEmail());
		}
	}
	
	public void setTeamCombo(List<TeamDO> teams){
		for(TeamDO team : teams){
			this.team.addItem(team.getName());
		}
	}
	
	public void initView(){
		owner.clear();
		team.clear();
	}
	
	public boolean validateForm(){
		String msg = "";
		boolean result = true;
		if(name.getValue().isEmpty()){
			lblName.setStyleName("invalidInputField");
			result = false;
		}else{
			lblName.setStyleName("inputField");
		}
		if(!result){
			msg = "please, fill required fields";
		}
		lblError.setText(msg);
		return result;
	}
	
	public ProjectDO getFormData() {
		if (project == null) {
			project = new ProjectDO();
		}
		project.setName(name.getValue());
		project.setDescription(description.getValue());
		project.setOwnerName(owner.getValue(owner.getSelectedIndex()));
		project.setTeamName(team.getValue(team.getSelectedIndex()));

		return project;
	}
	
	public void setFormData(ProjectDO data, int ownerIndex, int teamIndex){
		this.project = data;
		name.setValue(data.getName());
		description.setValue(data.getDescription());
		owner.setItemSelected(ownerIndex, true);
		team.setItemSelected(teamIndex, true);
		
//		owner.setItemSelected(index, selected);
	}
}
