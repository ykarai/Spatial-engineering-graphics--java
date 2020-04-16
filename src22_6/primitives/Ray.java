	package primitives;
/**
 * class for represent Ray in 3D model using Point3D and Vector
 */
public class Ray {

	private  Point3D POO ;
	private  Vector  direction ;
	
	/************Constructors**********/
	public Ray(double x, double y,double z,
			   double xd, double yd,double zd) {
		
		POO= new Point3D(x,y,z);
		direction=new Vector(xd ,yd ,zd).normalize();
	}

    public Ray (Point3D p, Vector v)
    {
        POO = p;
        direction = v.normalize();
    }

    public Ray(Ray other) {
		
    	POO=other.POO;
    	direction=other.direction;
    	
	}

    /************* Getters **************/
	public Point3D  getPOO(){
        return this.POO;
    }	
	public Vector get_direction(){
        return this.direction;
    }
	

	/************ Administration *********/
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Ray))
			return false;
		
		Ray other = (Ray)obj;
		if(other.POO.equals(this.POO)&&
	   	   other.direction.equals(this.direction))
				return true;
		else
			return false;
	}
	
	public String tostring(){
		return "Ray:  POO "+POO.tostring()+"    "+direction.tostring();
	}
	
    
    
    
    
    
}
