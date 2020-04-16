package renderer;

import elements.DirectionalLight;
import elements.LightSource;
import elements.PointLight;
import geometries.Geometry;
import geometries.Plane;
import primitives.*;
import primitives.Color;
import primitives.Vector;
import scene.Scene;
import java.util.*;
import java.util.List;

/* The class is in charge of rendering the entire scene
 * Including intersection points with shapes
 */
public class Render {

    private ImageWriter imageWriter;
    private Scene scene;

    // **************** Constructors ********************** //
    public Render(ImageWriter imageWriter, Scene scene) {
        this.imageWriter = imageWriter;
        this.scene = scene;
    }


    // **************** getters/setters ******************* //
    public ImageWriter getImageWriter() {
        return imageWriter;
    }

    public void setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
    }


    // ***************** Other Functions ****************** //
    /**
     * Render the scene into a picture
     */
    public void renderImage() {

        for (int i = 0; i < imageWriter.getNx(); i++)
            for (int j = 0; j < imageWriter.getNy(); j++) {

                //Construct camera rays
                Ray ray = this.scene.getCamera().constructRayThroughPixel(this.imageWriter.getNx(),
                        this.imageWriter.getNy(), i, j, this.scene.getDistance(), this.imageWriter.getWidth(),
                        this.imageWriter.getHeight());

                //Search for intersection
                Map<Geometry, List<Point3D>> intersectionPoints = this.scene.getGeo().findIntersectionsByGeometry(ray);

                //Decide pixel's color
                if (intersectionPoints.isEmpty()) {
                    //Background Color
                    this.imageWriter.writeEntirePixel(i, j, this.scene.getBackground().getColor());
                } else {
                    //Find closest point and its color
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints,ray);
                    this.imageWriter.writeEntirePixel(i, j, calcColor(closestPoint,ray).getColor());
                }
            }
    }

    /** Get a list of points and return the closest point to the camera
     *
     * @param intersectionPoints Map with Geometry Keys and list of intersection points as Value
     * @param inRay The ray which the intersection points was found with
     * @return The closest point to the camera of all the points in the given map
     */
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints,Ray inRay) {

        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();

        //Temporarily decide max distance possible
        double distance = Double.MAX_VALUE;
        Point3D p0 = inRay.getPOO();

        //Go through all the points
        for (Geometry key : intersectionPoints.keySet())
            //The closest will be picked
            for (Point3D p : intersectionPoints.get(key))
                if (p0.distance(p) < distance) {

                    minDistancePoint.clear(); // make it empty
                    minDistancePoint.put(key, new Point3D(p));
                    distance = p0.distance(p);
                }

        return minDistancePoint;
    }

    /** Function return the color of a point for rendering */
    private Color calcColor(Map<Geometry, Point3D> closestPoint, Ray inRay) {
        return calcColor(closestPoint, inRay, 4, 1);
    }

    /** Function return the color of a point for rendering
     *
     * @param closestPoint Map with one entry of Geometry as key and Point3D as value
     * @param inRay Ray of the point
     * @param level Recursion limit
     * @param k Attenuation of refraction/reflectance
     * @return The color of the point given in closestPoint value
     */
    private Color calcColor(Map<Geometry, Point3D> closestPoint, Ray inRay, int level, double k) {

        //Base case - return black
        if (level == 0 || Coordinate.isZero(k)) return new Color(0, 0, 0);

        // Get the Geometry and the point
        Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
        Geometry geometry = entry.getKey();
        Point3D point = entry.getValue();

        //Get geometry details
        int nShininess = geometry.getMaterial().getnShininess();
        double kd = geometry.getMaterial().getKd();
        double ks = geometry.getMaterial().getKs();
        Vector n = geometry.getNormal(point);
        Vector v = inRay.get_direction();

        // Add its emission and ambientlight
        Color color = scene.getAmbientLight().getIntensity();
        color.add(geometry.getEmission());

        //Calculate Diffusive and Specular of every lightsource in the scene
        for (LightSource lightSource : scene.getLights()) {

            //Get light details
            Color lightIntensity = lightSource.getIntensity(point);
            Vector l = lightSource.getL(point);

            double o = 0;
            //Add Diffusive and Specular light
            if ((l.dotProduct(n) * v.dotProduct(n)) > 0) {

                o = occluded(lightSource, point, geometry);
                if (!Coordinate.isZero(o * k)) {
                    lightIntensity.scale(o);
                    color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }

        // Recursive call for a reflected ray
        Color reflectedLight = new Color(0,0,0);
        double kr = entry.getKey().getMaterial().getKr();

        if (!Coordinate.isZero(kr) || level==1) {
            Vector r =  v.subtractVector(n.multiplySkalar(v.dotProduct(n) * 2));
            //Floating point fix
            Vector normal = geometry.getNormal(point);
            Point3D fixedPoint = floatingPointFix(point,normal,r);

            //Find intersections
            Ray reflectedRay = new Ray(fixedPoint,r);
            Map<Geometry, List<Point3D>> reflectedIntersections = scene.getGeo().findIntersectionsByGeometry(reflectedRay);

            //Decide color
            if (reflectedIntersections.isEmpty()) {
                reflectedLight = scene.getBackground();
            }
            else {
                Map<Geometry, Point3D> reflectedPoint = getClosestPoint(reflectedIntersections,reflectedRay);
                //Recursive call
                reflectedLight = calcColor(reflectedPoint, reflectedRay, level - 1, k * kr);
            }
            reflectedLight.scale(kr);
        }


        // Recursive call for a refracted ray
        Color refractedLight = new Color(0,0,0);
        double kt = entry.getKey().getMaterial().getKt();

        if (!Coordinate.isZero(kt) || level==1) {
            //Floating point fix
            Vector normal = geometry.getNormal(point);
            Point3D fixedPoint = floatingPointFix(point,normal,v);

            //Find intersections
            Ray refractedRay = new Ray(fixedPoint ,v) ;

            Map<Geometry, List<Point3D>> refractedIntersections = scene.getGeo().findIntersectionsByGeometry(refractedRay);

            //Decide color
            if (refractedIntersections.isEmpty()) {
                refractedLight = scene.getBackground();
            }
            else {
                Map<Geometry, Point3D> refractedPoint = getClosestPoint(refractedIntersections,refractedRay);
                //Recursive call
                refractedLight = calcColor(refractedPoint, refractedRay, level - 1, k * kt);
            }
            refractedLight.scale(kt);
        }

        color.add(reflectedLight, refractedLight);
        return color;

    }

    /** Calculate Diffusive light
     *
     * @param kd Diffusive factor
     * @param l Vector from light to the point
     * @param n Normal of the point
     * @param lightIntensity Containing ambient and emission light of the point
     * @return Diffusive color of the point
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        lightIntensity.scale(Math.abs(n.dotProduct(l)) * kd);
        return new Color(lightIntensity);
    }

    /** Calculate Specular light
     *
     * @param ks Specular factor
     * @param l Vector from light to the point
     * @param n Normal of the point
     * @param v Vector from camera to the point
     * @param nShininess Shininess factor
     * @param lightIntensity Containing ambient and emission light of the point
     * @return Specular color of the point
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

        Vector r = l.subtractVector(n.multiplySkalar(l.dotProduct(n) * 2));
        double vr = v.dotProduct(r);
        if (vr >= 0)
            return new Color(0, 0, 0);

        lightIntensity.scale(ks * Math.pow(-vr, nShininess));
        return new Color(lightIntensity);
    }

    /** Returns percent of the light at given point
     *
     * @param light Object of Light
     * @param point Specific point on the scene
     * @param geometry The geometry which the point is belong to
     * @return percent of light at given point
     */
    private double occluded(LightSource light, Point3D point, Geometry geometry) {

        //Get Light details
        Vector lightDirection = light.getL(point);
        double lightDistance =  light.getDistance(point);
        Point3D lightPosition = (light instanceof PointLight)? ((PointLight)light).getPosition() : null;
        double lightRadius =    (light instanceof PointLight)? ((PointLight)light).getRadius() : 0;
        double spiralGrowth =   0.1;
        int numOfRays =         (int)(lightRadius/spiralGrowth)+1;

        double lightPercentOverAll = 0;
        for (int i = 0; i<numOfRays ; i++) {

            //Vector from point to light
            Vector toLightVector;
            if (light instanceof  DirectionalLight)
                toLightVector = lightDirection.multiplySkalar(-1);
            else {
                //Generate point on spiral
                Point3D spiralPoint = generateSpiralPoint(spiralGrowth, lightPosition, lightDirection, i);
                toLightVector = spiralPoint.subtractPoint(point);
            }

            //Floating point fix
            Vector normal = geometry.getNormal(point);
            Point3D fixedPoint = floatingPointFix(point,normal,toLightVector);

            //Find Ray intersections
            Ray lightRay = new Ray(fixedPoint, toLightVector);
            Map<Geometry, List<Point3D>> intersectionPoints = scene.getGeo().
                    findIntersectionsByGeometry(lightRay, lightDistance);

            //Aggregate transparency attenuation
            double shadowK = 1;
            for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet())
                shadowK *= entry.getKey().getMaterial().getKt();

            //Add light-percent of each ray
            double lightPercentPerRay = (double)1/numOfRays;
            lightPercentOverAll += shadowK * lightPercentPerRay;
        }

        return lightPercentOverAll;
    }

    /** Generate spiral on lightsource and return a specific point on
     *  the spiral - depends the value of t
     *
     * @param spiralGrowth    Growth rate of the spiral
     * @param spiralPosition  Center point of the spiral
     * @param spiralNormal    Normal to the spiral-surface
     * @param t               index number of the requested point (t=0 : central point of the spiral)
     * @return a specific point on the spiral - depends the value of t
     */
    private Point3D generateSpiralPoint(Double spiralGrowth, Point3D spiralPosition, Vector spiralNormal, int t) {

        //Build spiral's surface
        Plane spiralSurface = new Plane(spiralPosition, spiralNormal);
        Point3D surfacePoint = spiralSurface.findPointOnPlane();

        //Vectors of Spiral's surface
        Vector v = surfacePoint.subtractPoint(spiralPosition).normalize();
        Vector u = v.crossProduct(spiralNormal).normalize();

        //Spiral components
        double spiralXcomponent = spiralGrowth*t*Math.cos(t);
        double spiralYcomponent = spiralGrowth*t*Math.sin(t);

        //Calculate spiral's point
        Vector vSpiralPosition = new Vector(spiralPosition.getCoordX(), spiralPosition.getCoordY(), spiralPosition.getCoordZ());
        Vector spiralVector = vSpiralPosition.addVector(
                u.multiplySkalar(spiralXcomponent).addVector(v.multiplySkalar(spiralYcomponent)));

        //Final light point
        return new Point3D(spiralVector.get_head());
    }

    /** Move the point in normal direction or the exact opposite direction
     *  depends on the ray that goes from the point
     *
     * @param point The point to move its position
     * @param rayDirection The vector that goes in ray-direction (from the point)
     * @return The point after been moved in the right direction
     */
    private Point3D floatingPointFix(Point3D point, Vector normal, Vector rayDirection)
    {
        Vector epsVector = normal.multiplySkalar((normal.dotProduct(rayDirection) > 0) ? 0.0001 : -0.0001);
        return point.AddVectorToPoint(epsVector);
    }
}