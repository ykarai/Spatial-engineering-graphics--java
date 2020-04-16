package geometries;

import java.util.ArrayList;
import java.util.List;

//import java.util.Vector;
//import primitives.Point3D;
//import primitives.Ray;

import primitives.*;

/**
 * Class represent Cylinder extends abstract class RadialGeometry
 */
public class Cylinder extends RadialGeometry {

	private Ray cRay;
	double Height;



	// ***************** Constructors *************** //
	public Cylinder(double _radius, Ray r, double h,Color emission) {
		super(_radius);
		this.cRay = r;
		this.Height = h;
		this.emission=emission;
        this.material=new Material();
	}

    //Copy constructor
	public Cylinder(Cylinder other) {
		super(other.get_radius());
		this.cRay = other.get_cRay();
		this.Height = other.get_height();
		this.emission = other.getEmission();
        this.material=other.getMaterial();
	}

	// *********** Getters/Setters  ***************** //
	public Ray get_cRay() {return this.cRay;}
	public double get_height() {return this.Height;}

	public Color getEmission() {
		return emission;
	}
	public void setEmission(Color emission) {
		this.emission = emission;
	}

    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }

    // ************* Override Functions ************** //
    @Override
	public Vector getNormal(Point3D p1) {
		Point3D p0 = cRay.getPOO();
		Vector v = p1.subtractPoint(p0);
		double t = cRay.get_direction().dotProduct(v);
		Point3D p2 = p0.AddVectorToPoint(cRay.get_direction().multiplySkalar(t));
		return p1.subtractPoint(p2).normalize();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cylinder))
			return false;

		Cylinder other = (Cylinder) obj;
        return other.cRay.equals(this.cRay) && other.Height == this.Height;
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		return null;
	}

}
