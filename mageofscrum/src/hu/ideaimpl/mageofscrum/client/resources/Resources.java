package hu.ideaimpl.mageofscrum.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	public Resources instance = GWT.create(Resources.class);
	
	@Source("MosStyle.css")
	public MosStyle mosStyle();
	
	@Source("mageofscrum-logo.png")
	public ImageResource mageofscrumLogo();
	
	@Source("close_24.png")
	public ImageResource closeImg();
	
	@Source("close_24_gray.png")
	public ImageResource closeImgHover();
}
