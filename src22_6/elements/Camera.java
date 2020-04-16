package elements;

import geometries.Triangle;
import primitives.*;

/**
 * Class represent a camera by 3 Vectors and the center point of the camera.
 */
public class Camera {

    Point3D p0;  	//Center point
    Vector vUp;  	//Vector up
    Vector vTo;		//Vector Towards a scene
    Vector vRight;	//Vector right


	// **************** Constructors ************* //

    /** Construct camera by point and 2 vectors
     *
     * @param p0 Camera's center point
     * @param vUp Upward vecotr
     * @param vTo Toward vector
     */
	public Camera(Point3D p0, Vector vUp, Vector vTo) {

		if (vUp.dotProduct(vTo) == 0) {
			this.p0 = p0;
			this.vRight = (vUp.crossProduct(vTo)).normalize();
			this.vUp = vUp.normalize();
			this.vTo = vTo.normalize();
		} else
			throw new IllegalArgumentException("Vectors not Vertical");

	}

    /** Copy Constructor
     *
     * @param other Other camera object to initialize the new camera
     */
    public Camera(Camera other) {

		if (other.vUp.dotProduct(other.vTo) == 0) {
			this.p0 = other.p0;
			this.vRight = (other.vUp.crossProduct(other.vTo)).normalize();
			this.vUp = other.vUp.normalize();
			this.vTo = other.vTo.normalize();
		} else
			throw new IllegalArgumentException("Vectors not Vertical");

	}


    // ************** Get/Set ********************* //
    public Point3D getp0() {
        return this.p0;
    }
    public void setp0(Point3D po) {
        this.p0 = po;
    }


	// ************ OperatorsAndFunctions ********* //

    /** Create a ray from the camera through a pixel
     *
     * @param Nx Pixel's width
     * @param Ny Pixel's height
     * @param i Pixel's Row number
     * @param j Pixel's Column number
     * @param screenDistance Distance of the viewplane from the camera
     * @param screenWidth Width of the viewplane
     * @param screenHeight Height of the viewplane
     * @return
     */
	public Ray constructRayThroughPixel(int Nx, int Ny, int i, int j, double screenDistance, double screenWidth,
			double screenHeight) {

        //Center point of the view plane
        Point3D Pc = p0.AddVectorToPoint(vTo.multiplySkalar(screenDistance));

        //Pixel's width/height
        double Ry = screenHeight / Ny;
        double Rx = screenWidth / Nx;

        //Pixel's center point
        Point3D Pcij = Pc.AddVectorToPoint(vRight.multiplySkalar(((i-((Nx+1)/2)))*Rx).subtractVector(vUp.multiplySkalar(((j-((Ny+1)/2)))*Ry)));

        //Return the requested ray
        return new Ray(p0,Pcij.subtractPoint(p0));
	}
	
	public String toString()
	{
	return "Vto:    " + vTo.tostring() + "\n" + "Vup:    " + vUp.tostring() + "\n" + "Vright: " + vRight.tostring() + ".";
	}
	
}
