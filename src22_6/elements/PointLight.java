package elements;

import java.awt.RenderingHints;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Class represent a point light
 */
public class PointLight extends Light implements LightSource {


	private Point3D position;  //Light point position
    private double radius;   //Light's radius
	private double Kc, Kl, Kq; //Attenuation constants

	// Default Constructor
	public PointLight() {
		this.color = new Color(0, 0, 0);
	}

	//Constructors
	public PointLight(Point3D p, Color c , double kc, double kl, double kq) {
		this.position = p;
		this.color = c;
        this.Kc = kc;
        this.Kl = kl;
        this.Kq = kq;
    }

    public PointLight(Point3D p, Color c ,double r, double kc, double kl, double kq) {
        this.position = p;
        this.color = c;
        this.radius = r;
        this.Kc = kc;
        this.Kl = kl;
        this.Kq = kq;
    }

    // **************** Get/Set ******************** //

	public Point3D getPosition() {
		return position;
	}
	public void setPosition(Point3D position) {
		this.position = position;
	}

    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }

    public double getKc() {
        return this.Kc;
    }
    public double getKL() { return this.Kl; }
    public double getKq() { return this.Kq; }

    // **************** Other ******************** //

    /** Return light's color */
    @Override
    public Color getIntensity() { return new Color(this.color); }

    /** Calculate the light intensity at certain point */
    @Override
	public Color getIntensity(Point3D point) {

		double d = point.distance(this.position);
		Color color = new Color(this.color);

		double A = this.Kc*(this.Kl * d)*(this.Kq * (d * d));
		color.scale(1/A);
		return color;
	}

    /** Return vector from the light to a point */
	@Override
	public Vector getL(Point3D point) {
		return point.subtractPoint(this.getPosition()).normalize();
	}

    /** Return vector from the light to a point */
    @Override
	public Vector getD(Point3D point) {
		return point.subtractPoint(this.getPosition()).normalize();
	}

    /** Return distance from light's position */
    @Override
    public double getDistance(Point3D point) { return this.position.distance(point); }
}
