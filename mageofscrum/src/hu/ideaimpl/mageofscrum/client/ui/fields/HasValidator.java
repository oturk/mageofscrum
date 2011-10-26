package hu.ideaimpl.mageofscrum.client.ui.fields;

public interface HasValidator {
	public boolean validate();
	public void setValidState(boolean isValid);
}
