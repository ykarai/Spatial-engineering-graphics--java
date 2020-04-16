package geometries;

import java.util.*;

import primitives.*;
import primitives.Vector;

/**
 * Class represent list of Geometry shapes with structural design pattern
 * "Composite"
 */
public class Geometries extends Geometry {

	private List<Geometry> GeometryShapes = new ArrayList<Geometry>();


    // ********************** Get/Set *********************** //
    public List<Geometry> getGeometryShapes() { return GeometryShapes; }
    public void setGeometryShapes(List<Geometry> geometryShapes) { GeometryShapes = geometryShapes; }

    public Color getEmission() { return null;}

    @Override
    public Material getMaterial() { return null; }
    public Color setEmission(Color color) { return this.emission=color; }


    // ********************** Other ************************ //

    /** Returns normal of the point in the shape
     *
     * @param p point on the shape
     * @return normal of the point
     */
    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }


    /** Function returns all Intersections of the ray with all shapes
     *
     * @param ray A ray to search its intersections with the geometries
     * @return Intersections points of the ray with the shapes
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {

        List<Point3D> intersections0 = new ArrayList<Point3D>();

        for (Geometry geometry : GeometryShapes) {
            intersections0.addAll(geometry.findIntersections(ray));
        }
        return intersections0;
    }


    /** Function return all Intersections points (and their Geometries) of the ray with the shapes
     *
     * @param ray A ray to search its intersections with the geometries
     * @return Intersections points (and their Geometries) of the ray with the shapes
     */
	public Map<Geometry, List<Point3D>> findIntersectionsByGeometry(Ray ray) {

		Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
		for (Geometry geometry : GeometryShapes) {
			List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
			if(geometryIntersectionPoints.size() != 0)
				intersectionPoints.put(geometry, geometryIntersectionPoints);
		}
		return intersectionPoints;
	}


    /** Function return all Intersections points (and their Geometries) of the ray with the shapes,
     *  with distance limitation .
     *
     * @param ray A ray to search its intersections with the geometries
     * @param distanceLimit Maximum distance limitation. Intersections with higher distance
     *                 will not be included.
     * @return Intersections points (and their Geometries) of the ray with the shapes
     */
    public Map<Geometry, List<Point3D>> findIntersectionsByGeometry(Ray ray, double distanceLimit) {

        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
        for (Geometry geometry : GeometryShapes) {
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);

            //Distance check
            ListIterator<Point3D> iter = geometryIntersectionPoints.listIterator();
            while(iter.hasNext()) {//Go through all points
                Point3D p = iter.next();
                if(p.distance(ray.getPOO()) >= distanceLimit)
                    iter.remove(); //Remove point
            }

            if(geometryIntersectionPoints.size() != 0)
                intersectionPoints.put(geometry, geometryIntersectionPoints);
        }
        return intersectionPoints;
    }

}
