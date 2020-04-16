package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Cylinder;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class NormalTests {

	// @Test
	// public void test() {
	// fail("Not yet implemented");
	// }

	@Test
	public void test_plane() {

		Vector normal = new Vector(0, 0, 1);
		Point3D point = new Point3D(0, 0, 0);
		Plane plane = new Plane(point, normal,new Color(111,111, 111));

		Vector vector0 = new Vector(0, 0, 1);
		assertEquals(vector0, plane.get_normal());

	}

	@Test
	public void test_triangle() {

		Point3D p1 = new Point3D(0, 4, 3);
		Point3D p2 = new Point3D(4, 0, 3);
		Point3D p3 = new Point3D(-4, 0, 3);

		Triangle triangle = new Triangle(p1, p2, p3,new Color(111,111, 111));
		Vector normal = new Vector(0, 0, 1);

		assertEquals(normal, triangle.get_normal());

	}
	
	@Test
	public void test_sphere() {

		Point3D ps =new Point3D(0,3,0);
		Point3D ps2 =new Point3D(0,1,0);
		Sphere sphere=new Sphere(ps, 2,new Color(111,111, 111));
		Vector normal = new Vector(0, -1, 0);
		
		assertEquals(normal, sphere.getNormal(ps2));

	}
	
	@Test
	public void test_Cylinder() {

		Point3D pc =new Point3D(1,0,0);
		
		Ray ray=new Ray(0, 0, 0, 0, 1, 0);
		Cylinder cylinder = new Cylinder(1, ray, 1,new Color(111,111, 111));	
		Vector normal = new Vector(1, 0, 0);
		
		assertEquals(normal, cylinder.getNormal(pc));

	}

	
	
	
	
}
