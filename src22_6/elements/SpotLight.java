package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Class represent a light source with position and direction
 */
public class SpotLight extends PointLight implements LightSource {

	// Direction of the light
	private Vector direction;

	//Constructor
	public SpotLight(Point3D p, Vector v, Color c , double kc, double kl, double kq) {
		super(p,c, kc, kl, kq);
		this.setDirection(v.normalize());
	}

    public SpotLight(Point3D p, Vector v, Color c ,double r, double kc, double kl, double kq) {
        super(p,c, kc, kl, kq);
        this.setDirection(v.normalize());
        this.setRadius(r);
    }

    // **************** Get/Set ******************** //

    public Vector getDirection() { return direction; }
    public void setDirection(Vector direction) {
        this.direction = direction.normalize();
    }

    // **************** Other ******************** //

    /** Return light's color */
    @Override
    public Color getIntensity() { return new Color(this.color); }

    /** Calculate the light intensity at certain point */
    @Override
	public Color getIntensity(Point3D point) {
		double d = point.distance(this.getPosition());
		Vector l = getL(point);
		Vector dir = getD(point);

		double dirL = dir.dotProduct(l);
		Color color = new Color(this.color);

		double A = super.getKc() * (super.getKL()* d) * (super.getKq() * (d * d));
		
		color.scale(dirL/A);
		return color;
	}
	
	/** return Direction of the light */
	@Override
	public Vector getD(Point3D point) {
		return this.direction.normalize();
	}

}
