package unittests;

import org.junit.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import java.util.ArrayList;
import java.util.List;

public class RefractionTest {
    @Test
    public void test() {
        //Scene
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(15, 0 , 120), new Vector(0, 1, 0), new Vector(0, 0, -1)));
        scene.setDistance(400);
        scene.setBackground(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight());
        Geometries geometries = new Geometries();
        scene.setGeomtries(geometries);

        //region Spheres
        //Sphere1
        Sphere sphere1 = new Sphere(
                new Point3D(-20, 30, -40), 10,
                new Color(new java.awt.Color(108, 0, 21)));
        sphere1.setMaterial(new Material(0.8 ,0.8,10));
        //Sphere2
        Sphere sphere2 = new Sphere(
                new Point3D(-20, -30, -40), 10,
                new Color(new java.awt.Color(0, 9, 108)));
        sphere2.setMaterial(new Material(0.8 ,0.8,10));
        //Sphere3
        Sphere sphere3 = new Sphere(
                new Point3D(-30,0,-95), 100,
                new Color(new java.awt.Color(42, 41, 70)));
        sphere3.setMaterial(new Material(0.4 ,1,10,0,0.8));
        //Sphere4
        Sphere sphere4 = new Sphere(
                new Point3D(-30,-30,40), 7,
                new Color(new java.awt.Color(73, 16, 23)));
        sphere4.setMaterial(new Material(0.2 ,1,20,0,0.8));
        //Sphere5
        Sphere sphere5 = new Sphere(
                new Point3D(-30,30,40), 7,
                new Color(new java.awt.Color(68, 69, 30)));
        sphere5.setMaterial(new Material(0.2 ,1,20,0,0.8));
        //endregion

        //region Plane
        //Plane1
        Plane plane1 = new Plane(
                new Point3D(-30,0,0),
                new Vector(50,0,0),
                new Color(new java.awt.Color(28, 75, 14)));
        plane1.setMaterial(new Material(0.8  ,0.4,20));
        //endregion

        //region Light Sources
        //Light Sources
        PointLight pointLight = new PointLight(
                new Point3D(-15 ,0, -40),
                new Color(new java.awt.Color(255, 252, 145)),
                10,0.1, 0.01 ,0.001);
        PointLight pointLight1 = new PointLight(
                new Point3D(-19,-30,40),
                new Color(new java.awt.Color(23, 250, 255)),
                2,1.3, 0.01 ,0.01);
        PointLight pointLight2 = new PointLight(
                new Point3D(-19,30,40),
                new Color(new java.awt.Color(31, 77, 255)),
                2,1.3, 0.01 ,0.01);
        //endregion

        //Add To Scene
        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        scene.addGeometry(sphere5);
        scene.addGeometry(plane1);
        scene.getLights().add(pointLight);
        scene.getLights().add(pointLight1);
        scene.getLights().add(pointLight2);


        //Render Image
        ImageWriter imageWriter = new ImageWriter("RefractionTest",601, 601, 600, 600);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
//        render.getImageWriter().printGrid(50);
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }

}