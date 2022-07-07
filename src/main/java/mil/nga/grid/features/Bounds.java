package mil.nga.grid.features;

import java.util.ArrayList;
import java.util.List;

import mil.nga.grid.GridUtils;
import mil.nga.grid.tile.GridTile;
import mil.nga.grid.tile.Pixel;
import mil.nga.grid.tile.PixelRange;
import mil.nga.sf.GeometryEnvelope;

/**
 * Grid Bounds
 * 
 * @author osbornb
 */
public class Bounds extends GeometryEnvelope {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Unit
	 */
	private Unit unit;

	/**
	 * Create bounds
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 * @param unit
	 *            unit
	 * @return bounds
	 */
	public static Bounds bounds(double minLongitude, double minLatitude,
			double maxLongitude, double maxLatitude, Unit unit) {
		return new Bounds(minLongitude, minLatitude, maxLongitude, maxLatitude,
				unit);
	}

	/**
	 * Create bounds in degrees
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 * @return bounds
	 */
	public static Bounds degrees(double minLongitude, double minLatitude,
			double maxLongitude, double maxLatitude) {
		return bounds(minLongitude, minLatitude, maxLongitude, maxLatitude,
				Unit.DEGREE);
	}

	/**
	 * Create bounds in meters
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 * @return bounds
	 */
	public static Bounds meters(double minLongitude, double minLatitude,
			double maxLongitude, double maxLatitude) {
		return bounds(minLongitude, minLatitude, maxLongitude, maxLatitude,
				Unit.METER);
	}

	/**
	 * Create bounds
	 * 
	 * @param southwest
	 *            southwest corner
	 * @param northeast
	 *            northeast corner
	 * @return bounds
	 */
	public static Bounds bounds(Point southwest, Point northeast) {
		return new Bounds(southwest, northeast);
	}

	/**
	 * Copy bounds
	 * 
	 * @param bounds
	 *            bounds to copy
	 * @return bounds
	 */
	public static Bounds bounds(Bounds bounds) {
		return new Bounds(bounds);
	}

	/**
	 * Create bounds
	 * 
	 * @param envelope
	 *            geometry envelope
	 * @param unit
	 *            unit
	 * @return bounds
	 */
	public static Bounds bounds(GeometryEnvelope envelope, Unit unit) {
		return new Bounds(envelope, unit);
	}

	/**
	 * Constructor
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 */
	public Bounds(double minLongitude, double minLatitude, double maxLongitude,
			double maxLatitude) {
		this(minLongitude, minLatitude, maxLongitude, maxLatitude, Unit.DEGREE);
	}

	/**
	 * Constructor
	 * 
	 * @param minLongitude
	 *            min longitude
	 * @param minLatitude
	 *            min latitude
	 * @param maxLongitude
	 *            max longitude
	 * @param maxLatitude
	 *            max latitude
	 * @param unit
	 *            unit
	 */
	public Bounds(double minLongitude, double minLatitude, double maxLongitude,
			double maxLatitude, Unit unit) {
		super(minLongitude, minLatitude, maxLongitude, maxLatitude);
		this.unit = unit;
	}

	/**
	 * Constructor
	 * 
	 * @param southwest
	 *            southwest corner
	 * @param northeast
	 *            northeast corner
	 */
	public Bounds(Point southwest, Point northeast) {
		this(southwest.getLongitude(), southwest.getLatitude(),
				northeast.getLongitude(), northeast.getLatitude(),
				southwest.getUnit());

		if (!isUnit(northeast.getUnit())) {
			throw new IllegalArgumentException(
					"Points are in different units. southwest: " + unit
							+ ", northeast: " + northeast.getUnit());
		}
	}

	/**
	 * Copy constructor
	 * 
	 * @param bounds
	 *            bounds to copy
	 */
	public Bounds(Bounds bounds) {
		this(bounds, bounds.unit);
	}

	/**
	 * Constructor
	 * 
	 * @param envelope
	 *            geometry envelope
	 * @param unit
	 *            unit
	 */
	public Bounds(GeometryEnvelope envelope, Unit unit) {
		super(envelope);
		this.unit = unit;
	}

	/**
	 * Get the min longitude
	 * 
	 * @return min longitude
	 */
	public double getMinLongitude() {
		return getMinX();
	}

	/**
	 * Set the min longitude
	 * 
	 * @param minLongitude
	 *            min longitude
	 */
	public void setMinLongitude(double minLongitude) {
		setMinX(minLongitude);
	}

	/**
	 * Get the min latitude
	 * 
	 * @return min latitude
	 */
	public double getMinLatitude() {
		return getMinY();
	}

	/**
	 * Set the min latitude
	 * 
	 * @param minLatitude
	 *            min latitude
	 */
	public void setMinLatitude(double minLatitude) {
		setMinY(minLatitude);
	}

