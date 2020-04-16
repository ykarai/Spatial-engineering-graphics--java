package unittests;

import org.junit.Test;


public class IntegralTest {

    final int numOfRays = 100;

    @Test
    public void linerGrowth() {

        double sum = 0;
        double lightPercent = 0;
        for (int i = 0; i < numOfRays; i++) {
            lightPercent = (double)(2*i+1)/(Math.pow(numOfRays,2));
            sum+=lightPercent;
            System.out.println("Ray " + (i+1) + ": " + lightPercent);
        }
        System.out.println("Over all: " + sum);
        System.out.println("------------------------------------------------------------------------");
    }

    @Test
    public void parabolicGrowth() {

        double sum = 0;
        double lightPercent = 0;
        for (int i = 0; i < numOfRays; i++) {
            lightPercent = (double)( 3*Math.pow(i,2)+3*i+1 )/( Math.pow(numOfRays,3) );
            sum+=lightPercent;
            System.out.println("Ray " + (i+1) + ": " + lightPercent);
        }
        System.out.println("Over all: " + sum);
        System.out.println("------------------------------------------------------------------------");
    }

    @Test
    public void exponentialGrowth() {

        double e = 2.7182818284590452353602874713527;
        double sum = 0;
        double lightPercent = 0;
        for (int i = 0; i < numOfRays; i++) {
            lightPercent = (double)( Math.pow(e,i)*(e-1) )/( Math.pow(e,numOfRays)-1 );
            sum+=lightPercent;
            System.out.println("Ray " + (i+1) + ": " + lightPercent);
        }
        System.out.println("Over all: " + sum);
        System.out.println("------------------------------------------------------------------------");
    }

}