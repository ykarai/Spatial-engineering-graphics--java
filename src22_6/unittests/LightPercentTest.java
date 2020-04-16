package unittests;

import org.junit.Test;

public class LightPercentTest {

    @Test
    public void linearDecay()
    {
        double sum = 0;
        int numOfRays = 50;
        for (int i = 0; i < numOfRays; i++)
        {
            double integral = (double)(-2*i-1)/(Math.pow(numOfRays,2)) + (double)2/numOfRays ;
            System.out.println("Ray number " + (i + 1) + ": " + integral);
            sum +=integral;
        }
        System.out.println("Sum: " + sum);
    }

    @Test
    public void exponentialDecay()
    {
        double e = 2.71828182845904523536;

        double sum = 0;
        int numOfRays = 50;
        for (int i = 0; i < numOfRays; i++)
        {
            double integral = Math.pow(e,-i) * (1-(double)1/e);
            System.out.println("Ray number " + (i + 1) + ": " + integral);
            sum +=integral;
        }
        System.out.println("Sum: " + sum);
    }

}