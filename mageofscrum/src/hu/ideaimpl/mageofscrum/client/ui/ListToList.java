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
	private Button addBtn = new Button("< add");
	private Button removeBtn = new Button("remove >");
	private Button addAllBtn = new Button("<< add all");
	private Button removeAllBtn = new Button("remove all >>");
	private CellList<?> toList;
	private CellList<?> fromList;

	public ListToList(CellList<?> toList, CellList<?> fromList) {
		this.toList = toList;
		this.fromList = fromList;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("571px", "500px");

//		toList = new CellList<Object>(new AbstractCell<Object>() {
//			@Override
//			public void render(Context context, Object value, SafeHtmlBuilder sb) {
//				// TODO
//			}
//		});
		toList.setStyleName("mosCellList");
		absolutePanel.add(toList, 0, 30);
		toList.setSize("200px", "466px");

//		fromList = new CellList<Object>(new AbstractCell<Object>() {
//			@Override
//			public void render(Context context, Object value, SafeHtmlBuilder sb) {
//				// TODO
//			}
//		});
		fromList.setStyleName("mosCellList");
		absolutePanel.add(fromList, 370, 30);
		fromList.setSize("196px", "466px");

		absolutePanel.add(addBtn, 235, 90);
		addBtn.setSize("100px", "30px");

		absolutePanel.add(removeBtn, 235, 135);
		removeBtn.setSize("100px", "30px");

		absolutePanel.add(addAllBtn, 235, 180);
		addAllBtn.setSize("100px", "30px");

		absolutePanel.add(removeAllBtn, 235, 225);
		removeAllBtn.setSize("100px", "30px");

		toListLbl.setStyleName("simpleLbl");
		absolutePanel.add(toListLbl, 0, 0);
		toListLbl.setSize("199px", "30px");

		fromListLbl.setStyleName("simpleLbl");
		absolutePanel.add(fromListLbl, 370, 0);
		fromListLbl.setSize("199px", "30px");
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

	public HasClickHandlers getAddAllBtn() {
		return addAllBtn;
	}

	public HasClickHandlers getRemoveAllBtn() {
		return removeAllBtn;
	}

}
