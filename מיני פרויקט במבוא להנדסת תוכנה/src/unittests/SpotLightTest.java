package unittests;

import org.junit.Test;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import elements.*;
import java.util.ArrayList;
import java.util.List;

public class SpotLightTest {
    @Test
    public void test() {
        //Scene
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0.0, 0.0 , 0.0), new Vector(0.0, 1.0, 0.0), new Vector(0.0, 0.0, -1.0)));
        scene.setDistance(350);
        scene.setBackground(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight());
        Geometries geometries = new Geometries();
        scene.setGeomtries(geometries);

        //Sphere1
        Sphere sphere1 = new Sphere(
                new Point3D(0, 0, -150), 50,
                new Color(new java.awt.Color(108, 0, 13)));
        sphere1.setMaterial(new Material(0.8 ,0.8,10));

        //Light Sources
        SpotLight spotLight = new SpotLight(
                new Point3D(-20 ,50, -110),
                new Vector(0,-50,-30),
                new Color(new java.awt.Color(255, 252, 18)),
                0.1, 0.01 ,0.001);


        //Add To Scene
        scene.addGeometry(sphere1);
        scene.getLights().add(spotLight);



        //Render Image
        ImageWriter imageWriter = new ImageWriter("SpotLight",501, 501, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        //render.getImageWriter().printGrid(50);
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }

}