	/**
	 * Get the max longitude
	 * 
	 * @return max longitude
	 */
	public double getMaxLongitude() {
		return getMaxX();
	}

	/**
	 * Set the max longitude
	 * 
	 * @param maxLongitude
	 *            max longitude
	 */
	public void setMaxLongitude(double maxLongitude) {
		setMaxX(maxLongitude);
	}

	/**
	 * Get the max latitude
	 * 
	 * @return max latitude
	 */
	public double getMaxLatitude() {
		return getMaxY();
	}

	/**
	 * Set the max latitude
	 * 
	 * @param maxLatitude
	 *            max latitude
	 */
	public void setMaxLatitude(double maxLatitude) {
		setMaxY(maxLatitude);
	}

	/**
	 * Get the western longitude
	 * 
	 * @return western longitude
	 */
	public double getWest() {
		return getMinLongitude();
	}

	/**
	 * Set the western longitude
	 * 
	 * @param west
	 *            western longitude
	 */
	public void setWest(double west) {
		setMinLongitude(west);
	}

	/**
	 * Get the southern latitude
	 * 
	 * @return southern latitude
	 */
	public double getSouth() {
		return getMinLatitude();
	}

	/**
	 * Set the southern latitude
	 * 
	 * @param south
	 *            southern latitude
	 */
	public void setSouth(double south) {
		setMinLatitude(south);
	}

	/**
	 * Get the eastern longitude
	 * 
	 * @return eastern longitude
	 */
	public double getEast() {
		return getMaxLongitude();
	}

	/**
	 * Set the eastern longitude
	 * 
	 * @param east
	 *            eastern longitude
	 */
	public void setEast(double east) {
		setMaxLongitude(east);
	}

	/**
	 * Get the northern latitude
	 * 
	 * @return northern latitude
	 */
	public double getNorth() {
		return getMaxLatitude();
	}

	/**
	 * Set the northern latitude
	 * 
	 * @param north
	 *            northern latitude
	 */
	public void setNorth(double north) {
		setMaxLatitude(north);
	}

	/**
	 * Get the unit
	 * 
	 * @return unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * Set the unit
	 * 
	 * @param unit
	 *            unit
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * Is in the provided unit type
	 * 
	 * @param unit
	 *            unit
	 * @return true if in the unit
	 */
	public boolean isUnit(Unit unit) {
		return this.unit == unit;
	}

	/**
	 * Are bounds in degrees
	 * 
	 * @return true if degrees
	 */
	public boolean isDegrees() {
		return isUnit(Unit.DEGREE);
	}

	/**
	 * Are bounds in meters
	 * 
	 * @return true if meters
	 */
	public boolean isMeters() {
		return isUnit(Unit.METER);
	}

	/**
	 * Convert to the unit
	 * 
	 * @param unit
	 *            unit
	 * @return bounds in units, same bounds if equal units
	 */
	public Bounds toUnit(Unit unit) {
		Bounds bounds = null;
		if (isUnit(unit)) {
			bounds = this;
		} else {
			Point southwest = getSouthwest().toUnit(unit);
			Point northeast = getNortheast().toUnit(unit);
			bounds = new Bounds(southwest, northeast);
		}
		return bounds;
	}

	/**
	 * Convert to degrees
	 * 
	 * @return bounds in degrees, same bounds if already in degrees
	 */
	public Bounds toDegrees() {
		return toUnit(Unit.DEGREE);
	}

	/**
	 * Convert to meters
	 * 
	 * @return bounds in meters, same bounds if already in meters
	 */
	public Bounds toMeters() {
		return toUnit(Unit.METER);
	}

	/**
	 * Get the centroid longitude
	 * 
	 * @return centroid longitude
	 */
	public double getCentroidLongitude() {
		return getMidX();
	}

