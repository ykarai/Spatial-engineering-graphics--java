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


public class AllLightsTest {
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
        Sphere sphere1 = new Sphere(new Point3D(0, 0, -150), 50, new Color(new java.awt.Color(108, 0, 13)));
        sphere1.setMaterial(new Material(0.8 ,0.8,10));


        //Light Sources
        List<LightSource> lights = new ArrayList<LightSource>();
        DirectionalLight directionLight = new DirectionalLight(new Color(new java.awt.Color(255, 254, 63)),
                new Vector(50,50, 10));
        PointLight pointLight = new PointLight(new Point3D(40 ,50, -80),
                new Color(new java.awt.Color(255, 10, 30)), 0.1, 0.01 ,0.001);
        SpotLight spotLight = new SpotLight(new Point3D(-20 ,50, -80), new Vector(0,-50,-30),
                new Color(new java.awt.Color(0, 255, 254)), 0.1, 0.01 ,0.001);


        //Add To Scene
        scene.addGeometry(sphere1);

        scene.getLights().add(directionLight);
        scene.getLights().add(pointLight);
        scene.getLights().add(spotLight);


        //Render Image
        ImageWriter imageWriter = new ImageWriter("AllLights",501, 501, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }

}