package primitives;

/**
 * class for represent 2D points in 2D model
 * 
 * 
 */

public class Point2D {

	private Coordinate coordX;
	private Coordinate coordY;

	/************ Constructors **********/
	public Point2D(double x, double y) {
		coordX = new Coordinate(x);
		coordY = new Coordinate(y);
	}

	public Point2D(Point2D other) {
		coordX = other.coordX;
		coordY = other.coordY;
	}

	/********** Getters *********/
	public double getCoordX() {
		return this.coordX.getCoord();
	}

	public double getCoordY() {
		return this.coordY.getCoord();
	}

	/************ Administration *********/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point2D))
			return false;
		Point2D other = (Point2D) obj;
		return this.coordX.equals(other.coordX) && this.coordY.equals(other.coordY);
	}

	public String tostring() {
		return "(" + coordX.tostring() + "," + coordY.tostring() + "";
	}

	

}
