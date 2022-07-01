package mil.nga.grid;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Zoom Level Matching Grids
 * 
 * @author osbornb
 * @param <T>
 *            grid type
 */
public class BaseZoomGrids<T extends BaseGrid> implements Iterable<T> {

	/**
	 * Zoom level
	 */
	private final int zoom;

	/**
	 * Grids
	 */
	protected final TreeSet<T> grids = new TreeSet<>();

	/**
	 * Constructor
	 * 
	 * @param zoom
	 *            zoom level
	 */
	public BaseZoomGrids(int zoom) {
		this.zoom = zoom;
	}

	/**
	 * Get the zoom level
	 * 
	 * @return zoom level
	 */
	public int getZoom() {
		return zoom;
	}

	/**
	 * Get the grids within the zoom level
	 * 
	 * @return grids
	 */
	public TreeSet<T> getGrids() {
		return grids;
	}

	/**
	 * Get the number of grids
	 * 
	 * @return number of grids
	 */
	public int numGrids() {
		return grids.size();
	}

	/**
	 * Determine if the zoom level has grids
	 * 
	 * @return true if has grids
	 */
	public boolean hasGrids() {
		return !grids.isEmpty();
	}

	/**
	 * Add a grid
	 * 
	 * @param grid
	 *            grid
	 * @return true if added
	 */
	public boolean addGrid(T grid) {
		return grids.add(grid);
	}

	/**
	 * Remove the grid
	 * 
	 * @param grid
	 *            grid
	 * @return true if removed
	 */
	public boolean removeGrid(T grid) {
		return grids.remove(grid);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return grids.iterator();
	}

}
