package elements;

import primitives.Color;
import primitives.Point3D;

/**
 * Class represent a light
 */
public abstract class Light {

    //Color of light
    Color color;

    /** Function return color intensity of the light
     *
     * @return color intensity of the light
     */
    public abstract Color getIntensity() ;

}
