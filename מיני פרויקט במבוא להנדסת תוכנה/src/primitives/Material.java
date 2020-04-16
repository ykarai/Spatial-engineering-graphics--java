package primitives;

/**
 * Class represent the material of the shape
 */
public class Material {

	private double Kd = 1; //Diffuse factor
	private double Ks = 1 ; //Specular factor
	private int nShininess = 10;// Shininess of the material
    private double Kr = 0; // Reflection  factor
    private double Kt = 0; // Refraction factor

    // ************** Constructors **************** //

    //** Default Constructor */
    public Material() {}

	public Material(double d ,double s,int shininess) {

		this.Kd=d;
		this.Ks=s;
		this.nShininess=shininess;
	}

    public Material(double d, double s, int shininess, double kR, double kT) {

        this.Kd = d;
        this.Ks = s;
        this.nShininess = shininess;
        this.Kr = kR;
        this.Kt = kT;
    }
	
	// **************** Get/Set ******************** //
	public double getKd() { return Kd; }
	public void setKd(double kd) { Kd = kd; }

	public double getKs() { return Ks; }
	public void setKs(double ks) { Ks = ks; }

	public int getnShininess() { return nShininess; }
	public void setnShininess(int nShininess) { this.nShininess = nShininess; }

    public double getKr() { return Kr; }
    public void setKr(double kr) { Kr = kr; }

    public double getKt() { return Kt; }
    public void setKt(double kt) { Kt = kt; }

}
