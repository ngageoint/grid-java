package mil.nga.grid;

import mil.nga.grid.features.Bounds;
import mil.nga.grid.features.Point;

/**
 * Grid Label
 * 
 * @author osbornb
 */
public class Label {

	/**
	 * Name
	 */
	private String name;

	/**
	 * Center point
	 */
	private Point center;

	/**
	 * Bounds
	 */
	private Bounds bounds;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            name
	 * @param center
	 *            center point
	 * @param bounds
	 *            bounds
	 */
	public Label(String name, Point center, Bounds bounds) {
		this.name = name;
		this.center = center;
		this.bounds = bounds;
	}

	/**
	 * Get the name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the center point
	 * 
	 * @return center point
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * Set the center point
	 * 
	 * @param center
	 *            center point
	 */
	public void setCenter(Point center) {
		this.center = center;
	}

	/**
	 * Get the bounds
	 * 
	 * @return bounds
	 */
	public Bounds getBounds() {
		return bounds;
	}

	/**
	 * Set the bounds
	 * 
	 * @param bounds
	 *            bounds
	 */
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

}
