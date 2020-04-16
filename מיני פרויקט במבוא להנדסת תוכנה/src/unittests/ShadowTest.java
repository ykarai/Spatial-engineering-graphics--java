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


public class ShadowTest {
    @Test
    public void test() {
        //Scene
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0.0, 0.0 , 60), new Vector(0.0, 1.0, 0.0), new Vector(0.0, 0.0, -1.0)));
        scene.setDistance(350);
        scene.setBackground(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight());
        Geometries geometries = new Geometries();
        scene.setGeomtries(geometries);

        //Sphere1
        Sphere sphere1 = new Sphere(
                new Point3D(-20, 30, -30), 10,
                new Color(new java.awt.Color(108, 0, 21)));
        sphere1.setMaterial(new Material(0.8 ,0.8,10));
        //Sphere2
        Sphere sphere2 = new Sphere(
                new Point3D(-20, -30, -30), 10,
                new Color(new java.awt.Color(0, 9, 108)));
        sphere2.setMaterial(new Material(0.8 ,0.8,10));

        //Plane1
        Plane plane1 = new Plane(
                new Point3D(-30,0,0),
                new Vector(50,0,0),
                new Color(new java.awt.Color(28, 75, 14)));
        plane1.setMaterial(new Material(0.7 ,0.7,10,0.4,0.4));

        //Light Sources
        List<LightSource> lights = new ArrayList<LightSource>();
        PointLight pointLight = new PointLight(
                new Point3D(-15 ,0, -30),
                new Color(new java.awt.Color(255, 252, 145)),
                15,0.1, 0.01 ,0.001);
        DirectionalLight directionLight = new DirectionalLight(
                new Color(new java.awt.Color(255, 251, 0)),
                new Vector(50,0, -15));

        //Add To Scene
        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(plane1);
        scene.getLights().add(pointLight);
        scene.getLights().add(directionLight);


        //Render Image
        ImageWriter imageWriter = new ImageWriter("ShadowTest",501, 501, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
//        render.getImageWriter().printGrid(50);
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }

}