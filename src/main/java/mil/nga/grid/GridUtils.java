package mil.nga.grid;

import mil.nga.grid.features.Bounds;
import mil.nga.grid.features.Line;
import mil.nga.grid.features.Point;
import mil.nga.grid.features.Unit;
import mil.nga.grid.tile.Pixel;
import mil.nga.sf.util.GeometryConstants;
import mil.nga.sf.util.GeometryUtils;

/**
 * Grid utilities
 * 
 * @author osbornb
 */
public class GridUtils {

	/**
	 * Get the pixel where the point fits into the bounds
	 * 
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @param bounds
	 *            bounds
	 * @param point
	 *            point
	 * @return pixel
	 */
	public static Pixel getPixel(int width, int height, Bounds bounds,
			Point point) {

		point = point.toMeters();
		bounds = bounds.toMeters();

		float x = getXPixel(width, bounds, point.getLongitude());
		float y = getYPixel(height, bounds, point.getLatitude());
		return new Pixel(x, y);
	}

	/**
	 * Get the X pixel for where the longitude in meters fits into the bounds
	 *
	 * @param width
	 *            width
	 * @param bounds
	 *            bounds
	 * @param longitude
	 *            longitude in meters
	 * @return x pixel
	 */
	public static float getXPixel(int width, Bounds bounds, double longitude) {

		bounds = bounds.toMeters();

		double boxWidth = bounds.getMaxLongitude() - bounds.getMinLongitude();
		double offset = longitude - bounds.getMinLongitude();
		double percentage = offset / boxWidth;
		float pixel = (float) (percentage * width);

		return pixel;
	}

	/**
	 * Get the Y pixel for where the latitude in meters fits into the bounds
	 *
	 * @param height
	 *            height
	 * @param bounds
	 *            bounds
	 * @param latitude
	 *            latitude
	 * @return y pixel
	 */
	public static float getYPixel(int height, Bounds bounds, double latitude) {

		bounds = bounds.toMeters();

		double boxHeight = bounds.getMaxLatitude() - bounds.getMinLatitude();
		double offset = bounds.getMaxLatitude() - latitude;
		double percentage = offset / boxHeight;
		float pixel = (float) (percentage * height);

		return pixel;
	}

	/**
	 * Get the tile bounds from the XYZ tile coordinates and zoom level
	 *
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param zoom
	 *            zoom level
	 * @return bounds
	 */
	public static Bounds getBounds(int x, int y, int zoom) {

		int tilesPerSide = tilesPerSide(zoom);
		double tileSize = tileSize(tilesPerSide);

		double minLon = (-1 * GeometryConstants.WEB_MERCATOR_HALF_WORLD_WIDTH)
				+ (x * tileSize);
		double minLat = GeometryConstants.WEB_MERCATOR_HALF_WORLD_WIDTH
				- ((y + 1) * tileSize);
		double maxLon = (-1 * GeometryConstants.WEB_MERCATOR_HALF_WORLD_WIDTH)
				+ ((x + 1) * tileSize);
		double maxLat = GeometryConstants.WEB_MERCATOR_HALF_WORLD_WIDTH
				- (y * tileSize);

		return Bounds.meters(minLon, minLat, maxLon, maxLat);
	}

	/**
	 * Get the tiles per side, width and height, at the zoom level
	 *
	 * @param zoom
	 *            zoom level
	 * @return tiles per side
	 */
	public static int tilesPerSide(int zoom) {
		return (int) Math.pow(2, zoom);
	}

	/**
	 * Get the tile size in meters
	 *
	 * @param tilesPerSide
	 *            tiles per side
	 * @return tile size
	 */
	public static double tileSize(int tilesPerSide) {
		return (2 * GeometryConstants.WEB_MERCATOR_HALF_WORLD_WIDTH)
				/ tilesPerSide;
	}

