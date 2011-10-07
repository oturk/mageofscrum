package hu.ideaimpl.mageofscrum.client.ui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;

public class ListToList extends Composite {

	private Label fromListLbl = new Label();
	private Label toListLbl = new Label();
	private Button addBtn = new Button("<< add");
	private Button removeBtn = new Button("remove >");
	private CellList<?> toList;
	private CellList<?> fromList;
	private AbsolutePanel absolutePanel = new AbsolutePanel();

	public ListToList(CellList<?> toList, CellList<?> fromList) {
		this.toList = toList;
		this.fromList = fromList;
		
		initWidget(absolutePanel);
		absolutePanel.setSize("571px", "500px");

//		toList = new CellList<Object>(new AbstractCell<Object>() {
//			@Override
//			public void render(Context context, Object value, SafeHtmlBuilder sb) {
//				// TODO
//			}
//		});
		this.toList.setStyleName("mosCellList");
		absolutePanel.add(this.toList, 0, 30);
		this.toList.setSize("200px", "466px");

//		fromList = new CellList<Object>(new AbstractCell<Object>() {
//			@Override
//			public void render(Context context, Object value, SafeHtmlBuilder sb) {
//				// TODO
//			}
//		});
		this.fromList.setStyleName("mosCellList");
		absolutePanel.add(this.fromList, 370, 30);
		this.fromList.setSize("196px", "466px");
		addBtn.setStyleName("menuButton");

		absolutePanel.add(addBtn, 235, 90);
		addBtn.setSize("100px", "30px");
		removeBtn.setText("remove >>");
		removeBtn.setStyleName("menuButton");

		absolutePanel.add(removeBtn, 235, 135);
		removeBtn.setSize("100px", "30px");

		toListLbl.setStyleName("simpleLbl");
		absolutePanel.add(toListLbl, 0, 0);
		toListLbl.setSize("199px", "30px");

		fromListLbl.setStyleName("simpleLbl");
		absolutePanel.add(fromListLbl, 370, 0);
		fromListLbl.setSize("199px", "30px");
	}
	
	public void setHeight(int height){
		absolutePanel.setHeight(Integer.toString(height)+"px");
		fromList.setHeight(Integer.toString(height-34)+"px");
		toList.setHeight(Integer.toString(height-34)+"px");
	}
	
	public void setFromLbl(String text){
		fromListLbl.setText(text);
	}
	
	public void setToLbl(String text){
		toListLbl.setText(text);
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
