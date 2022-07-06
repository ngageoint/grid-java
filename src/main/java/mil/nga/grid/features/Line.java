package mil.nga.grid.features;

import java.util.ArrayList;
import java.util.List;

import mil.nga.grid.GridUtils;

/**
 * Line between two points
 * 
 * @author osbornb
 */
public class Line extends mil.nga.sf.Line {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a line
	 * 
	 * @param point1
	 *            first point
	 * @param point2
	 *            second point
	 * @return line
	 */
	public static Line line(Point point1, Point point2) {
		return new Line(point1, point2);
	}

	/**
	 * Copy a line
	 * 
	 * @param line
	 *            line to copy
	 * @return line
	 */
	public static Line line(Line line) {
		return new Line(line);
	}

	/**
	 * Constructor
	 * 
	 * @param point1
	 *            first point
	 * @param point2
	 *            second point
	 */
	public Line(Point point1, Point point2) {
		setPoints(point1, point2);
	}

	/**
	 * Copy Constructor
	 * 
	 * @param line
	 *            line to copy
	 */
	public Line(Line line) {
		super(line);
	}

	/**
	 * Get the first point
	 * 
	 * @return first point
	 */
	public Point getPoint1() {
		return (Point) startPoint();
	}

	/**
	 * Set the first point
	 * 
	 * @param point1
	 *            first point
	 */
	public void setPoint1(Point point1) {
		setPoints(point1, getPoint2());
	}

	/**
	 * Get the second point
	 * 
	 * @return second point
	 */
	public Point getPoint2() {
		return (Point) endPoint();
	}

	/**
	 * Set the second point
	 * 
	 * @param point2
	 *            second point
	 */
	public void setPoint2(Point point2) {
		setPoints(getPoint1(), point2);
	}

	/**
	 * Set the points
	 * 
	 * @param point1
	 *            first point
	 * @param point2
	 *            second point
	 */
	public void setPoints(Point point1, Point point2) {
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(point1);
		points.add(point2);
		setPoints(points);
		validateUnits();
	}

	/**
	 * Get the unit
	 * 
	 * @return unit
	 */
	public Unit getUnit() {
		return getPoint1().getUnit();
	}

	/**
	 * Is in the provided unit type
	 * 
	 * @param unit
	 *            unit
	 * @return true if in the unit
	 */
	public boolean isUnit(Unit unit) {
		return getPoint1().isUnit(unit);
	}

	/**
	 * Is this line in degrees
	 * 
	 * @return true if degrees
	 */
	public boolean isDegrees() {
		return getPoint1().isDegrees();
	}

	/**
	 * Is this line in meters
	 * 
	 * @return true if meters
	 */
	public boolean isMeters() {
		return getPoint1().isMeters();
	}

	/**
	 * Convert to the unit
	 * 
	 * @param unit
	 *            unit
	 * @return point in units, same point if equal units
	 */
	public Line toUnit(Unit unit) {
		Line line = null;
		if (isUnit(unit)) {
			line = this;
		} else {
			line = copy();
			line.setPoints(getPoint1().toUnit(unit), getPoint2().toUnit(unit));
		}
		return line;
	}

	/**
	 * Convert to degrees
	 * 
	 * @return line in degrees, same line if already in degrees
	 */
	public Line toDegrees() {
		return toUnit(Unit.DEGREE);
	}

	/**
	 * Convert to meters
	 * 
	 * @return line in meters, same line if already in meters
	 */
	public Line toMeters() {
		return toUnit(Unit.METER);
	}

	/**
	 * Get the intersection between this line and the provided line
	 * 
	 * @param line
	 *            line
	 * @return intersection
	 */
	public Point intersection(Line line) {
		return GridUtils.intersection(this, line);
	}

	/**
	 * Copy the line
	 * 
	 * @return line copy
	 */
	public Line copy() {
		return new Line(this);
	}

	/**
	 * Validate units are the same
	 */
	private void validateUnits() {
		if (!getPoint1().isUnit(getPoint2().getUnit())) {
			throw new IllegalArgumentException(
					"Points are in different units. point1: "
							+ getPoint1().getUnit() + ", point2: "
							+ getPoint2().getUnit());
		}
	}

}