	/**
	 * Get the centroid latitude
	 * 
	 * @return centroid latitude
	 */
	public double getCentroidLatitude() {
		double centerLatitude;
		if (unit == Unit.DEGREE) {
			centerLatitude = getCentroid().getLatitude();
		} else {
			centerLatitude = getMidY();
		}
		return centerLatitude;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Point getCentroid() {
		Point point = null;
		if (unit == Unit.DEGREE) {
			point = toMeters().getCentroid().toDegrees();
		} else {
			point = Point.point(super.getCentroid(), unit);
		}
		return point;
	}

	/**
	 * Get the width
	 * 
	 * @return width
	 */
	public double getWidth() {
		return getXRange();
	}

	/**
	 * Get the height
	 * 
	 * @return height
	 */
	public double getHeight() {
		return getYRange();
	}

	/**
	 * Get the southwest coordinate
	 * 
	 * @return southwest coordinate
	 */
	public Point getSouthwest() {
		return Point.point(getMinLongitude(), getMinLatitude(), unit);
	}

	/**
	 * Get the northwest coordinate
	 * 
	 * @return northwest coordinate
	 */
	public Point getNorthwest() {
		return Point.point(getMinLongitude(), getMaxLatitude(), unit);
	}

	/**
	 * Get the southeast coordinate
	 * 
	 * @return southeast coordinate
	 */
	public Point getSoutheast() {
		return Point.point(getMaxLongitude(), getMinLatitude(), unit);
	}

	/**
	 * Get the northeast coordinate
	 * 
	 * @return northeast coordinate
	 */
	public Point getNortheast() {
		return Point.point(getMaxLongitude(), getMaxLatitude(), unit);
	}

	/**
	 * Create a new bounds as the overlapping between this bounds and the
	 * provided
	 * 
	 * @param bounds
	 *            bounds
	 * @return overlap bounds
	 */
	public Bounds overlap(Bounds bounds) {

		Bounds overlap = null;

		GeometryEnvelope overlapEnvelope = super.overlap(bounds.toUnit(unit),
				true);
		if (overlapEnvelope != null) {
			overlap = new Bounds(overlapEnvelope, unit);
		}

		return overlap;
	}

	/**
	 * Create a new bounds as the union between this bounds and the provided
	 * 
	 * @param bounds
	 *            bounds
	 * @return union bounds
	 */
	public Bounds union(Bounds bounds) {

		Bounds union = null;

		GeometryEnvelope unionEnvelope = super.union(bounds.toUnit(unit));
		if (unionEnvelope != null) {
			union = new Bounds(unionEnvelope, unit);
		}

		return union;
	}

	/**
	 * Get the western line
	 * 
	 * @return west line
	 */
	public Line getWestLine() {
		return Line.line(getNorthwest(), getSouthwest());
	}

	/**
	 * Get the southern line
	 * 
	 * @return south line
	 */
	public Line getSouthLine() {
		return Line.line(getSouthwest(), getSoutheast());
	}

	/**
	 * Get the eastern line
	 * 
	 * @return east line
	 */
	public Line getEastLine() {
		return Line.line(getSoutheast(), getNortheast());
	}

	/**
	 * Get the northern line
	 * 
	 * @return north line
	 */
	public Line getNorthLine() {
		return Line.line(getNortheast(), getNorthwest());
	}

	/**
	 * Convert the bounds to be precision accurate minimally containing the
	 * bounds. Each bound is equal to or larger by the precision degree amount.
	 * 
	 * @param precision
	 *            precision in degrees
	 * @return precision bounds
	 */
	public Bounds toPrecision(double precision) {

		Bounds bounds = toDegrees();

		double minLon = GridUtils.precisionBefore(bounds.getMinLongitude(),
				precision);
		double minLat = GridUtils.precisionBefore(bounds.getMinLatitude(),
				precision);
		double maxLon = GridUtils.precisionAfter(bounds.getMaxLongitude(),
				precision);
		double maxLat = GridUtils.precisionAfter(bounds.getMaxLatitude(),
				precision);

		return Bounds.degrees(minLon, minLat, maxLon, maxLat);
	}

	/**
	 * Get the pixel range where the bounds fit into the tile
	 * 
	 * @param tile
	 *            tile
	 * @return pixel range
	 */
	public PixelRange getPixelRange(GridTile tile) {
		return getPixelRange(tile.getWidth(), tile.getHeight(),
				tile.getBounds());
	}

	/**
	 * Get the pixel range where the bounds fit into the provided bounds
	 * 
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @param bounds
	 *            bounds
	 * @return pixel range
	 */
	public PixelRange getPixelRange(int width, int height, Bounds bounds) {
		bounds = bounds.toMeters();
		Pixel topLeft = GridUtils.getPixel(width, height, bounds,
				getNorthwest());
		Pixel bottomRight = GridUtils.getPixel(width, height, bounds,
				getSoutheast());
		return new PixelRange(topLeft, bottomRight);
	}

	/**
	 * Get the four line bounds in meters
	 * 
	 * @return lines
	 */
	public List<Line> getLines() {

		Point southwest = getSouthwest();
		Point northwest = getNorthwest();
		Point northeast = getNortheast();
		Point southeast = getSoutheast();

		List<Line> lines = new ArrayList<>();
		lines.add(Line.line(southwest, northwest));
		lines.add(Line.line(northwest, northeast));
		lines.add(Line.line(northeast, southeast));
		lines.add(Line.line(southeast, southwest));

		return lines;
	}

	/**
	 * Copy the bounds
	 * 
	 * @return bounds copy
	 */
	public Bounds copy() {
		return new Bounds(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bounds other = (Bounds) obj;
		if (unit != other.unit)
			return false;
		return true;
	}

}
