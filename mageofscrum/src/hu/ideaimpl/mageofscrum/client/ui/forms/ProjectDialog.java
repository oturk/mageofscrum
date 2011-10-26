package hu.ideaimpl.mageofscrum.client.ui.forms;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.DialogPanel;
import hu.ideaimpl.mageofscrum.client.ui.fields.ComboBoxInputField;
import hu.ideaimpl.mageofscrum.client.ui.fields.HasValidator;
import hu.ideaimpl.mageofscrum.client.ui.fields.InputField;
import hu.ideaimpl.mageofscrum.client.ui.fields.RichTextInputField;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

public class ProjectDialog extends DialogBox implements HasValidator, HasForm<ProjectDO>{

	private InputField nameField = new InputField();

	public ProjectDialog() {
		setGlassEnabled(true);
		setGlassStyleName("mosDialogGlass");
		setStyleName("");
		
		DialogPanel dialogPanel = new DialogPanel();
		dialogPanel.setText("create project");
		dialogPanel.setSize(470, 520);
		setWidget(dialogPanel);
		
		nameField.setText("name:");
		nameField.setFocus(true);
		nameField.setTabIndex(1);
		dialogPanel.add(nameField);
		
		ComboBoxInputField<UserDO> comboBoxInputField = new ComboBoxInputField<UserDO>();
		comboBoxInputField.setText("owner");
		comboBoxInputField.setTabIndex(2);
		dialogPanel.add(comboBoxInputField);
		
		ComboBoxInputField<TeamDO> comboBoxInputField_1 = new ComboBoxInputField<TeamDO>();
		comboBoxInputField_1.setText("team:");
		comboBoxInputField_1.setTabIndex(3);
		dialogPanel.add(comboBoxInputField_1);
		
		RichTextInputField richTextInputField = new RichTextInputField();
		richTextInputField.setTabIndex(4);
		richTextInputField.setText("description:");
		dialogPanel.add(richTextInputField);
		
		Button btnClose = new Button("close");
		btnClose.setStyleName(Resources.instance.mosStyle().commandBtn());
		dialogPanel.add(btnClose);
		btnClose.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ProjectDialog.this.hide();
			}
		});
		
	}

	@Override
	public void clearForm() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setFormData(ProjectDO data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProjectDO getFormData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void setValidState(boolean isValid) {
		// TODO Auto-generated method stub
		
	}

}
