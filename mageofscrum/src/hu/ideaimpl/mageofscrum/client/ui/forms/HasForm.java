package hu.ideaimpl.mageofscrum.client.ui.forms;

public interface HasForm<T> {
	public void clearForm();
	public void setFormData(T data);
	public T getFormData();
}
