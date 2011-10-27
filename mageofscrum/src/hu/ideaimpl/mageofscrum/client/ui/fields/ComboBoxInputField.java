package hu.ideaimpl.mageofscrum.client.ui.fields;


import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class ComboBoxInputField<T> extends Composite implements Focusable, HasValidator, HasText, HasItems<T>{

	private Label lblTitle = new Label("title:");
	private ListBox listBox = new ListBox();
	private boolean required = false;
	private List<T> items;
	private List<String> labels;
	
	public ComboBoxInputField() {

		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("273px", "37px");

		lblTitle.setStyleName("loginLbl");
		lblTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		absolutePanel.add(lblTitle, 0, 6);
		lblTitle.setSize("113px", "24px");

		absolutePanel.add(listBox, 116, 0);
		listBox.setSize("156px", "35px");
	}

	public void setRequired(boolean value) {
		required = value;
	}

	@Override
	public boolean validate() {
		if (required && getValue() != null) {
			lblTitle.setStyleName("invalidInputField");
			return false;
		} else {
			lblTitle.setStyleName("inputField");
			return true;
		}
	}

	@Override
	public String getText() {
		return lblTitle.getText();
	}

	@Override
	public void setText(String text) {
		lblTitle.setText(text);
	}

	@Override
	public void addItems(List<String> labels, List<T> values) {
		this.items = values;
		this.labels = labels;
		listBox.clear();
		for(String label : labels){
			listBox.addItem(label);
		}
		
	}

	@Override
	public T getValue() {
		if(items != null){
			return items.get(listBox.getSelectedIndex());
		}
		return null;
	}
	
	public void clear(){
		listBox.clear();
	}

	@Override
	public int getTabIndex() {
		return listBox.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		listBox.setAccessKey(key);
	}

	@Override
	public void setFocus(final boolean focused) {
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			public void execute() {
				listBox.setFocus(focused);
			}
		});
	}

	@Override
	public void setTabIndex(int index) {
		listBox.setTabIndex(index);
	}

	@Override
	public void setValidState(boolean isValid) {
		// TODO Auto-generated method stub
	}

	@Override
	public void selectItem(String item) {
		int index = labels.indexOf(item);
		listBox.setItemSelected(index, true);
	}

}
