package hu.ideaimpl.mageofscrum.client.ui.inputfields;

import java.util.List;

public interface HasItems<T> {
	public void addItems(List<String> labels, List<T> values);
	public T getValue();
}
