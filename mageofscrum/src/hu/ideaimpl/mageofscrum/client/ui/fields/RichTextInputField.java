package hu.ideaimpl.mageofscrum.client.ui.fields;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Label;
import hu.ideaimpl.mageofscrum.client.ui.RichTextToolbar;

public class RichTextInputField extends Composite implements Focusable, HasText, HasValue<String>, HasValidator, HasEnabled{

	private RichTextArea richTextArea = new RichTextArea();
	private RichTextToolbar richTextToolbar = new RichTextToolbar(richTextArea);
	private Label title = new Label("description:");
	private boolean required = false;

	public RichTextInputField() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(1);
		initWidget(verticalPanel);
		title.setStyleName("loginLbl");
		
		verticalPanel.add(title);
		
		verticalPanel.add(richTextToolbar);
		
		verticalPanel.add(richTextArea);
		richTextArea.setSize("620px", "200px");
		
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return null;
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public String getValue() {
		return richTextArea.getHTML();
	}

	@Override
	public void setValue(String value) {
		richTextArea.setHTML(value);
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		
	}

	@Override
	public String getText() {
		return title.getText();
	}

	@Override
	public void setText(String text) {
		title.setText(text);
	}

	@Override
	public int getTabIndex() {
		return richTextArea.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		richTextArea.setAccessKey(key);
	}

	@Override
	public void setFocus(final boolean focused) {
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			public void execute() {
				richTextArea.setFocus(focused);
			}
		});
	}

	@Override
	public void setTabIndex(int index) {
		richTextArea.setTabIndex(index);
	}
	
	public void setHTML(String html){
		richTextArea.setHTML(html);
	}
	
	public String getHTML(){
		return richTextArea.getHTML();
	}

	@Override
	public boolean isEnabled() {
		return richTextArea.isEnabled();
	}

	@Override
	public void setEnabled(final boolean enabled) {
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			public void execute() {
				richTextArea.setEnabled(enabled);
			}
		});
	}

	@Override
	public void setValidState(boolean isValid) {
		// TODO Auto-generated method stub
		
	}

}