	/**
	 * Get the zoom level of the bounds using the shortest bounds side length
	 * 
	 * @param bounds
	 *            bounds
	 * @return zoom level
	 */
	public static double getZoomLevel(Bounds bounds) {
		bounds = bounds.toMeters();
		double tileSize = Math.min(bounds.getWidth(), bounds.getHeight());
		double tilesPerSide = 2
				* GeometryConstants.WEB_MERCATOR_HALF_WORLD_WIDTH / tileSize;
		return Math.log(tilesPerSide) / Math.log(2);
	}

	/**
	 * Convert a coordinate from a unit to another unit
	 * 
	 * @param fromUnit
	 *            unit of provided coordinate
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @param toUnit
	 *            desired unit
	 * @return point in unit
	 */
	public static Point toUnit(Unit fromUnit, double longitude, double latitude,
			Unit toUnit) {
		Point point = null;
		if (fromUnit == toUnit) {
			point = Point.point(longitude, latitude, toUnit);
		} else {
			point = toUnit(longitude, latitude, toUnit);
		}
		return point;
	}

	/**
	 * Convert a coordinate to the unit, assumes the coordinate is in the
	 * opposite unit
	 * 
	 * @param longitude
	 *            longitude
	 * @param latitude
	 *            latitude
	 * @param unit
	 *            desired unit
	 * @return point in unit
	 */
	public static Point toUnit(double longitude, double latitude, Unit unit) {
		mil.nga.sf.Point point = null;
		switch (unit) {
		case DEGREE:
			point = GeometryUtils.metersToDegrees(longitude, latitude);
			break;
		case METER:
			point = GeometryUtils.degreesToMeters(longitude, latitude);
			break;
		default:
			throw new IllegalArgumentException("Unsupported unit: " + unit);
		}
		return Point.point(point, unit);
	}

	/**
	 * Is the band letter an omitted letter
	 * {@link GridConstants#BAND_LETTER_OMIT_I} or
	 * {@link GridConstants#BAND_LETTER_OMIT_O}
	 * 
	 * @param letter
	 *            band letter
	 * @return true if omitted
	 */
	public static boolean isOmittedBandLetter(char letter) {
		return letter == GridConstants.BAND_LETTER_OMIT_I
				|| letter == GridConstants.BAND_LETTER_OMIT_O;
	}

	/**
	 * Get the precision value before the value
	 * 
	 * @param value
	 *            value
	 * @param precision
	 *            precision
	 * @return precision value
	 */
	public static double precisionBefore(double value, double precision) {
		double before = 0.0;
		if (Math.abs(value) >= precision) {
			before = value - ((value % precision + precision) % precision);
		} else if (value < 0.0) {
			before = -precision;
		}
		return before;
	}

	/**
	 * Get the precision value after the value
	 * 
	 * @param value
	 *            value
	 * @param precision
	 *            precision
	 * @return precision value
	 */
	public static double precisionAfter(double value, double precision) {
		return precisionBefore(value + precision, precision);
	}

	/**
	 * Get the point intersection between two lines
	 * 
	 * @param line1
	 *            first line
	 * @param line2
	 *            second line
	 * @return intersection point or null if no intersection
	 */
	public static Point intersection(Line line1, Line line2) {
		return intersection(line1.getPoint1(), line1.getPoint2(),
				line2.getPoint1(), line2.getPoint2());
	}

	/**
	 * Get the point intersection between end points of two lines
	 * 
	 * @param line1Point1
	 *            first point of the first line
	 * @param line1Point2
	 *            second point of the first line
	 * @param line2Point1
	 *            first point of the second line
	 * @param line2Point2
	 *            second point of the second line
	 * @return intersection point or null if no intersection
	 */
	public static Point intersection(Point line1Point1, Point line1Point2,
			Point line2Point1, Point line2Point2) {

		Point intersection = null;

		mil.nga.sf.Point point = GeometryUtils.intersection(
				line1Point1.toMeters(), line1Point2.toMeters(),
				line2Point1.toMeters(), line2Point2.toMeters());

		if (point != null) {
			intersection = Point.point(point, Unit.METER)
					.toUnit(line1Point1.getUnit());
		}

		return intersection;
	}

}
