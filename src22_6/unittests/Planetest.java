package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import primitives.*;
import elements.*;
import geometries.*;

public class Planetest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testIntersectionPoints1() {
		final int WIDTH = 3;
		final int HEIGHT = 3;
		Ray[][] rays = new Ray[HEIGHT+1][WIDTH+1];
		Camera camera = new Camera(new Point3D(0.0, 0.0, 0.0), new Vector(0.0, 1.0, 0.0), new Vector(0.0, 0.0, -1.0));
		// plane orthogonal to the view plane
		Plane plane = new Plane(new Point3D(0.0, 0.0, -3.0), new Vector(0.0, 0.0, -1.0),new Color(111,111, 111));
		// 45 degrees to the view plane
		Plane plane2 = new Plane(new Point3D(0.0, 0.0, -3.0), new Vector(0.0, 0.25, -1.0),new Color(111,111, 111));
		List<Point3D> intersectionPointsPlane = new ArrayList<Point3D>();
		List<Point3D> intersectionPointsPlane2 = new ArrayList<Point3D>();
		System.out.println("Camera:\n" + camera);
		for (int i = 1; i <= HEIGHT; i++) {
			for (int j = 1; j <= WIDTH; j++) {
				rays[i][j] = camera.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
				//System.out.println(rays[i][j].tostring());
				
				List<Point3D> rayIntersectionPoints = plane.findIntersections(rays[i][j]);
				List<Point3D> rayIntersectionPoints2 = plane2.findIntersections(rays[i][j]);
				for (Point3D iPoint : rayIntersectionPoints)
					intersectionPointsPlane.add(iPoint);
				for (Point3D iPoint : rayIntersectionPoints2)
					intersectionPointsPlane2.add(iPoint);
			}
		}
		assertTrue(intersectionPointsPlane.size() == 9);
		assertTrue(intersectionPointsPlane2.size() == 9);
		for (Point3D iPoint : intersectionPointsPlane)
			System.out.println(iPoint.tostring());
		System.out.println("---");
		for (Point3D iPoint : intersectionPointsPlane2)
			System.out.println(iPoint.tostring());
	}

}


