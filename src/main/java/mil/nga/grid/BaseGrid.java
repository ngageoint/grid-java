package mil.nga.grid;

import mil.nga.grid.color.Color;

/**
 * Base Grid
 * 
 * @author osbornb
 */
public class BaseGrid {

	/**
	 * Enabled grid
	 */
	private boolean enabled;

	/**
	 * Minimum zoom level
	 */
	private int minZoom;

	/**
	 * Maximum zoom level
	 */
	private Integer maxZoom;

	/**
	 * Minimum zoom level override for drawing grid lines
	 */
	private Integer linesMinZoom;

	/**
	 * Maximum zoom level override for drawing grid lines
	 */
	private Integer linesMaxZoom;

	/**
	 * Grid line style
	 */
	private GridStyle style = new GridStyle();

	/**
	 * Grid labeler
	 */
	private Labeler labeler;

	/**
	 * Constructor
	 */
	public BaseGrid() {

	}

	/**
	 * Is the grid enabled
	 * 
	 * @return enabled flag
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Set the enabled flag
	 * 
	 * @param enabled
	 *            enabled flag
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Get the minimum zoom level
	 * 
	 * @return minimum zoom level
	 */
	public int getMinZoom() {
		return minZoom;
	}

	/**
	 * Set the minimum zoom level
	 * 
	 * @param minZoom
	 *            minimum zoom level
	 */
	public void setMinZoom(int minZoom) {
		this.minZoom = minZoom;
	}

	/**
	 * Get the maximum zoom level
	 * 
	 * @return maximum zoom level
	 */
	public Integer getMaxZoom() {
		return maxZoom;
	}

	/**
	 * Has a maximum zoom level
	 * 
	 * @return true if has a maximum, false if unbounded
	 */
	public boolean hasMaxZoom() {
		return maxZoom != null;
	}

	/**
	 * Set the maximum zoom level
	 * 
	 * @param maxZoom
	 *            maximum zoom level
	 */
	public void setMaxZoom(Integer maxZoom) {
		this.maxZoom = maxZoom;
	}

	/**
	 * Is the zoom level within the grid zoom range
	 * 
	 * @param zoom
	 *            zoom level
	 * @return true if within range
	 */
	public boolean isWithin(int zoom) {
		return zoom >= minZoom && (maxZoom == null || zoom <= maxZoom);
	}

	/**
	 * Get the minimum zoom level for drawing grid lines
	 * 
	 * @return minimum zoom level
	 */
	public int getLinesMinZoom() {
		return linesMinZoom != null ? linesMinZoom : getMinZoom();
	}

	/**
	 * Has a minimum zoom level override for drawing grid lines
	 * 
	 * @return true if has a minimum, false if not overridden
	 */
	public boolean hasLinesMinZoom() {
		return linesMinZoom != null;
	}

	/**
	 * Set the minimum level override for drawing grid lines
	 * 
	 * @param linesMinZoom
	 *            minimum zoom level or null to remove
	 */
	public void setLinesMinZoom(Integer linesMinZoom) {
		this.linesMinZoom = linesMinZoom;
	}

	/**
	 * Get the maximum zoom level for drawing grid lines
	 * 
	 * @return maximum zoom level
	 */
	public Integer getLinesMaxZoom() {
		return linesMaxZoom != null ? linesMaxZoom : getMaxZoom();
	}

	/**
	 * Has a maximum zoom level override for drawing grid lines
	 * 
	 * @return true if has a maximum, false if not overridden
	 */
	public boolean hasLinesMaxZoom() {
		return linesMaxZoom != null;
	}

	/**
	 * Set the maximum level override for drawing grid lines
	 * 
	 * @param linesMaxZoom
	 *            maximum zoom level or null to remove
	 */
	public void setLinesMaxZoom(Integer linesMaxZoom) {
		this.linesMaxZoom = linesMaxZoom;
	}

	/**
	 * Is the zoom level within the grid lines zoom range
	 * 
	 * @param zoom
	 *            zoom level
	 * @return true if within range
	 */
	public boolean isLinesWithin(int zoom) {
		return (linesMinZoom == null || zoom >= linesMinZoom)
				&& (linesMaxZoom == null || zoom <= linesMaxZoom);
	}

	/**
	 * Get the grid line style
	 * 
	 * @return grid line style
	 */
	public GridStyle getStyle() {
		return style;
	}

	/**
	 * Set the grid line style
	 * 
	 * @param style
	 *            grid line style
	 */
	public void setStyle(GridStyle style) {
		this.style = style != null ? style : new GridStyle();
	}

	/**
	 * Get the grid line color
	 * 
	 * @return grid line color
	 */
	public Color getColor() {
		return getStyle().getColor();
	}

	/**
	 * Set the grid line color
	 * 
	 * @param color
	 *            grid line color
	 */
	public void setColor(Color color) {
		getStyle().setColor(color);
	}

	/**
	 * Get the grid line width
	 * 
	 * @return grid line width
	 */
	public double getWidth() {
		return getStyle().getWidth();
	}

	/**
	 * Set the grid line width
	 * 
	 * @param width
	 *            grid line width
	 */
	public void setWidth(double width) {
		getStyle().setWidth(width);
	}

	/**
	 * Get the grid labeler
	 * 
	 * @return grid labeler
	 */
	public Labeler getLabeler() {
		return labeler;
	}

	/**
	 * Has a grid labeler
	 * 
	 * @return true if has a grid labeler
	 */
	public boolean hasLabeler() {
		return labeler != null;
	}

	/**
	 * Set the grid labeler
	 * 
	 * @param labeler
	 *            grid labeler
	 */
	public void setLabeler(Labeler labeler) {
		this.labeler = labeler;
	}

	/**
	 * Is labeler zoom level within the grid zoom range
	 * 
	 * @param zoom
	 *            zoom level
	 * @return true if within range
	 */
	public boolean isLabelerWithin(int zoom) {
		return hasLabeler() && labeler.isEnabled() && labeler.isWithin(zoom);
	}

	/**
	 * Get the label grid edge buffer
	 * 
	 * @return label buffer (greater than or equal to 0.0 and less than 0.5)
	 */
	public double getLabelBuffer() {
		return hasLabeler() ? labeler.getBuffer() : 0.0;
	}

}
