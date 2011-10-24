package hu.ideaimpl.mageofscrum.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.CellTable.Style;

public interface TableResource extends Resources {
	public TableResource instance = GWT.create(TableResource.class);
	 /**
     * The background used for footer cells.
     */
    @Source("cellTableHeaderBackground.png")
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal, flipRtl = true)
    ImageResource cellTableFooterBackground();

    /**
     * The background used for header cells.
     */
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal, flipRtl = true)
    ImageResource cellTableHeaderBackground();

    /**
     * The loading indicator used while the table is waiting for data.
     */
    @ImageOptions(flipRtl = true)
    ImageResource cellTableLoading();

    /**
     * The background used for selected cells.
     */
    @Source("cellListSelectedBackground.png")
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal, flipRtl = true)
    ImageResource cellTableSelectedBackground();

    /**
     * Icon used when a column is sorted in ascending order.
     */
    @Source("sortAscending.png")
    @ImageOptions(flipRtl = true)
    ImageResource cellTableSortAscending();

    /**
     * Icon used when a column is sorted in descending order.
     */
    @Source("sortDescending.png")
    @ImageOptions(flipRtl = true)
    ImageResource cellTableSortDescending();

    /**
     * The styles used in this widget.
     */
    @Source("TableStyle.css")
    Style cellTableStyle();
}
