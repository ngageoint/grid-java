package mil.nga.grid;

import mil.nga.sf.util.GeometryConstants;

/**
 * Grid Constants
 *
 * @author osbornb
 */
public class GridConstants {

	/**
	 * Minimum longitude
	 */
	public static final double MIN_LON = -GeometryConstants.WGS84_HALF_WORLD_LON_WIDTH;

	/**
	 * Maximum longitude
	 */
	public static final double MAX_LON = GeometryConstants.WGS84_HALF_WORLD_LON_WIDTH;

	/**
	 * Minimum latitude
	 */
	public static final double MIN_LAT = -GeometryConstants.WGS84_HALF_WORLD_LAT_HEIGHT;

	/**
	 * Maximum latitude
	 */
	public static final double MAX_LAT = GeometryConstants.WGS84_HALF_WORLD_LAT_HEIGHT;

	/**
	 * Omitted band letter 'I'
	 */
	public static final char BAND_LETTER_OMIT_I = 'I';

	/**
	 * Omitted band letter 'O'
	 */
	public static final char BAND_LETTER_OMIT_O = 'O';

	/**
	 * Max map zoom level
	 */
	public static final int MAX_MAP_ZOOM_LEVEL = 21;

	/**
	 * North single character as a string
	 */
	public static final String NORTH_CHAR = "N";

	/**
	 * South single character as a string
	 */
	public static final String SOUTH_CHAR = "S";

	/**
	 * West single character as a string
	 */
	public static final String WEST_CHAR = "W";

	/**
	 * East single character as a string
	 */
	public static final String EAST_CHAR = "E";

}
