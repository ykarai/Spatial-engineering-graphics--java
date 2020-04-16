package unittests;

import elements.*;
import org.junit.Test;

import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;


public class RenderTest {
	@Test
	public void basicRendering(){
		//Scene
	    Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0,0,0), new Vector(0,1,0), new Vector(0,0,-1)));
		scene.setDistance(150);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientLight(new AmbientLight());
		Geometries geometries = new Geometries();
        scene.setGeomtries(geometries);


        //Sphere1
        Sphere sphere1 = new Sphere(
                new Point3D(0, 0, -150), 50,
                new Color(new java.awt.Color(179, 0, 18)));
        sphere1.setMaterial(new Material( 0.8,1,10));

        //Triangle1
        Triangle triangle1 = new Triangle(
                new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149),
                new Color(new java.awt.Color(46, 155, 46)));
        triangle1.setMaterial(new Material( 0.8,1,10));

        //Triangle2
        Triangle triangle2 = new Triangle(
                new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149),
                new Color(new java.awt.Color(175, 182, 7)));
        triangle2.setMaterial(new Material( 0.8,1,10));

        //Triangle3
        Triangle triangle3 = new Triangle(
                new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149),
                new Color(new java.awt.Color(50, 0, 182)));
        triangle3.setMaterial(new Material(0.8 ,1,10));

        //Triangle4
        Triangle triangle4 = new Triangle(
                new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149),
                new Color(new java.awt.Color(158, 0, 151)));
        triangle4.setMaterial(new Material(0.8 ,1,10));

        //Add To Scene
        scene.addGeometry(sphere1);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        //Render Image
        ImageWriter imageWriter = new ImageWriter("RenderTest",501, 501, 500, 500);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.getImageWriter().printGrid(50);
		render.getImageWriter().writeToimage();
		System.out.println(imageWriter.PROJECT_PATH);
	}
}