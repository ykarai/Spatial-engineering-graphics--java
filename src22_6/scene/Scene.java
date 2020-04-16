package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Sphere;
import primitives.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represent a Scene that includes Geometry shapes which
 * describes the scene
 */
public class Scene {

	private String name;                // name of scene
	private Color background;           // Background color of scene
	private Geometries geo;             // list of Geometry shapes
	private Camera camera;              // camera
	private double distance;            // distance of the view plane from the camera
    private AmbientLight ambientLight;  //ambient light
    private List<LightSource> lights;   //light-source list


	// ******************* Constructors ********************* //

	public Scene(String name, Color color, Geometries geo, Camera camera, double distance,AmbientLight ambientLight) {
		this.name = name;
		this.background = color;
		this.geo = geo;
		this.camera = camera;
		this.distance = distance;
		this.ambientLight=ambientLight;
        this.lights = new ArrayList<>();
    }

	public Scene(String string) {
        this.name = string;
        this.lights = new ArrayList<>();
    }


    // ******************* Getters/Setters **************** //

    public Color getBackground() {return this.background;}
    public void setBackground(Color color0) {	this.background = color0;}

    public Camera getCamera() {	return this.camera;}
    public void setCamera(Camera camera0) {	this.camera = camera0;}

    public double getDistance() {return this.distance;}
    public void setDistance(double distance0) {	this.distance = distance0;}

    public void setGeomtries(Geometries geometries) {this.geo = geometries;}
    public Geometries getGeo() {return this.geo;}

    public AmbientLight getAmbientLight() {return this.ambientLight;}
    public void setAmbientLight(AmbientLight ambientLight) {this.ambientLight = ambientLight;}

    public List<LightSource> getLights() { return lights; }
    public void setLights(List<LightSource> lights) { this.lights = lights; }


    // ******************* Other Functions **************** //

    /** Add shape to the scene
     *
     * @param geometry A geomerty to add to the sence
     */
    public void addGeometry(Geometry geometry) {this.geo.getGeometryShapes().add(geometry);}

	public void removeGeometry(Geometry geometry) {this.geo.getGeometryShapes().remove(geometry);}
		

}
