package mil.nga.grid;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;

import mil.nga.grid.color.Color;
import mil.nga.grid.property.GridProperties;
import mil.nga.grid.property.PropertyConstants;

/**
 * Grids
 * 
 * @author osbornb
 * @param <TGrid>
 *            grid type
 * @param <TZoomGrids>
 *            zoom grids type
 */
public abstract class BaseGrids<TGrid extends BaseGrid, TZoomGrids extends BaseZoomGrids<TGrid>> {

	/**
	 * Grid properties
	 */
	protected final GridProperties properties;

	/**
	 * Map between zoom levels and grids
	 */
	private TreeMap<Integer, TZoomGrids> zoomGrids = new TreeMap<>();

	/**
	 * Constructor
	 * 
	 * @param properties
	 *            grid properties
	 */
	public BaseGrids(GridProperties properties) {
		this.properties = properties;
	}

	/**
	 * Get the default grid line width
	 * 
	 * @return width
	 */
	public abstract double getDefaultWidth();

	/**
	 * Get the grids
	 * 
	 * @return grids
	 */
	public abstract Collection<TGrid> grids();

	/**
	 * Create a new zoom grids
	 * 
	 * @param zoom
	 *            zoom level
	 * @return zoom grids
	 */
	protected abstract TZoomGrids newZoomGrids(int zoom);

	/**
	 * Load the grid
	 * 
	 * @param grid
	 *            name
	 * @param gridKey
	 *            grid name key
	 * @param enabled
	 *            enable created grids
	 * @param labeler
	 *            grid labeler
	 */
	protected void loadGrid(TGrid grid, String gridKey, Boolean enabled,
			Labeler labeler) {

		if (enabled == null) {
			enabled = properties.getBooleanProperty(false,
					PropertyConstants.GRIDS, gridKey,
					PropertyConstants.ENABLED);
			if (enabled == null) {
				enabled = true;
			}
		}
		grid.setEnabled(enabled);

		Integer minZoom = properties.getIntegerProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.MIN_ZOOM);
		if (minZoom == null) {
			minZoom = 0;
		}
		grid.setMinZoom(minZoom);

