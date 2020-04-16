package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.*;

/**
 * Class represent Triangle extends abstract class Geometry
 */
public class Triangle extends Plane {

	private Point3D point1;
	private Point3D point2;
	private Point3D point3;


	/************ Constructors **********/
	public Triangle(Point3D p1, Point3D p2, Point3D p3,Color emission) {
		super(p1, p2, p3);
		this.point1 = p1;
		this.point2 = p2;
		this.point3 = p3;
		this.emission=emission;
        this.material=new Material();
	}

	//Copy constructor
	public Triangle(Triangle t) {
		super(t.point1, t.point2, t.point3);
		this.point1 = t.get_point1();
		this.point2 = t.get_point2();
		this.point3 = t.get_point3();
		this.emission=t.getEmission();
        this.material=t.getMaterial();
	}

	// ********* Getters/Setters  ************** //
	public Point3D get_point1() {
		return this.point1;
	}
	public Point3D get_point2() {
		return this.point2;
	}
	public Point3D get_point3() {
		return this.point3;
	}

	public Color getEmission() {
		return emission;
	}
	public void setEmission(Color emission) {
		this.emission = emission;
	}

    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }

    // ************ Override Functions ********* //

    /** Return normal of the plane */
    @Override
    public Vector getNormal(Point3D a) {
        return super.get_normal();
    }

    @Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Triangle))
			return false;

		Triangle other = (Triangle) obj;
        return other.point1.equals(this.point1) && other.point2.equals(this.point2) && other.point3.equals((this.point3));
	}

    /** Function return list of Intersections with our shape given a Ray */
	@Override
	public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = new ArrayList<Point3D>();

        intersections = super.findIntersections(ray); // go to plane function

        if (intersections.isEmpty())
            return intersections;

        Vector v1 = new Vector(point1.getCoordX() - ray.getPOO().getCoordX(),
                point1.getCoordY() - ray.getPOO().getCoordY(), point1.getCoordZ() - ray.getPOO().getCoordZ());
        Vector v2 = new Vector(point2.getCoordX() - ray.getPOO().getCoordX(),
                point2.getCoordY() - ray.getPOO().getCoordY(), point2.getCoordZ() - ray.getPOO().getCoordZ());
        Vector v3 = new Vector(point3.getCoordX() - ray.getPOO().getCoordX(),
                point3.getCoordY() - ray.getPOO().getCoordY(), point3.getCoordZ() - ray.getPOO().getCoordZ());

        Vector n1 = new Vector((v1.crossProduct(v2)));
        Vector n2 = new Vector((v2.crossProduct(v3)));
        Vector n3 = new Vector((v3.crossProduct(v1)));

        if(( n1.lengthVector() * n2.lengthVector() * n3.lengthVector() )!= 0)
        {    n1.normalize();n2.normalize();n3.normalize();}

        Point3D pointOnPlane = intersections.get(0);
        Vector vectorP0P = new Vector(pointOnPlane.getCoordX() - ray.getPOO().getCoordX(),
                pointOnPlane.getCoordY() - ray.getPOO().getCoordY(),
                pointOnPlane.getCoordZ() - ray.getPOO().getCoordZ());


        if (vectorP0P.dotProduct(n1) == 0 || vectorP0P.dotProduct(n2) == 0 || vectorP0P.dotProduct(n3) == 0) {
            intersections.removeAll(intersections);

        }
        if ((vectorP0P.dotProduct(n1) < 0 && vectorP0P.dotProduct(n2) < 0 && vectorP0P.dotProduct(n3) < 0) ||
                (vectorP0P.dotProduct(n1) > 0 && vectorP0P.dotProduct(n2) > 0 && vectorP0P.dotProduct(n3) > 0)) {
            return intersections;
        } else {
            intersections.removeAll(intersections);
        }
        return intersections;
    }

}
