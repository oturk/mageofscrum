package hu.ideaimpl.mageofscrum.client.ui.forms;

import hu.ideaimpl.mageofscrum.client.resources.Resources;
import hu.ideaimpl.mageofscrum.client.ui.DialogPanel;
import hu.ideaimpl.mageofscrum.client.ui.ReportData;
import hu.ideaimpl.mageofscrum.client.ui.fields.DateInputField;
import hu.ideaimpl.mageofscrum.client.ui.fields.HasValidator;
import hu.ideaimpl.mageofscrum.client.ui.fields.NumberInputField;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;

public class ReportDialog extends DialogBox implements HasValidator, HasForm<ReportData> {

	private Button btnReport = new Button("report");
	private NumberInputField numberInputField = new NumberInputField();
	private DateInputField dateInputField = new DateInputField();
	private Label lblError = new Label("");
	private TextArea textArea = new TextArea();

	public ReportDialog() {
		setGlassEnabled(true);
		setStyleName("");
		setGlassStyleName("mosDialogGlass");

		DialogPanel dialogPanel = new DialogPanel();
		dialogPanel.setText("report");
		setWidget(dialogPanel);
		dialogPanel.setSize(413, 390);

		numberInputField.setText("time:");
		numberInputField.setRequired(true);
		numberInputField.setFocus(true);
		dialogPanel.add(numberInputField);

		dateInputField.setText("date:");
		dateInputField.setRequired(true);
		dialogPanel.add(dateInputField);

		Label lblNewLabel_1 = new Label("description:");
		lblNewLabel_1.setStyleName("loginLbl");
		dialogPanel.add(lblNewLabel_1);
		lblNewLabel_1.setSize("272px", "24px");

		dialogPanel.add(textArea);
		textArea.setSize("392px", "162px");

		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(1);
		btnReport.setStyleName(Resources.instance.mosStyle().commandBtn());
		buttonBar.add(btnReport);
		
		Button btnCancel = new Button("cancel");
		btnCancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ReportDialog.this.hide();
			}
		});
		btnCancel.setStyleName(Resources.instance.mosStyle().commandBtn());
		buttonBar.add(btnCancel);
		dialogPanel.add(buttonBar);

		lblError.setStyleName("errorLbl");
		dialogPanel.add(lblError);
		lblError.setSize("100%", "20px");
	}

	public HasClickHandlers getReportBtn() {
		return btnReport;
	}

	@Override
	public boolean validate() {
		boolean ret = true;
		String msg = "";
		if (!numberInputField.validate()) {
			ret = false;
		}
		if (!dateInputField.validate()) {
			ret = false;
		}
		if (!ret) {
			msg = "fill required fields";
		}

		lblError.setText(msg);

		return ret;
	}

	@Override
	public void clearForm() {
		numberInputField.setValue(null);
		dateInputField.setValue(new Date());
		textArea.setValue("");
	}

	@Override
	public void setFormData(ReportData data) {
	}

	@Override
	public ReportData getFormData() {
		return new ReportData(numberInputField.getValue(), dateInputField.getValue(), textArea.getValue());
	}

	@Override
	public void setValidState(boolean isValid) {
		// TODO Auto-generated method stub
		
	}
}
