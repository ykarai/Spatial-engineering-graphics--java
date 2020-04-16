package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.*;

/**
 *  Class represent a Plane extends abstract class Geometry
 */
public class Plane extends Geometry {

	private Point3D point;
	private Vector normal;


	// ************ Constructors ********** //
    public Plane(Point3D p1, Vector n) {
        super();
        this.point = p1;
        this.normal = n.normalize();
    }
    
	public Plane(Point3D p1, Vector n,Color emission) {
		super();
		this.point = p1;
		this.normal = n.normalize();
		this.emission=emission;
		this.material=new Material();
	}

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        super();
        this.point = p1;
        primitives.Vector vector1 = new primitives.Vector(p1.getCoordX() - p2.getCoordX(),
                p1.getCoordY() - p2.getCoordY(), p1.getCoordZ() - p2.getCoordZ());
        primitives.Vector vector2 = new primitives.Vector(p1.getCoordX() - p3.getCoordX(),
                p1.getCoordY() - p3.getCoordY(), p1.getCoordZ() - p3.getCoordZ());
        this.normal = (vector1.crossProduct(vector2)).normalize();
        this.material=new Material();
    }

    //Copy Constructor
	public Plane(Plane other) {
		super();
		this.point = other.get_point();
		this.normal = other.get_normal();
		this.emission = other.getEmission();
	}


	// ********* Getters/Setters ************** //
	public Point3D get_point() {
		return this.point;
	}

    public Vector get_normal() { return this.normal;}

	public Color getEmission() {
		return emission;
	}
	public void setEmission(Color emission) {
		this.emission = emission;
	}

    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }

    // ************ Override Functions ********* //
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Plane))
			return false;

		Plane other = (Plane) obj;
        return other.point.equals(this.point) && other.normal.equals(this.normal);
	}

    /** Function return a list of Intersections with the plane given a Ray */
	@Override
	public List<Point3D> findIntersections(Ray ray) {

		List<Point3D> intersections = new ArrayList<Point3D>();

		Vector pointp0 = new Vector(point.getCoordX() - ray.getPOO().getCoordX(),
				point.getCoordY() - ray.getPOO().getCoordY(), point.getCoordZ() - ray.getPOO().getCoordZ());

		double t = this.normal.dotProduct(pointp0);
		t = t / (this.normal.dotProduct((ray.get_direction().normalize())));
		if (t < 0 || Double.isInfinite(t))
			return intersections;
		else {
			intersections.add(ray.getPOO()
					.AddVectorToPoint(((ray.get_direction().normalize()).multiplySkalar(t))));
			return intersections;
		}
	}

    /** Return normal of the plane */
    @Override
    public Vector getNormal(Point3D a) {
        return normal.normalize();
    }


    // ************ Other Functions ********* //
    /** Find a random point on the plane which different from the plane's point
     * 
     * @return A random point on the plane
     */
    public Point3D findPointOnPlane() {

        Vector vPoint = new Vector(point.getCoordX(),point.getCoordY(),point.getCoordZ());
        double A = normal.get_head().getCoordX();
        double B = normal.get_head().getCoordY();
        double C = normal.get_head().getCoordZ();
        Double D = normal.dotProduct(vPoint);

        if (A==0 && B==0)
        {
            if (point.getCoordX() != 1)
                return new Point3D(1,1,D/C);
            else
                return new Point3D(2,1,D/C);
        }

        if (A==0)
        {
            if (point.getCoordX() != 1)
                return new Point3D(1,-C/B+D/B,1);
            else
                return new Point3D(2,-C/B+D/B,1);
        }

        if (point.getCoordY() != 1)
            return new Point3D(-B/A-C/A+D/A,1,1);
        else
            return new Point3D(-2*B/A-C/A+D/A,2,1);

    }
}
