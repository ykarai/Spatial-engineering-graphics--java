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

public class Scene2Test {
    @Test
    public void basicRendering(){
        //Scene
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(20,0,60), new Vector(0,1,0), new Vector(-20,0,-100)));
        scene.setDistance(350);
        scene.setBackground(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight());
        Geometries geometries = new Geometries();
        scene.setGeomtries(geometries);


        //region Spheres
        //Sphere1
        Sphere sphere1 = new Sphere(
                new Point3D(-30, 0, -132), 20,
                new Color(new java.awt.Color(108, 0, 13)));
        sphere1.setMaterial(new Material(0.8 ,0.8,10));
        //Sphere2
        Sphere sphere2 = new Sphere(
                new Point3D(-37, 50, -115), 13,
                new Color(new java.awt.Color(20, 33, 119)));
        sphere2.setMaterial(new Material(0.8 ,0.8,10));
        //Sphere3
        Sphere sphere3 = new Sphere(
                new Point3D(-25, -50, -115), 25,
                new Color(new java.awt.Color(84, 15, 87)));
        sphere3.setMaterial(new Material(0.8 ,0.8,10));
        //Sphere4
        Sphere sphere4 = new Sphere(
                new Point3D(-35, 60, -60), 15,
                new Color(new java.awt.Color(63, 61, 17)));
        sphere4.setMaterial(new Material(0.8 ,0.8,10,0,0.4
        ));
        //endregion

        //region Surface
        //Triangle1
        Triangle triangle1 = new Triangle(
                new Point3D(-50,80,-200), //A     A |\
                new Point3D(-50,80,-50),  //B       | \
                new Point3D(-50,-80,-50), //C     B ---C
                new Color(new java.awt.Color(18, 62, 18)));
        triangle1.setMaterial(new Material(0.8 ,0.8,10,0.6,0));
        //Triangle2
        Triangle triangle2 = new Triangle(
                new Point3D(-50,80,-200), //A    A ___D
                new Point3D(-50,-80,-50), //C      \ |
                new Point3D(-50,-80,-200),//D       \|C
                new Color(new java.awt.Color(18, 62, 18)));
        triangle2.setMaterial(new Material(0.8 ,0.8,10,0.6,0));
        //endregion

        //region Lights
        //Light Sources
        List<LightSource> lights = new ArrayList<LightSource>();

        DirectionalLight directionLight = new DirectionalLight(
                new Color(new java.awt.Color(255, 254, 63)),
                new Vector(0,50, 0));
        SpotLight spotLight1 = new SpotLight(
                new Point3D(30 ,0, -125),
                new Vector(-50,0,0),
                new Color(new java.awt.Color(33, 245, 255)),
                15,0.1, 0.01 ,0.001);
        SpotLight spotLight2 = new SpotLight(
                new Point3D(35, 130, -60),
                new Vector(-70,-70,0),
                new Color(new java.awt.Color(255, 0, 2)),
                10,0.1, 0.01 ,0.001);
        //endregion

        //Add To Scene
        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        scene.getLights().add(directionLight);
        scene.getLights().add(spotLight1);
        scene.getLights().add(spotLight2);


        //Render Image
        ImageWriter imageWriter = new ImageWriter("Scene2",501, 501, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
//        render.getImageWriter().printGrid(50);
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }
}
