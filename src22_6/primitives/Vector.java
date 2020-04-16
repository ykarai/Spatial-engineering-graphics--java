package primitives;
/** class for represent Vector in 3D model using Point3D
 * 
 *  
 */
public class Vector {

	
	
//	private final Point3D _start=new Point3D(0,0,0) ;
	private Point3D _head ;
	
	
	/************Constructors**********/
	public Vector(double x, double y,double z) {

		_head=new Point3D(x,y,z) ;
	}

	public Vector(Vector other) {
		
		_head=other._head;
	}
	/**********Getters*********/
	public Point3D get_head(){
        return this._head;
    }	
   //public void set_direct(Point3D _direct0){
   //     this._direct=_direct0;
   // }	
	
	
	/************Administration*********/
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Vector))
			return false;
		
		Vector other = (Vector)obj;
		if(other._head.equals(this._head))
				return true;
		else
			return false;
	}
	
	public String tostring(){
		return  "Vector: head "+_head.tostring()+"    "+"\n";
	}

	/************OperatorsAndFunctions*********/
    public Vector addVector(Vector other){
		
    	double x=this._head.getCoordX()+other._head.getCoordX();
    	double y=this._head.getCoordY()+other._head.getCoordY();
    	double z=this._head.getCoordZ()+other._head.getCoordZ();
    	
    	return new Vector(x,y,z);     			                	                   	     
    }
    public Vector subtractVector(Vector other){
		
	 double x=this._head.getCoordX()-other._head.getCoordX();
 	 double y=this._head.getCoordY()-other._head.getCoordY();
 	 double z=this._head.getCoordZ()-other._head.getCoordZ();
 	
 	 return new Vector(x,y,z);
    	
    	     			                	                   	     
   }
    public Vector multiplySkalar(double other){
	
    	 double x=this._head.getCoordX()*other;
     	 double y=this._head.getCoordY()*other;
     	 double z=this._head.getCoordZ()*other;
     	
     	 return new Vector(x,y,z);
	
	}
//  x1*x2 + y1*y2 + z1*z2
	public double dotProduct(Vector other){
	
		//double a=lengthVectors(this);
		//double b=lengthVectors(other);
		//double z=a*b*cosA
		
	    double x=this._head.getCoordX()*other._head.getCoordX();
		double y=this._head.getCoordY()*other._head.getCoordY();
		double z=this._head.getCoordZ()*other._head.getCoordZ();
		if((x+y+z)==(this.lengthVector()*other.lengthVector()))
		{
			String b ="vectors In the same direction";
		}
		if((x+y+z)==0)
		{
			String a ="vectors orthogonal";
		}
		if((x+y+z)<0)
		{
			String b ="vectors Obtuse angle";
		}
		if(!((x+y+z)<0) && !((x+y+z)==0))
		{
			String b ="vectors sharp angle";
		}
		return (x+y+z);
	 }

	 //   sqrt(x^2 * y^2 * +z^2)
	 public double lengthVector(){
	
		    double x=this._head.getCoordX()*this._head.getCoordX();
			double y=this._head.getCoordY()*this._head.getCoordY();
			double z=this._head.getCoordZ()*this._head.getCoordZ();;
			return Math.sqrt(x+y+z); 
	}
	 
	 
    //   v1 v2
	//  -------
    //x  a  e  
    //y  b  f   
	//z  c  g
	 
	public Vector crossProduct(Vector other){
	
		double a=this._head.getCoordX();
		double b=this._head.getCoordY();
		double c=this._head.getCoordZ();
		
		double e=other._head.getCoordX();
		double f=other._head.getCoordY();
		double g=other._head.getCoordZ();
		
		if(((b*g)-(f*c)-((a*g)-(e*c))+(a*f)-(e*b)==0))
		{
			String q ="vectors parallel";
		}
		 return new Vector((b*g)-(f*c),-((a*g)-(e*c)),(a*f)-(e*b));
		}

	    public Vector normalize(){
	
		  double vLength=this.lengthVector();
		  if((vLength!=0)){
	      return new Vector(this.get_head().getCoordX()/vLength,
	    		            this.get_head().getCoordY()/vLength,
	    		            this.get_head().getCoordZ()/vLength);
		  }
		  throw new IllegalArgumentException("Length of vector is 0");
		 
		}
		
	

}
