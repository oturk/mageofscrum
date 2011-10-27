package hu.ideaimpl.mageofscrum.client.ui.dialog;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.DialogPanel;
import hu.ideaimpl.mageofscrum.client.ui.fields.HasValidator;
import hu.ideaimpl.mageofscrum.client.ui.fields.InputField;
import hu.ideaimpl.mageofscrum.client.ui.fields.NumberInputField;
import hu.ideaimpl.mageofscrum.client.ui.fields.RichTextInputField;
import hu.ideaimpl.mageofscrum.client.ui.forms.HasForm;
import hu.ideaimpl.mageofscrum.shared.TaskDO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class TaskDialog extends DialogBox implements HasValidator, HasForm<TaskDO>{

	private InputField nameField = new InputField();
	private NumberInputField estTime = new NumberInputField();
	private NumberInputField priority = new NumberInputField();
	private RichTextInputField description = new RichTextInputField();
	private Button btnSave = new Button("save");
	private Label errorlbl = new Label("");
	private TaskDO formTask = null;
	private Button btnCancel = new Button("cancel");
	private DialogPanel dialogPanel;

	public TaskDialog() {
		setGlassEnabled(true);
		setGlassStyleName("mosDialogGlass");
		setStyleName("");
		
		dialogPanel = new DialogPanel();
		dialogPanel.setText("create task");
		dialogPanel.setSize(652, 550);
		
		nameField.setText("name:");
		nameField.setRequired(true);
		nameField.setFocus(true);
		dialogPanel.add(nameField);
		
		estTime.setText("estimate time:");
		estTime.setRequired(true);
		dialogPanel.add(estTime);
		
		priority.setText("priority:");
		priority.setRequired(true);
		dialogPanel.add(priority);
		dialogPanel.add(description);
		
		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(1);
		btnSave.setStyleName(Resources.instance.mosStyle().commandBtn());
		buttonBar.add(btnSave);
		
		btnCancel.setStyleName(Resources.instance.mosStyle().commandBtn());
		buttonBar.add(btnCancel);
		dialogPanel.add(buttonBar);
		btnCancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				TaskDialog.this.hide();
			}
		});
		
		setWidget(dialogPanel);
		
		errorlbl.setStyleName("errorLbl");
		dialogPanel.add(errorlbl);
		errorlbl.setSize("361px", "20px");
	}
	
	public void clearForm(){
		formTask = null;
		nameField.setValue("");
		estTime.setValue(null);
		priority.setValue(null);
		description.setHTML("");
	}
	
	public void setFormData(TaskDO task){
		this.formTask = task;
		nameField.setValue(task.getName());
		description.setHTML(task.getDescription());
		estTime.setValue(task.getEstimateTime());
		priority.setValue(task.getPriority());
	}
	
	public TaskDO getFormData() {
		if (formTask == null) {
			formTask = new TaskDO();
		}

		formTask.setName(nameField.getValue());
		formTask.setDescription(description.getHTML());
		if (estTime.getValue() != null) {
			formTask.setEstimateTime(estTime.getValue());
		}
		if (priority.getValue() != null) {
			formTask.setPriority(priority.getValue());
		}
		return formTask;
	}
	
	public HasClickHandlers getSaveBtn(){
		return btnSave;
	}
	
	@Override
	public void hide() {
		clearForm();
		super.hide();
	}
	
	@Override
	public boolean validate() {
		boolean result = true;
		String msg ="";
		if(!nameField.validate()){
			result = false;
		}
		if(!estTime.validate()){
			result = false;
		}
		if(!priority.validate()){
			result = false;
		}
		if(!result){
			msg = "fill required fields";
		}
		errorlbl.setText(msg);
		return result;
	}
	
	public static TaskDialog getTaskDialog(){
		TaskDialog dialog = new TaskDialog();
		return dialog;
	}
	
	public static TaskDialog getDetailsDialog(){
		TaskDialog dialog = new TaskDialog();
		dialog.nameField.setEnabled(false);
		dialog.estTime.setEnabled(false);
		dialog.priority.setEnabled(false);
		dialog.description.setEnabled(false);
		dialog.btnSave.setVisible(false);
		return dialog;
	}

	@Override
	public void setValidState(boolean isValid) {
		// TODO Auto-generated method stub
		
	}


}
