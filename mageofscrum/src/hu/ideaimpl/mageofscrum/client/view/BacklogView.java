package hu.ideaimpl.mageofscrum.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.cellview.client.CellTable;
import hu.ideaimpl.mageofscrum.shared.TaskDO;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.Column;
import java.util.Date;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HasHandlers;

public class BacklogView extends Composite {

	private Label lblName;
	private TextBox name;
	private Label lblEstimateTime;
	private TextBox estTime;
	private Label lblPriority;
	private TextBox priority;
	private Label lblDescription;
	private TextArea description;
	private Button btnSave;
	private Button btnDelete;
	private Button btnClearForm;
	private CellTable<TaskDO> backlog;

	public BacklogView() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		verticalPanel.add(absolutePanel);
		absolutePanel.setSize("542px", "308px");
		
		lblName = new Label("name:");
		lblName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		lblName.setStyleName("loginLbl");
		absolutePanel.add(lblName, 0, 10);
		lblName.setSize("113px", "24px");
		
		name = new TextBox();
		absolutePanel.add(name, 112, 4);
		name.setSize("147px", "30px");
		
		lblEstimateTime = new Label("estimate time:");
		lblEstimateTime.setStyleName("loginLbl");
		lblEstimateTime.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblEstimateTime, 269, 10);
		lblEstimateTime.setSize("113px", "24px");
		
		estTime = new TextBox();
		absolutePanel.add(estTime, 381, 4);
		estTime.setSize("151px", "30px");
		
		lblPriority = new Label("priority:");
		lblPriority.setStyleName("loginLbl");
		lblPriority.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblPriority, 269, 56);
		lblPriority.setSize("113px", "24px");
		
		priority = new TextBox();
		absolutePanel.add(priority, 381, 50);
		priority.setSize("151px", "30px");
		
		lblDescription = new Label("description:");
		lblDescription.setStyleName("loginLbl");
		lblDescription.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblDescription, 0, 69);
		lblDescription.setSize("263px", "24px");
		
		description = new TextArea();
		absolutePanel.add(description, 0, 92);
		description.setSize("530px", "164px");
		
		btnSave = new Button("save");
		btnSave.setStyleName("menuButton");
		absolutePanel.add(btnSave, 0, 268);
		
		btnDelete = new Button("delete");
		btnDelete.setStyleName("menuButton");
		absolutePanel.add(btnDelete, 55, 268);
		
		btnClearForm = new Button("clear form");
		btnClearForm.setStyleName("menuButton");
		absolutePanel.add(btnClearForm, 112, 268);
		
		backlog = new CellTable<TaskDO>();
		verticalPanel.add(backlog);
		backlog.setWidth("537px");
		
		TextColumn<TaskDO> nameColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return object.toString();
			}
		};
		backlog.addColumn(nameColumn, "name");
		
		TextColumn<TaskDO> estTimeColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return object.toString();
			}
		};
		backlog.addColumn(estTimeColumn, "estimate time");
		
		TextColumn<TaskDO> priorityColumn = new TextColumn<TaskDO>() {
			@Override
			public String getValue(TaskDO object) {
				return object.toString();
			}
		};
		backlog.addColumn(priorityColumn, "priority");
		
		Column<TaskDO, Date> createdColumn = new Column<TaskDO, Date>(new DateCell()) {
			@Override
			public Date getValue(TaskDO object) {
				return (Date) null;
			}
		};
		backlog.addColumn(createdColumn, "created");
	}
	
	private HasClickHandlers getSaveBtn(){
		return btnSave;
	}
	
	private HasClickHandlers getDeleteBtn(){
		return btnDelete;
	}
	
	private HasClickHandlers getClearBtn(){
		return btnClearForm;
	}
	
	private HasHandlers getBacklogTable(){
		return backlog;
	}
}
