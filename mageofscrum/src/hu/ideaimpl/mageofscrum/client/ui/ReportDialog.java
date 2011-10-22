package hu.ideaimpl.mageofscrum.client.ui;

import hu.ideaimpl.mageofscrum.client.ui.inputfields.DateInputField;
import hu.ideaimpl.mageofscrum.client.ui.inputfields.NumberInputField;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
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
		setGlassStyleName("mosDialogGlass");

		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("404px", "391px");
		absolutePanel.setStylePrimaryName("mosDialog");

		numberInputField.setText("time:");
		numberInputField.setRequired(true);
		absolutePanel.add(numberInputField, 0, 62);

		dateInputField.setText("date:");
		dateInputField.setRequired(true);
		absolutePanel.add(dateInputField, 0, 105);

		Label lblNewLabel = new Label("Report to task");
		lblNewLabel.setStyleName("loginHeader");
		absolutePanel.add(lblNewLabel, 0, 0);
		lblNewLabel.setSize("100%", "30px");

		Label lblNewLabel_1 = new Label("description:");
		lblNewLabel_1.setStyleName("loginLbl");
		absolutePanel.add(lblNewLabel_1, 0, 148);
		lblNewLabel_1.setSize("272px", "24px");

		absolutePanel.add(textArea, 0, 178);
		textArea.setSize("392px", "162px");

		btnReport.setStyleName("menuButton");
		absolutePanel.add(btnReport, 0, 352);

		Button btnCancel = new Button("cancel");
		btnCancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ReportDialog.this.hide();
			}
		});
		btnCancel.setStyleName("menuButton");
		absolutePanel.add(btnCancel, 61, 352);

		lblError.setStyleName("errorLbl");
		absolutePanel.add(lblError, 0, 36);
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
	public void setFormData() {
	}

	@Override
	public ReportData getFormData() {
		return new ReportData(numberInputField.getValue(), dateInputField.getValue(), textArea.getValue());
	}
}
