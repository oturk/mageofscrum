package hu.ideaimpl.mageofscrum.client.ui.forms;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.inputfields.ComboBoxInputField;
import hu.ideaimpl.mageofscrum.client.ui.inputfields.HasValidator;
import hu.ideaimpl.mageofscrum.client.ui.inputfields.InputField;
import hu.ideaimpl.mageofscrum.shared.ProjectDO;
import hu.ideaimpl.mageofscrum.shared.TeamDO;
import hu.ideaimpl.mageofscrum.shared.UserDO;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProjectDialog extends DialogBox implements Focusable, HasValidator, HasForm<ProjectDO>{

	private InputField nameField = new InputField();
	private Label closeImg = new Label("");

	public ProjectDialog() {
		setGlassEnabled(true);
		setGlassStyleName("mosDialogGlass");
//		setSize("450px", "500px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSize("460px", "520px");
		verticalPanel.setStylePrimaryName(Resources.instance.mosStyle().dialogPanel());
		setWidget(verticalPanel);
		
		HorizontalPanel headerPanel = new HorizontalPanel();
		headerPanel.setStyleName(Resources.instance.mosStyle().dialogHeader());
		headerPanel.setSize("450px", "500px");
		headerPanel.getElement().getStyle().setMarginTop(5, Style.Unit.PX);
		verticalPanel.add(headerPanel);
		headerPanel.setWidth("100%");
		headerPanel.setHeight("30px");
		
		Label lblNewLabel = new Label("create project");
		lblNewLabel.setWidth("426px");
		lblNewLabel.setHeight("30px");
		lblNewLabel.setStyleName(Resources.instance.mosStyle().dialogHeaderText());
		headerPanel.add(lblNewLabel);
		
		closeImg.setStyleName(Resources.instance.mosStyle().closeImgStyle());
		closeImg.getElement().getStyle().setMarginTop(3, Style.Unit.PX);
		closeImg.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ProjectDialog.this.hide();
			}
		});
		closeImg.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				closeImg.setStyleName(Resources.instance.mosStyle().closeImgStyleHover());
			}
		});
		closeImg.addMouseOutHandler(new MouseOutHandler() {
			public void onMouseOut(MouseOutEvent event) {
				closeImg.setStyleName(Resources.instance.mosStyle().closeImgStyle());
			}
		});
		headerPanel.add(closeImg);
		
		nameField.setText("name:");
		verticalPanel.add(nameField);
		
		ComboBoxInputField<UserDO> comboBoxInputField = new ComboBoxInputField<UserDO>();
		comboBoxInputField.setText("owner");
		verticalPanel.add(comboBoxInputField);
		
		ComboBoxInputField<TeamDO> comboBoxInputField_1 = new ComboBoxInputField<TeamDO>();
		comboBoxInputField_1.setText("team:");
		verticalPanel.add(comboBoxInputField_1);
		
		RichTextInputField richTextInputField = new RichTextInputField();
		richTextInputField.setText("description:");
		verticalPanel.add(richTextInputField);
		
		Button btnClose = new Button("close");
		btnClose.setStyleName(Resources.instance.mosStyle().commandBtn());
		verticalPanel.add(btnClose);
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
	public void setFormData() {
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
	public int getTabIndex() {
		return nameField.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		nameField.setAccessKey(key);
	}

	@Override
	public void setFocus(final boolean focused) {
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			public void execute() {
				nameField.setFocus(focused);
			}
		});
		
	}

	@Override
	public void setTabIndex(int index) {
		nameField.setTabIndex(index);
	}

}
