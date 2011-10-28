package hu.ideaimpl.mageofscrum.client.ui;

import hu.ideaimpl.mageofscrum.client.resources.ListResource;
import hu.ideaimpl.mageofscrum.client.resources.Resources;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListToList<T> extends Composite {

	private Label fromListLbl = new Label();
	private Label toListLbl = new Label();
	private Button addBtn = new Button("<< add");
	private Button removeBtn = new Button("remove >>");
	private CellList<T> fromList;
	private CellList<T> toList;
	private HorizontalPanel hPanel = new HorizontalPanel();
	private TitledPanel fromPanel = new TitledPanel();
	private TitledPanel toPanel = new TitledPanel();

	public ListToList(AbstractCell<T> cell) {
		initWidget(hPanel);
		hPanel.setSpacing(10);

		toList = new CellList<T>(cell,ListResource.instance);
		toList.setSize("200px", "466px");
		fromList = new CellList<T>(cell,ListResource.instance);
		fromList.setSize("196px", "466px");
		
		VerticalPanel buttonBar = new VerticalPanel();
		buttonBar.setSpacing(1);
		buttonBar.add(addBtn);
		buttonBar.add(removeBtn);
		addBtn.setStyleName(Resources.instance.mosStyle().commandBtn());
		removeBtn.setStyleName(Resources.instance.mosStyle().commandBtn());
		
		hPanel.add(fromPanel);
		hPanel.add(buttonBar);
		fromPanel.setText("user's roles");
		fromPanel.addContent(fromList);
		fromPanel.setSize("200px", "400px");
		
		hPanel.add(toPanel);
		toPanel.setText("user's roles");
		toPanel.addContent(toList);
		toPanel.setSize("200px", "400px");
		
		addBtn.setSize("100px", "30px");
		removeBtn.setSize("100px", "30px");
	}
	
	public CellList<T> getFromList(){
		return fromList;
	}
	public CellList<T> getToList(){
		return toList;
	}
	public void setHeight(int height){
		hPanel.setHeight(Integer.toString(height)+"px");
		fromList.setHeight(Integer.toString(height-34)+"px");
		toList.setHeight(Integer.toString(height-34)+"px");
	}
	
	public void setToLbl(String text){
		fromPanel.setText(text);
	}
	
	public void setFromLbl(String text){
		toPanel.setText(text);
	}

	public HasText getFromLbl() {
		return fromListLbl;
	}

	public HasText gerToLbl() {
		return toListLbl;
	}

	public HasClickHandlers getAddBtn() {
		return addBtn;
	}

	public HasClickHandlers getRemoveBtn() {
		return removeBtn;
	}

}
