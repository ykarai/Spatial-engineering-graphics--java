package elements;

import javax.swing.text.Position;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Class represent a light with direction with constant intensity (like the sun)
 */
public class DirectionalLight extends Light implements LightSource {

	// Direction of the light
	private Vector direction;

	// Constructor
	public DirectionalLight(Color c, Vector direction) {
		this.direction = direction.normalize();
		this.color = c;
	}

	// ********************* Get/Set ************************** //

	public Vector getDirection() {
		return direction;
	}
	public void setDirection(Vector direction) {
		this.direction = direction.normalize();
	}

    // ********************** Other *************************** //

    /** Return light's color */
    @Override
    public Color getIntensity() { return new Color(this.color); }

    /** Calculate the light intensity at certain point */
    @Override
    public Color getIntensity(Point3D point) {
        //No attenuation with distance
        return new Color(this.color);
    }

    /** Return light's direction */
	@Override
	public Vector getL(Point3D point) {
		return this.direction.normalize();
	}

	/** Return light's direction */
	@Override
	public Vector getD(Point3D point) {
		return this.direction.normalize();
	}

	/** Return distance from light's position */
    @Override
    public double getDistance(Point3D point) { return Double.MAX_VALUE; }

}