		Integer maxZoom = properties.getIntegerProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.MAX_ZOOM);
		grid.setMaxZoom(maxZoom);

		Integer linesMinZoom = properties.getIntegerProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.LINES,
				PropertyConstants.MIN_ZOOM);
		grid.setLinesMinZoom(linesMinZoom);

		Integer linesMaxZoom = properties.getIntegerProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.LINES,
				PropertyConstants.MAX_ZOOM);
		grid.setLinesMaxZoom(linesMaxZoom);

		String colorProperty = properties.getProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.COLOR);
		Color color = colorProperty != null ? Color.color(colorProperty)
				: Color.black();
		grid.setColor(color);

		Double width = properties.getDoubleProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.WIDTH);
		if (width == null) {
			width = getDefaultWidth();
		}
		grid.setWidth(width);

		if (labeler != null) {
			loadLabeler(labeler, gridKey);
		}
		grid.setLabeler(labeler);

	}

	/**
	 * Load the labeler
	 * 
	 * @param labeler
	 *            labeler
	 * @param gridKey
	 *            grid name key
	 */
	private void loadLabeler(Labeler labeler, String gridKey) {

		Boolean enabled = properties.getBooleanProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.LABELER,
				PropertyConstants.ENABLED);
		labeler.setEnabled(enabled != null && enabled);

		Integer minZoom = properties.getIntegerProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.LABELER,
				PropertyConstants.MIN_ZOOM);
		if (minZoom != null) {
			labeler.setMinZoom(minZoom);
		}

		Integer maxZoom = properties.getIntegerProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.LABELER,
				PropertyConstants.MAX_ZOOM);
		if (maxZoom != null) {
			labeler.setMaxZoom(maxZoom);
		}

		String color = properties.getProperty(false, PropertyConstants.GRIDS,
				gridKey, PropertyConstants.LABELER, PropertyConstants.COLOR);
		if (color != null) {
			labeler.setColor(Color.color(color));
		}

		Double textSize = properties.getDoubleProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.LABELER,
				PropertyConstants.TEXT_SIZE);
		if (textSize != null) {
			labeler.setTextSize(textSize);
		}

		Double buffer = properties.getDoubleProperty(false,
				PropertyConstants.GRIDS, gridKey, PropertyConstants.LABELER,
				PropertyConstants.BUFFER);
		if (buffer != null) {
			labeler.setBuffer(buffer);
		}

	}

	/**
	 * Load the grid style color
	 * 
	 * @param gridKey
	 *            grid name key
	 * @param gridKey2
	 *            second grid name key
	 * @return color
	 */
	protected Color loadGridStyleColor(String gridKey, String gridKey2) {
		String colorProperty = properties.getProperty(false,
				PropertyConstants.GRIDS, gridKey, gridKey2,
				PropertyConstants.COLOR);
		Color color = null;
		if (colorProperty != null) {
			color = Color.color(colorProperty);
		}
		return color;
	}

	/**
	 * Load the grid style width
	 * 
	 * @param gridKey
	 *            grid name key
	 * @param gridKey2
	 *            second grid name key
	 * @return width
	 */
	protected Double loadGridStyleWidth(String gridKey, String gridKey2) {
		return properties.getDoubleProperty(false, PropertyConstants.GRIDS,
				gridKey, gridKey2, PropertyConstants.WIDTH);
	}

	/**
	 * Get a combined grid style from the provided color, width, and grid
	 * 
	 * @param color
	 *            color
	 * @param width
	 *            width
	 * @param grid
	 *            grid
	 * @return grid style
	 */
	protected GridStyle getGridStyle(Color color, Double width, TGrid grid) {

		if (color == null) {
			color = grid.getColor();
		}

		if (width == null || width == 0) {
			width = grid.getWidth();
		}

		return GridStyle.style(color, width);
	}

	/**
	 * Create the zoom level grids
	 */
	protected void createZoomGrids() {
		for (int zoom = 0; zoom <= GridConstants.MAX_MAP_ZOOM_LEVEL; zoom++) {
			createZoomGrids(zoom);
		}
	}

	/**
	 * Get the grids for the zoom level
	 * 
	 * @param zoom
	 *            zoom level
	 * @return grids
	 */
	public TZoomGrids getGrids(int zoom) {
		TZoomGrids grids = zoomGrids.get(zoom);
		if (grids == null) {
			grids = createZoomGrids(zoom);
		}
		return grids;
	}

	/**
	 * Create grids for the zoom level
	 * 
	 * @param zoom
	 *            zoom level
	 * @return grids
	 */
	private TZoomGrids createZoomGrids(int zoom) {
		TZoomGrids zoomLevelGrids = newZoomGrids(zoom);
		for (TGrid grid : grids()) {
			if (grid.isEnabled() && grid.isWithin(zoom)) {
				zoomLevelGrids.addGrid(grid);
			}
		}
		zoomGrids.put(zoom, zoomLevelGrids);
		return zoomLevelGrids;
	}

	/**
	 * Enable grids
	 * 
	 * @param grids
	 *            grids
	 */
	public void enableGrids(@SuppressWarnings("unchecked") TGrid... grids) {
		enableGrids(Arrays.asList(grids));
	}

	/**
	 * Enable grids
	 * 
	 * @param grids
	 *            grids
	 */
	public void enableGrids(Collection<TGrid> grids) {
		for (TGrid grid : grids) {
			enable(grid);
		}
	}

	/**
	 * Disable grids
	 * 
	 * @param grids
	 *            grids
	 */
	public void disableGrids(@SuppressWarnings("unchecked") TGrid... grids) {
		disableGrids(Arrays.asList(grids));
	}

	/**
	 * Disable grids
	 * 
	 * @param grids
	 *            grids
	 */
	public void disableGrids(Collection<TGrid> grids) {
		for (TGrid grid : grids) {
			disable(grid);
		}
	}

	/**
	 * Enable the grid
	 * 
	 * @param grid
	 *            grid
	 */
	public void enable(TGrid grid) {

		if (!grid.isEnabled()) {

			grid.setEnabled(true);

			int minZoom = grid.getMinZoom();
			Integer maxZoom = grid.getMaxZoom();
			if (maxZoom == null) {
				maxZoom = zoomGrids.lastKey();
			}

			for (int zoom = minZoom; zoom <= maxZoom; zoom++) {
				addGrid(grid, zoom);
			}

		}

	}

	/**
	 * Disable the grid
	 * 
	 * @param grid
	 *            grid
	 */
	public void disable(TGrid grid) {

		if (grid.isEnabled()) {

			grid.setEnabled(false);

			int minZoom = grid.getMinZoom();
			Integer maxZoom = grid.getMaxZoom();
			if (maxZoom == null) {
				maxZoom = zoomGrids.lastKey();
			}

			for (int zoom = minZoom; zoom <= maxZoom; zoom++) {
				removeGrid(grid, zoom);
			}

		}

	}

	/**
	 * Set the grid minimum zoom
	 * 
	 * @param grid
	 *            grid
	 * @param minZoom
	 *            minimum zoom
	 */
	public void setMinZoom(TGrid grid, int minZoom) {
		Integer maxZoom = grid.getMaxZoom();
		if (maxZoom != null && maxZoom < minZoom) {
			maxZoom = minZoom;
		}
		setZoomRange(grid, minZoom, maxZoom);
	}

	/**
	 * Set the grid maximum zoom
	 * 
	 * @param grid
	 *            grid
	 * @param maxZoom
	 *            maximum zoom
	 */
	public void setMaxZoom(TGrid grid, Integer maxZoom) {
		int minZoom = grid.getMinZoom();
		if (maxZoom != null && minZoom > maxZoom) {
			minZoom = maxZoom;
		}
		setZoomRange(grid, minZoom, maxZoom);
	}

	/**
	 * Set the grid zoom range
	 * 
	 * @param grid
	 *            grid
	 * @param minZoom
	 *            minimum zoom
	 * @param maxZoom
	 *            maximum zoom
	 */
	public void setZoomRange(TGrid grid, int minZoom, Integer maxZoom) {

		if (maxZoom != null && maxZoom < minZoom) {
			throw new IllegalArgumentException("Min zoom '" + minZoom
					+ "' can not be larger than max zoom '" + maxZoom + "'");
		}

		// All grids zoom range
		final int allGridsMin = zoomGrids.firstKey();
		final int allGridsMax = zoomGrids.lastKey();

		// Existing grid zoom range
		int gridMinZoom = grid.getMinZoom();
		Integer gridMaxZoom = grid.getMaxZoom();
		if (gridMaxZoom == null) {
			gridMaxZoom = allGridsMax;
		} else {
			gridMaxZoom = Math.min(gridMaxZoom, allGridsMax);
		}

		grid.setMinZoom(minZoom);
		grid.setMaxZoom(maxZoom);

		minZoom = Math.max(minZoom, allGridsMin);
		if (maxZoom == null) {
			maxZoom = allGridsMax;
		} else {
			maxZoom = Math.min(maxZoom, allGridsMax);
		}

		int minOverlap = Math.max(minZoom, gridMinZoom);
		int maxOverlap = Math.min(maxZoom, gridMaxZoom);

		boolean overlaps = minOverlap <= maxOverlap;

		if (overlaps) {

			int min = Math.min(minZoom, gridMinZoom);
			int max = Math.max(maxZoom, gridMaxZoom);

			for (int zoom = min; zoom <= max; zoom++) {

				if (zoom < minOverlap || zoom > maxOverlap) {

					if (zoom >= minZoom && zoom <= maxZoom) {
						addGrid(grid, zoom);
					} else {
						removeGrid(grid, zoom);
					}

				}

			}
		} else {

			for (int zoom = gridMinZoom; zoom <= gridMaxZoom; zoom++) {
				removeGrid(grid, zoom);
			}

			for (int zoom = minZoom; zoom <= maxZoom; zoom++) {
				addGrid(grid, zoom);
			}

		}

	}

	/**
	 * Add a grid to the zoom level
	 * 
	 * @param grid
	 *            grid
	 * @param zoom
	 *            zoom level
	 */
	private void addGrid(TGrid grid, int zoom) {
		TZoomGrids grids = zoomGrids.get(zoom);
		if (grids != null) {
			grids.addGrid(grid);
		}
	}

	/**
	 * Remove a grid from the zoom level
	 * 
	 * @param grid
	 *            grid
	 * @param zoom
	 *            zoom level
	 */
	private void removeGrid(TGrid grid, int zoom) {
		TZoomGrids grids = zoomGrids.get(zoom);
		if (grids != null) {
			grids.removeGrid(grid);
		}
	}

	/**
	 * Enable all grid labelers
	 */
	public void enableAllLabelers() {
		for (TGrid grid : grids()) {
			Labeler labeler = grid.getLabeler();
			if (labeler != null) {
				labeler.setEnabled(true);
			}
		}
	}

	/**
	 * Set all label grid edge buffers
	 * 
	 * @param buffer
	 *            label buffer (greater than or equal to 0.0 and less than 0.5)
	 */
	public void setAllLabelBuffers(double buffer) {
		for (TGrid grid : grids()) {
			Labeler labeler = grid.getLabeler();
			if (labeler != null) {
				labeler.setBuffer(buffer);
			}
		}
	}

}
