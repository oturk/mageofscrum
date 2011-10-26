package hu.ideaimpl.mageofscrum.client.view;

import hu.ideaimpl.mageofscrum.client.resources.MosStyle;
import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.resources.TableResource;
import hu.ideaimpl.mageofscrum.client.ui.TitledPanel;
import hu.ideaimpl.mageofscrum.client.ui.fields.ComboBoxInputField;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionModel;

public class ProjectsView extends Composite{

	private Button btnCreate = new Button("save");
	private Button btnDelete = new Button("delete");
	private Button btnClearForm = new Button("clear form");
	private Button btnNewButton = new Button("create");
	private CellTable<ProjectDO> projectsTable = new CellTable<ProjectDO>(3, TableResource.instance);
//	private ListBox owner = new ListBox();
	private ComboBoxInputField<UserDO> owner = new ComboBoxInputField<UserDO>();
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
		MosStyle style = Resources.instance.mosStyle();
		style.ensureInjected();
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(5);
		initWidget(verticalPanel);
		
		HorizontalPanel buttonBar = new HorizontalPanel();
		verticalPanel.add(buttonBar);
		buttonBar.setSpacing(1);
		buttonBar.add(btnCreate);
		buttonBar.add(btnDelete);
		buttonBar.add(btnClearForm);
		
		btnNewButton.setText("create");
		btnNewButton.setStyleName(Resources.instance.mosStyle().commandBtn());
		buttonBar.add(btnNewButton);
		buttonBar.add(lblError);
		btnCreate.setStyleName(style.commandBtn());
		btnDelete.setStyleName(style.commandBtn());
		btnClearForm.setStyleName(style.commandBtn());
		lblError.setStyleName(style.errorLbl());
		lblError.setSize("548px", "24px");
		
		AbsolutePanel projectForm = new AbsolutePanel();
		verticalPanel.add(projectForm);
		projectForm.setSize("702px", "247px");
		
		lblName.setStyleName("loginLbl");
		lblName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		projectForm.add(lblName, 10, 10);
		lblName.setSize("151px", "24px");
		
		projectForm.add(name, 10, 40);
		name.setSize("147px", "30px");
		
		lblOwner.setStyleName("loginLbl");
		lblOwner.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		projectForm.add(lblOwner, 10, 82);
		lblOwner.setSize("151px", "24px");
		
		lblDescription.setStyleName("loginLbl");
		projectForm.add(lblDescription, 181, 10);
		lblDescription.setSize("377px", "24px");
		
		projectForm.add(description, 179, 40);
		description.setSize("373px", "142px");
		
		owner.setStyleName("comboField");
		projectForm.add(owner, 10, 112);
		owner.setSize("151px", "36px");
		
		lblTeam.setStyleName("loginLbl");
		lblTeam.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		projectForm.add(lblTeam, 10, 154);
		lblTeam.setSize("151px", "24px");
		
		team.setStyleName("comboField");
		projectForm.add(team, 10, 184);
		team.setSize("151px", "36px");
		
//		SimplePanel simplePanel = new SimplePanel();
//		verticalPanel.add(simplePanel);
		
		projectsTable.setSize("560px", "100px");
		TitledPanel projectsPanel = new TitledPanel();
		verticalPanel.add(projectsPanel);
		projectsPanel.setText("projects");
		projectsPanel.addContent(projectsTable);
		projectsPanel.setSize("560px", "200px");
		
//		simplePanel.setWidget(projectsTable);
		
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
	
	public HasClickHandlers getSaveBtn(){
		return btnCreate;
	}
	
	public HasClickHandlers getDeleteBtn(){
		return btnDelete;
	}
	
	public HasClickHandlers getClearBtn(){
		return btnClearForm;
	}
	
	public HasClickHandlers getCreateBtn(){
		return btnNewButton;
	}
	
	public CellTable<ProjectDO> getProjectsTable(){
		return projectsTable;
	}
	
	public void setSelectionModel(SelectionModel<ProjectDO> selectionModel){
		projectsTable.setSelectionModel(selectionModel);
	}
	
	public void setTableData(List<ProjectDO> data){
		projectsTable.setRowData(data);
	}
	
	public void setOwnerCombo(List<UserDO> users){
		List<String> labels = new ArrayList<String>();
		for(UserDO user : users){
			labels.add(user.getUsername());
		}
		owner.addItems(labels, users);
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
//		project.setOwnerName(owner.getValue(owner.getSelectedIndex()));
		project.setTeamName(team.getValue(team.getSelectedIndex()));

		return project;
	}
	
	public void setFormData(ProjectDO data, int ownerIndex, int teamIndex){
		this.project = data;
		name.setValue(data.getName());
		description.setValue(data.getDescription());
//		owner.setItemSelected(ownerIndex, true);
		team.setItemSelected(teamIndex, true);
		
//		owner.setItemSelected(index, selected);
	}
	
	public void clearForm(){
		this.project = null;
		name.setValue("");
		description.setValue("");
	}
}
