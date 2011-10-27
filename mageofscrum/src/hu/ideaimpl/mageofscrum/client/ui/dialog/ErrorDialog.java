package hu.ideaimpl.mageofscrum.client.ui.dialog;

import hu.ideaimpl.mageofscrum.client.ui.DialogPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.InlineHTML;

public class ErrorDialog extends DialogBox {
	private static ErrorDialog instance = new ErrorDialog();
	private InlineHTML inlineHTML;
	private DialogPanel dialogPanel;
	
	private ErrorDialog() {
		setGlassEnabled(true);
		setGlassStyleName("mosDialogGlass");
		setStyleName("");
		
		dialogPanel = new DialogPanel();
		dialogPanel.setText("error");
		dialogPanel.userErrorMode();
		inlineHTML = new InlineHTML();
		inlineHTML.setSize("430px", "200px");
		dialogPanel.setSize("450px", "200px");
		dialogPanel.setHeaderSize("441px", "25px");
		dialogPanel.add(inlineHTML);
		
		dialogPanel.getHeaderClick().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ErrorDialog.this.hide();
			}
		});
		
		dialogPanel.addDomHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ErrorDialog.this.hide();
			}
		}, ClickEvent.getType());
		
		setWidget(dialogPanel);
	}
	
	public void setText(String msg){
		inlineHTML.setHTML(msg);
	}
	
	public static void show(String title, String msg){
		instance.dialogPanel.setText(title);
		instance.setText(msg);
		instance.center();
		instance.show();
	}

}
