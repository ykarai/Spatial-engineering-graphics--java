package unittests;

import org.junit.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class ReflectionTest {
    @Test
    public void test() {
        //Scene
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(15.0, 0.0 , 60), new Vector(0.0, 1.0, 0.0), new Vector(0.0, 0.0, -1.0)));
        scene.setDistance(350);
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
                new Point3D(80, 0, -300), 45,
                new Color(new java.awt.Color(79, 79, 79)));
        sphere3.setMaterial(new Material(1,1,10));
        //Sphere4
        Sphere sphere4 = new Sphere(
                new Point3D(89, -24, -260), 45,
                new Color(new java.awt.Color(0, 0, 0)));
        sphere4.setMaterial(new Material(0,0,0));

        //endregion

        //region Mirror
        //Triangle1
        Triangle triangle1 = new Triangle(
                new Point3D(70,100,-100),  //A     A |\
                new Point3D(-30,100,-100), //B       | \
                new Point3D(-30,-100,-100),//C      B ---C
                new Color(new java.awt.Color(42, 41, 70)));
        triangle1.setMaterial(new Material(0.4 ,0,10,0.4,0.4));
        //Triangle2
        Triangle triangle2 = new Triangle(
                new Point3D(70,100,-100),  //A    A___D
                new Point3D(-30,-100,-100),//C     \ |
                new Point3D(70,-100,-100), //D      \|C
                new Color(new java.awt.Color(42, 41, 70)));
        triangle2.setMaterial(new Material(0.4 ,0,10,0.4,0.4));
        //endregion

        //region Plane
        //Plane1
        Plane plane1 = new Plane(
                new Point3D(-30,0,0),
                new Vector(50,0,0),
                new Color(new java.awt.Color(28, 75, 14)));
        plane1.setMaterial(new Material(0.8 ,0.4,10,0,0.6));
        //endregion

        //region Lights
        //Light Sources
        PointLight pointLight1 = new PointLight(
                new Point3D(-15 ,0, -40),
                new Color(new java.awt.Color(255, 252, 145)),
                20,0.1, 0.01 ,0.001);
        PointLight pointLight2 = new PointLight(
                new Point3D(80, 65, -225),
                new Color(new java.awt.Color(255,255,255)),
                0.1, 0.01 ,0.01);
        DirectionalLight directionLight = new DirectionalLight(
                new Color(new java.awt.Color(255, 251, 0)),
                new Vector(50,0, -15));
        //endregion


        //Add To Scene
        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(plane1);

        scene.getLights().add(pointLight1);
        scene.getLights().add(pointLight2);
        scene.getLights().add(directionLight);


        //Render Image
        ImageWriter imageWriter = new ImageWriter("ReflectionTest",601, 601, 600, 600);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
//        render.getImageWriter().printGrid(50);
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }

}