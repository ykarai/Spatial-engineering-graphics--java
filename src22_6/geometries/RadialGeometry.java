package geometries;

/** abstract class represent Geometric shapes using primitives
 * extends Geometry class with _radius
 */
public abstract class RadialGeometry extends Geometry {

	 private double _radius;
	
	 /************Constructors**********/
	public RadialGeometry(double radius) {
		super();
		this._radius=radius;
	}

	//Copy Constructor
	public RadialGeometry(RadialGeometry other) {
		super();
		this._radius=other._radius;
		
	}
	/************ Getter ***********/
	public double get_radius(){
        return this._radius;
    }	


}
