package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import primitives.*;
import elements.*;
import geometries.*;

public class Spheretest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testIntersectionPoints() {
		final int WIDTH = 3;
		final int HEIGHT = 3;
		Ray[][] rays = new Ray[HEIGHT+1][WIDTH+1];
		Camera camera = new Camera(new Point3D(0.0, 0.0, 0.0), new Vector(0.0, 1.0, 0.0), new Vector(0.0, 0.0, -1.0));
		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -3.0), 1,new Color(111,111, 111));
		Sphere sphere2 = new Sphere(new Point3D(0.0, 0.0, -3.0), 10,new Color(111,111, 111));
		// Only the center ray intersect the sphere in two locations
		List<Point3D> intersectionPointsSphere = new ArrayList<Point3D>();
		// The sphere encapsulates the view plane - all rays intersect with the
		// sphere once
		List<Point3D> intersectionPointsSphere2 = new ArrayList<Point3D>();
		System.out.println("Camera:\n" + camera);
		for (int i = 1; i <= HEIGHT; i++) {
			for (int j = 1; j <= WIDTH; j++) {
				rays[i][j] = camera.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
				List<Point3D> rayIntersectionPoints = sphere.findIntersections(rays[i][j]);
				List<Point3D> rayIntersectionPoints2 = sphere2.findIntersections(rays[i][j]);
//				for (Point3D iPoint : rayIntersectionPoints)
//					intersectionPointsSphere.add(iPoint);
//				for (Point3D iPoint : rayIntersectionPoints2)
//					intersectionPointsSphere2.add(iPoint);
//			}
//			assertTrue(intersectionPointsSphere.size() == 2);
//			assertTrue(intersectionPointsSphere2.size() == 9);
//			System.out.println("Intersection Points:");
//			for (Point3D iPoint : intersectionPointsSphere) {
//				assertTrue(iPoint.equals(new Point3D(0.0, 0.0, -2.0)) || iPoint.equals(new Point3D(0.0, 0.0, -4.0)));
//				System.out.println(iPoint);
			}
		}

	}
}
