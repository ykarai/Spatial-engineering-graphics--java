package geometries;

import java.util.List;
import java.util.Map;

//import java.util.Vector;

//import primitives.Point3D;
import primitives.*;

/**
 * abstract class represent Geometric shape using primitives
 */
public abstract class Geometry {

    public Color emission;  //represent the color of a specific shape
    Material material;      //material of the shape

    /************ Constructors **********/
	public Geometry() {}

    /** Get the Emission of the shape
     *
     * @return shape's emission color
     */
    public abstract Color getEmission ();


    /** Get the Material of the shape
     *
     * @return shape's material
     */
    public abstract Material getMaterial ();

    /** Get The Normal to a Point in the Shape
     *
     * @param a  A point on the shape
     * @return point's normal
     */
	public abstract Vector getNormal(Point3D a);


    /** Function return all Intersections points of the ray with the shapes
     *
     * @param ray A ray to search its intersections with the shape
     * @return Intersections points of the ray with the shape
     */
	public abstract List<Point3D> findIntersections(Ray ray);


}
