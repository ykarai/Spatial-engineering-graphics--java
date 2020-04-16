package unittests;

import elements.*;
import org.junit.Test;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class Scene1Test {
    @Test
    public void basicRendering(){
        //Scene
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0,0,60), new Vector(0,1,0), new Vector(0,0,-1)));
        scene.setDistance(350);
        scene.setBackground(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight());
        Geometries geometries = new Geometries();
        scene.setGeomtries(geometries);

        //region Spheres
        //Sphere1
        Sphere sphere1 = new Sphere(
                new Point3D(0, 0, -160), 50,
                new Color(new java.awt.Color(108, 0, 13)));
        sphere1.setMaterial(new Material(0.35 ,0,20,0.7,0));
        //Sphere2
        Sphere sphere2 = new Sphere(
                new Point3D(-37, 0, -50), 13,
                new Color(new java.awt.Color(20, 33, 119)));
        sphere2.setMaterial(new Material(0.8 ,0.8,10));
        //Sphere3
        Sphere sphere3 = new Sphere(
                new Point3D(-30, -50, -80), 20,
                new Color(new java.awt.Color(84, 15, 87)));
        sphere3.setMaterial(new Material(0.8 ,0.7,10));
        //endregion

        //Plane
        Plane plane = new Plane(
                new Point3D(-50,0,0),
                new Vector(-50,0,0),
                new Color(new java.awt.Color(28, 75, 14)));
        plane.setMaterial(new Material(0.75 ,0,10,0,1));

        //Light Sources
        PointLight pointLight1 = new PointLight(
                new Point3D(-20 ,50, -80),
                new Color(new java.awt.Color(33, 245, 255)),
                20,0.1, 0.01 ,0.001);
        PointLight pointLight2 = new PointLight(
                new Point3D(-52, -40, -30),
                new Color(new java.awt.Color(255, 250, 8)),
                0.3, 0.01 ,0.01);


        //Add To Scene
        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(plane);
        scene.getLights().add(pointLight1);
        scene.getLights().add(pointLight2);


        //Render Image
        ImageWriter imageWriter = new ImageWriter("Scene1",501, 501, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
//        render.getImageWriter().printGrid(50);
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }
}
