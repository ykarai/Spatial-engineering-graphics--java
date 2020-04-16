package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;



public class SpiralTest{
    @Test
    public void basicTest() {

        Vector N = new Vector(0, 4, 3);  //Normal to spiral's surface
        Point3D Q0 = new Point3D(2, 2, 3); //Center point of the spiral
        double spiralGrowth = 0.25;
        double lightRadius = 20;
        //floor(lightRadius/spiralGrowth) + 1 - result is how much points will be generated

        Vector vQ0 = new Vector(Q0.getCoordX(), Q0.getCoordY(), Q0.getCoordZ());
        Plane spiralSurface = new Plane(Q0,N);
        Point3D surfacePoint = spiralSurface.findPointOnPlane();

        //Vectors of Spiral's surface
        Vector v = surfacePoint.subtractPoint(Q0).normalize();
        Vector u = v.crossProduct(N).normalize();

        //Each iteration - find next spiral's point
        int t = 0;
        while (true) {
            double spiralXcomponent = spiralGrowth*t*Math.cos(t);
            double spiralYcomponent = spiralGrowth*t*Math.sin(t);

            double distance = Math.sqrt(spiralXcomponent*spiralXcomponent + spiralYcomponent*spiralYcomponent)-0.000000000000004;
            if (distance > lightRadius)
                break;

            Vector spiralPoint = vQ0.addVector(
                    u.multiplySkalar(spiralXcomponent).addVector(v.multiplySkalar(spiralYcomponent)));
            System.out.println(t+1+": " + spiralPoint.tostring());

            t += 1;
        }
        int raysFormula = (int)(lightRadius/spiralGrowth)+1;
        System.out.println("(lightRadius/spiralGrowth)+1: " + raysFormula);
    }
}