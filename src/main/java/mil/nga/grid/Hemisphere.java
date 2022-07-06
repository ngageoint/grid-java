package mil.nga.grid;

import mil.nga.grid.features.Point;

/**
 * Hemisphere enumeration
 * 
 * @author osbornb
 */
public enum Hemisphere {

	/**
	 * Northern hemisphere
	 */
	NORTH,

	/**
	 * Southern hemisphere
	 */
	SOUTH;

	/**
	 * Get the hemisphere for the latitude
	 * 
	 * @param latitude
	 *            latitude
	 * @return hemisphere
	 */
	public static Hemisphere fromLatitude(double latitude) {
		return latitude >= 0 ? Hemisphere.NORTH : Hemisphere.SOUTH;
	}

	/**
	 * Get the hemisphere for the point
	 * 
	 * @param point
	 *            point
	 * @return hemisphere
	 */
	public static Hemisphere from(Point point) {
		return fromLatitude(point.getLatitude());
	}

}