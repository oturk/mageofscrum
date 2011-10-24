package hu.ideaimpl.mageofscrum.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.user.cellview.client.CellList.Resources;
import com.google.gwt.user.cellview.client.CellList.Style;

public interface ListResource extends Resources {
	final static ListResource instance = GWT.create(ListResource.class);
	
	@Override
	@ImageOptions(repeatStyle = RepeatStyle.Horizontal, flipRtl = true)
	public ImageResource cellListSelectedBackground();
	
	@Override
	@Source("ListStyle.css")
	public Style cellListStyle();
}
