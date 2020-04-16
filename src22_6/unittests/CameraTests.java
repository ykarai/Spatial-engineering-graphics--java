package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import primitives.*;
import elements.*;

public class CameraTests {

	/*** Camera test ***/
	@Test
	public void testRaysConstruction() {
		final int WIDTH = 3;
		final int HEIGHT = 3;
		Point3D[][] screen = new Point3D[HEIGHT+1][WIDTH+1];
		Camera camera = new Camera(new Point3D(0.0, 0.0, 0.0), new Vector(0.0, 1.0, 0.0), new Vector(0.0, 0.0, -1.0));
		System.out.println("Camera:\n" + camera);
		for (int i = 1; i <= HEIGHT; i++) {
			for (int j = 1; j <= WIDTH; j++) {
				Ray ray = camera.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
				screen[i][j] = ray.get_direction().get_head();
				System.out.print(screen[i][j].tostring());
								
				assertFalse("++Different",screen[i][j].getCoordZ() != -1.0);
				
				// Checking all options
				double x = screen[i][j].getCoordX();
				double y = screen[i][j].getCoordY();
				if (Double.compare(x, 3) == 0 || Double.compare(x, 0) == 0 || Double.compare(x, -3) == 0) {
					if (Double.compare(y, 3) == 0 || Double.compare(y, 0) == 0 || Double.compare(y, -3) == 0) {
						assertTrue(true);
					} else
						fail("Wrong y coordinate");
				} else
					fail("Wrong x coordinate");
			}
			System.out.println("");
		}
	}

	
}
