package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/*
 * Interface of Light Sources
 */

public interface LightSource {

    /** Intensity of the light at certain point
     *
     * @param point Specific point on the scene
     * @return Color of the given point
     */
    public abstract Color getIntensity(Point3D point);


    /** Direction of the light to a point
     *
     * @param point Specific point on the scene
     * @return Vector from light source to the point (if the light doesn't
     *              have a position - it returns light's direction vector
     */
    public abstract Vector getL(Point3D point);


    /** Get Light direction
     *
     * @param point Specific point on the scene
     * @return Direction of the light (if it doesn't have
     *         a direction - it returns vector from light to the point)
     */
    public abstract Vector getD(Point3D point);


    /** Return distance from light's position
     *
     * @param point Specific point on the scene
     * @return Distance from light position to the given point
     */
    public abstract double getDistance(Point3D point);

}
