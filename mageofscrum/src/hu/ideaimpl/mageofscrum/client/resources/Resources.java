package hu.ideaimpl.mageofscrum.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	public static class Util{
		private static Resources resource = null;
		
		public static Resources getResources(){
			if(resource == null){
				resource = GWT.create(Resources.class);
			}
			return resource;
		}
	}
	
	@Source("projects-icon.png")
	public ImageResource projectsIcon();

	@Source("backlog-icon.png")
	public ImageResource backlogIcon();
	
	@Source("manage-icon.png")
	public ImageResource manageIcon();
	
	@Source("sprint-icon.png")
	public ImageResource sprintIcon();
	
	@Source("error-logo.png")
	public ImageResource errorLogo();
	
	@Source("mageofscrum-logo.png")
	public ImageResource mageofscrumLogo();
}
