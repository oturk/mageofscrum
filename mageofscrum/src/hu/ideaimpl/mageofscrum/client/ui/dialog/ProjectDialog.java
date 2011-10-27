package hu.ideaimpl.mageofscrum.client.ui.dialog;

import java.util.ArrayList;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.DialogPanel;
import hu.ideaimpl.mageofscrum.client.ui.fields.ComboBoxInputField;
import hu.ideaimpl.mageofscrum.client.ui.fields.HasItems;
import hu.ideaimpl.mageofscrum.client.ui.fields.HasValidator;
import hu.ideaimpl.mageofscrum.client.ui.fields.InputField;
import hu.ideaimpl.mageofscrum.client.ui.fields.RichTextInputField;
import hu.ideaimpl.mageofscrum.client.ui.forms.HasForm;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class ProjectDialog extends DialogBox implements HasValidator, HasForm<ProjectDO>{

	private InputField nameField = new InputField();
	private ComboBoxInputField<UserDO> owners = new ComboBoxInputField<UserDO>();
	private ComboBoxInputField<TeamDO> teams = new ComboBoxInputField<TeamDO>();
	private Button btnSave = new Button("save");
	private Label errorLbl = new Label("");
	private ProjectDO project;
	private RichTextInputField richTextInputField = new RichTextInputField();

	public ProjectDialog() {
		setGlassEnabled(true);
		setGlassStyleName("mosDialogGlass");
		setStyleName("");
		
		DialogPanel dialogPanel = new DialogPanel();
		dialogPanel.setText("create project");
		dialogPanel.setSize(642, 520);
		setWidget(dialogPanel);
		
		nameField.setText("name:");
		nameField.setFocus(true);
		nameField.setRequired(true);
		nameField.setTabIndex(1);
		dialogPanel.add(nameField);
		
		owners.setText("owner");
		owners.setTabIndex(2);
		dialogPanel.add(owners);
		
		teams.setText("team:");
		teams.setTabIndex(3);
		dialogPanel.add(teams);
		
		richTextInputField.setTabIndex(4);
		richTextInputField.setText("description:");
		dialogPanel.add(richTextInputField);
		
		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(1);
		btnSave.setStyleName(Resources.instance.mosStyle().commandBtn());
		errorLbl.setStyleName(Resources.instance.mosStyle().errorLbl());
		
		Button btnClose = new Button("close");
		btnClose.setStyleName(Resources.instance.mosStyle().commandBtn());
		buttonBar.add(btnSave);
		buttonBar.add(btnClose);
		buttonBar.add(errorLbl);
		dialogPanel.add(buttonBar);
		btnClose.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ProjectDialog.this.hide();
			}
		});
		
	}
	
	public HasClickHandlers getSaveBtn(){
		return btnSave;
	}
	
	public HasItems<UserDO> getOwnersCombo(){
		return owners;
	}
	
	public HasItems<TeamDO> getTeamsCombo(){
		return teams;
	}

	@Override
	public void clearForm() {
		project = null;
		nameField.setValue("");
		owners.addItems(new ArrayList<String>(), new ArrayList<UserDO>());
		teams.addItems(new ArrayList<String>(), new ArrayList<TeamDO>());
		richTextInputField.setValue("");
	}

	@Override
	public void setFormData(ProjectDO data) {
		this.project = data;
		nameField.setValue(data.getName());
		richTextInputField.setValue(data.getDescription());
//		 owners.setItemSelected(ownerIndex, true);
//		teams.setItemSelected(teamIndex, true);

//		 owners.setItemSelected(index, selected);
		
	}

	@Override
	public ProjectDO getFormData() {
		if (project == null) {
			project = new ProjectDO();
		}
		project.setName(nameField.getValue());
		project.setDescription(richTextInputField.getValue());
		 project.setOwnerName(owners.getValue().getUsername());
		project.setTeamName(teams.getValue().getName());

		return project;
	}

	@Override
	public boolean validate() {
		String msg = "";
		boolean result = true;
		if (!nameField.validate()) {
			result = false;
		}
		if (!result) {
			msg = "please, fill required fields";
		}
		errorLbl.setText(msg);
		return result;
	}

	@Override
	public void setValidState(boolean isValid) {
		
	}
	
}
