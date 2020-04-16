package elements;

import primitives.Color;

/**
 * class represent AmbientLight in scene
 */
public class AmbientLight extends Light {

	float Ka = 1;

	// ****** Constructors ********** //

    /**
     * Construct new ambient light with zeros
     */
    public AmbientLight()
    {
        this.color = new Color(0,0,0);
    }

    /** Construct new ambient light with color
     *
     * @param c Color of the ambient light
     * @param ka Scale factor to the color
     */
    public AmbientLight(Color c, float ka) {
        this.color = new Color(c);
        this.Ka = ka;
    }


    /** Return color-intensity of the Ambient liight
     *
     * @return Color-intensity of the Ambient liight
     */
	 public Color getIntensity() {
         return new Color((int) (this.color.getColor().getRed() * Ka),
                 (int) (this.color.getColor().getGreen() * Ka),
                 (int) (this.color.getColor().getBlue() * Ka));
     }



}
