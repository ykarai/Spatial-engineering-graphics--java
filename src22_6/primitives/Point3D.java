package primitives;

/**
 * class represent 3D point in 3D model
 */

public class Point3D extends Point2D {

	private Coordinate coordZ;

	/************ Constructors **********/
	public Point3D(double x, double y, double z) {
		super(x, y);
		coordZ = new Coordinate(z);
	}

	public Point3D(Point3D other) {
		super(other);
		coordZ = new Coordinate(other.coordZ);
	}

	/********** Getters *********/
	public double getCoordZ() {
		return this.coordZ.getCoord();
	}
	// public void setcoordZ(double z){
	// this.coordY.setcoordZ = z;
	// }

	/************ Administration *********/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return super.equals(other) && this.coordZ.equals(other.coordZ);
	}

	public String tostring() {
		return "(x,y,z)" + super.tostring() + "," + coordZ.tostring() + ")";
	}

	/************ OperatorsAndFunctions *********/
	public double distance(Point3D other) {

		double x = Math.abs(this.getCoordX() - other.getCoordX());
		double y = Math.abs(this.getCoordY() - other.getCoordY());
		double z = Math.abs(this.getCoordZ() - other.getCoordZ());

		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	public Point3D AddVectorToPoint(Vector other) {

		double x = this.getCoordX() + other.get_head().getCoordX();
		double y = this.getCoordY() + other.get_head().getCoordY();
		double z = this.getCoordZ() + other.get_head().getCoordZ();

		return new Point3D(x, y, z);
	}
	public Vector subtractPoint(Point3D other) {

		double x = this.getCoordX() - other.getCoordX();
		double y = this.getCoordY() - other.getCoordY();
		double z = this.getCoordZ() - other.getCoordZ();

		return new Vector(x, y, z);
	}


}
