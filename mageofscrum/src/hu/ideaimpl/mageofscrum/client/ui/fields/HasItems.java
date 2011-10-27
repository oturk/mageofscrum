package hu.ideaimpl.mageofscrum.client.ui.fields;

import java.util.List;

public interface HasItems<T> {
	public void addItems(List<String> labels, List<T> values);
	public T getValue();
	public void selectItem(String item);
}
