package geometries;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;

//import java.util.Vector;
//import primitives.Point3D;
import primitives.*;

/**
 * Class represent a Sphere extends abstract class RadialGeometry
 */
public class Sphere extends RadialGeometry {

	private Point3D center;


	/************ Constructors **********/
	public Sphere(Point3D c, double _radius,Color emission) {
		super(_radius);
		this.center = c;
		this.emission=emission;
        this.material=new Material();
	}

	//Copy constructor
	public Sphere(Sphere other) {
		super(other.get_radius());
		this.center = other.get_point();
		this.emission=other.getEmission();
		this.material=other.getMaterial();
	}

	// ********* Getters/Setters ************** //
	public Point3D get_point() { return this.center; }

    public Color getEmission() { return emission; }
    public void setEmission(Color emission) { this.emission = emission; }

    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }

    // ************ Override Functions *********//
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Sphere))
			return false;

		Sphere other = (Sphere) obj;
		if (other.center.equals(this.center) && other.get_radius() == this.get_radius())
			return true;
		else
			return false;
	}

    /** Return Normal of a point on the sphere */
    @Override
    public Vector getNormal(Point3D p) {
        Vector vector1 = new Vector(p.subtractPoint(this.center));
        return vector1.normalize();
    }

    /** Function return list of Intersections with our shape given a Ray */
	@Override
	public List<Point3D> findIntersections(Ray ray) {

		List<Point3D> intersections=new ArrayList<Point3D>();//array list of Intersections
		Point3D p0 = new Point3D(ray.getPOO());

		Vector vectorU = new Vector(this.center.getCoordX() - ray.getPOO().getCoordX(),
				this.center.getCoordY() - ray.getPOO().getCoordY(),
				this.center.getCoordZ() - ray.getPOO().getCoordZ());

		double tm =(ray.get_direction().normalize()).dotProduct(vectorU);
		
		double d =Math.sqrt((vectorU.lengthVector()*vectorU.lengthVector()) -(tm*tm));
		
		if (d>super.get_radius()) //"there are no intersections"
			return intersections;
		
		double th =Math.sqrt((Math.pow(super.get_radius(), 2))-(Math.pow(d, 2)));
		
		
		double t1=tm+th;
		double t2=tm-th;
		if (t1>=0) {
			intersections.add(p0.AddVectorToPoint(((ray.get_direction().normalize()).multiplySkalar(t1))));
		}
		if (t2>=0) {
			intersections.add(p0.AddVectorToPoint(((ray.get_direction().normalize()).multiplySkalar(t2))));
		}
		
		return intersections;
	}
	

}
