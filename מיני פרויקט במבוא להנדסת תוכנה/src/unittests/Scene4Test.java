package unittests;

import elements.*;
import org.junit.Test;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import java.util.ArrayList;
import java.util.List;

public class Scene4Test {
    @Test
    public void basicRendering(){
        //Scene
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0,0,70), new Vector(0,1,0), new Vector(0,0,-1)));
        scene.setDistance(350);
        scene.setBackground(new Color(0,0,0));
        scene.setAmbientLight(new AmbientLight());
        Geometries geometries = new Geometries();
        scene.setGeomtries(geometries);


        //region Spheres
        //Sphere1
        Sphere sphere1 = new Sphere(
                new Point3D(-30, 0, -135), 20,
                new Color(new java.awt.Color(108, 0, 13)));
        sphere1.setMaterial(new Material(0.8 ,0.8,20));
        //Sphere2
        Sphere sphere2 = new Sphere(
                new Point3D(-37, 52, -115), 13,
                new Color(new java.awt.Color(20, 33, 119)));
        sphere2.setMaterial(new Material(0.8 ,0.8,20));
        //Sphere3
        Sphere sphere3 = new Sphere(
                new Point3D(-30, -39, -100), 20,
                new Color(new java.awt.Color(84, 15, 87)));
        sphere3.setMaterial(new Material(0.8 ,0.8,20));
        //Sphere4
        Sphere sphere4 = new Sphere(
                new Point3D(-40, 30, -75), 10,
                new Color(new java.awt.Color(63, 61, 17)));
        sphere4.setMaterial(new Material(0.6/**/ ,1,20));
        //endregion

        //region Surface
        Plane plane = new Plane(
                new Point3D(-50,0,0),
                new Vector(-50,0,0),
                new Color(new java.awt.Color(18, 62, 18)));
        plane.setMaterial(new Material(0.8 ,0.8,10,0,1));
        //endregion

        //region Pyramid
        //Front Triangle
        Triangle triangle1 = new Triangle(
                new Point3D(-50,80,-50),
                new Point3D(-50,-80,-50),
                new Point3D(60,0,-125),
                new Color(new java.awt.Color(36, 42, 62)));
        triangle1.setMaterial(new Material(0.7 ,0.8,10, 0, 0.9));
        //Back Triangle
        Triangle triangle2 = new Triangle(
                new Point3D(-50,80,-200),
                new Point3D(-50,-80,-200),
                new Point3D(60,0,-125),
                new Color(new java.awt.Color(36, 42, 62)));
        triangle2.setMaterial(new Material(0.7 ,0.8,10, 0, 0));
        //Right Triangle
        Triangle triangle3 = new Triangle(
                new Point3D(-50,-80,-50),
                new Point3D(-50,-80,-200),
                new Point3D(60,0,-125),
                new Color(new java.awt.Color(36, 42, 62)));
        triangle3.setMaterial(new Material(0.7 ,0.8,10, 0.6, 0));
        //Left Triangle
        Triangle triangle4 = new Triangle(
                new Point3D(-50,80,-200),
                new Point3D(-50,80,-50),
                new Point3D(60,0,-125),
                new Color(new java.awt.Color(36, 42, 62)));
        triangle4.setMaterial(new Material(0.7 ,0.8,10, 0.6, 0));
        //endregion

        //region Lights
        //Light Sources
        PointLight pointLight1 = new PointLight(
                new Point3D(30 ,0, -125),
                new Color(new java.awt.Color(255, 254, 63)),
                25,0.1, 0.01 ,0.001);
        PointLight pointLight2 = new PointLight(
                new Point3D(-52, 0, -50),
                new Color(new java.awt.Color(112, 255, 50)),
                10,0.1, 0.004 ,0.1);
        //endregion


        //Add To Scene
        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        scene.addGeometry(plane);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        scene.getLights().add(pointLight1);
        scene.getLights().add(pointLight2);

        ImageWriter imageWriter = new ImageWriter("Scene4" ,601, 601, 600, 600);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
//        render.getImageWriter().printGrid(50);
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }
}
