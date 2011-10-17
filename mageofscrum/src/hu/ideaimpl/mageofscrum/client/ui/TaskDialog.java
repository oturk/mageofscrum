package hu.ideaimpl.mageofscrum.client.ui;

import hu.ideaimpl.mageofscrum.shared.TaskDO;

import com.google.gwt.core.ext.typeinfo.ParseException;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;

public class TaskDialog extends DialogBox {

	private Label nameLbl = new Label("name:");
	private TextBox name = new TextBox();
	private Label estTimeLbl = new Label("estimate time");
	private IntegerBox estTime = new IntegerBox();
	private Label priorityLbl = new Label("priority");
	private IntegerBox priority = new IntegerBox();
	private Label descLbl = new Label("description:");
	private RichTextArea description = new RichTextArea();
	private RichTextToolbar toolbar = new RichTextToolbar(description);
	private Label titleLbl = new Label("Create new task");
	private Button btnSave = new Button("save");
	private Label errorlbl = new Label("");

	public TaskDialog() {
		setGlassEnabled(true);
		setGlassStyleName("mosDialogGlass");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setSize("500px", "573px");
		absolutePanel.setStylePrimaryName("mosDialog");
		
		nameLbl.setStyleName("loginLbl");
		nameLbl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(nameLbl,10,53);
		nameLbl.setSize("113px", "24px");
		
		absolutePanel.add(name, 124, 47);
		name.setSize("151px", "30px");
		
		estTimeLbl.setStyleName("loginLbl");
		estTimeLbl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(estTimeLbl, 10, 97);
		estTimeLbl.setSize("113px", "24px");
		
		absolutePanel.add(estTime, 124, 91);
		estTime.setSize("151px", "30px");
		
		priorityLbl.setStyleName("loginLbl");
		priorityLbl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(priorityLbl, 10, 141);
		priorityLbl.setSize("113px", "24px");
		
		absolutePanel.add(priority, 124, 135);
		priority.setSize("151px", "30px");
		
		descLbl.setStyleName("loginLbl");
		absolutePanel.add(descLbl, 10, 177);
		descLbl.setSize("269px", "24px");
		
		absolutePanel.add(toolbar,10,206);
		toolbar.setWidth("480px");
		absolutePanel.add(description, 10, 288);
		description.setSize("480px", "236px");
		
		titleLbl.setStyleName("loginHeader");
		absolutePanel.add(titleLbl, 0, 0);
		titleLbl.setSize("500px", "30px");
		
		btnSave.setStyleName("menuButton");
		absolutePanel.add(btnSave, 10, 534);
		
		Button btnCancel = new Button("cancel");
		btnCancel.setStyleName("menuButton");
		absolutePanel.add(btnCancel, 62, 534);
		
		btnCancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				TaskDialog.this.hide();
			}
		});
		
		setWidget(absolutePanel);
		
		errorlbl.setStyleName("errorLbl");
		absolutePanel.add(errorlbl, 124, 534);
		errorlbl.setSize("361px", "20px");
	}
	
	public boolean validateForm(){
		boolean result = true;
		String msg = "";
		if(name.getValue().isEmpty()){
			nameLbl.setStyleName("invalidInputField");
			result = false;
		}else{
			nameLbl.setStyleName("inputField");
		}
		try{
			estTime.getValueOrThrow();		
			estTimeLbl.setStyleName("inputField");
		} catch (java.text.ParseException e) {
			if (result) {
				msg = "Invalid number format!";
			}
			estTimeLbl.setStyleName("invalidInputField");
			result = false;
		}
		try{
			priority.getValueOrThrow();		
			priorityLbl.setStyleName("inputField");
		} catch (java.text.ParseException e) {
			if (result) {
				msg = "Invalid number format!";
			}
			priorityLbl.setStyleName("invalidInputField");
			result = false;
		}
		if(!result & msg.isEmpty()){
			msg = "Fill require fields!";
		}
		errorlbl.setText(msg);
		return result;
	}
	
	public void clearForm(){
		name.setValue("");
		estTime.setValue(null);
		priority.setValue(null);
		description.setHTML("");
	}
	
	public TaskDO getFormData(){
		TaskDO result = new TaskDO();
		result.setName(name.getValue());
		result.setDescription(description.getHTML());
		if(estTime.getValue() != null){
			result.setEstimateTime(estTime.getValue());
		}
		if(priority.getValue() != null){
			result.setPriority(priority.getValue());
		}
		return result;
	}
	
	public HasClickHandlers getSaveBtn(){
		return btnSave;
	}
}
