package unittests;

import elements.*;
//import javafx.scene.shape.TriangleMesh;
import org.junit.Test;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import java.util.ArrayList;
import java.util.List;

public class Scene3Test {
    @Test
    public void basicRendering(){
        //Scene
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(15,0,70), new Vector(0,1,0), new Vector(0,0,-1)));
        scene.setDistance(350);
        scene.setBackground(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight());
        Geometries geometries = new Geometries();
        scene.setGeomtries(geometries);

        //region Spheres
        //Sphere1
        Sphere sphere1 = new Sphere(
                new Point3D(0, 0, -150), 50,
                new Color(new java.awt.Color(108, 0, 13)));
        sphere1.setMaterial(new Material( 0.8,1,20));
        //Sphere2
        Sphere sphere2 = new Sphere(
                new Point3D(-40, 0, -60), 10,
                new Color(new java.awt.Color(20, 33, 119)));
        sphere2.setMaterial(new Material(0.8 ,0.8,10));
        //Sphere3
        Sphere sphere3 = new Sphere(
                new Point3D(-30, -50, -80), 20,
                new Color(new java.awt.Color(84, 15, 87)));
        sphere3.setMaterial(new Material(1 ,2,20));
        //Sphere4
        Sphere sphere4 = new Sphere(
                new Point3D(-15, 65, -65), 15,
                new Color(new java.awt.Color(99, 97, 29)));
        sphere4.setMaterial(new Material(0.7 ,0.7,10));

        //endregion

        //region Floor
        //Triangle1
        Triangle triangle1 = new Triangle(
                new Point3D(-50,80,-200), //A     A |\
                new Point3D(-50,80,-50),  //B       | \
                new Point3D(-50,-80,-50), //C     B ---C
                new Color(new java.awt.Color(18, 62, 18)));
        triangle1.setMaterial(new Material(0.4 ,2,30));
        //Triangle2
        Triangle triangle2 = new Triangle(
                new Point3D(-50,80,-200), //A    A ___D
                new Point3D(-50,-80,-50), //C      \ |
                new Point3D(-50,-80,-200),//D       \|C
                new Color(new java.awt.Color(18, 62, 18)));
        triangle2.setMaterial(new Material(0.4 ,2,30));
        //endregion

        //region Cube
        //Upper side
        Triangle triangle3 = new Triangle(
                new Point3D(-30,75,-75),  //E     E |\
                new Point3D(-30,75,-55),  //F       | \
                new Point3D(-30,55,-55),  //G     F ---G
                new Color(new java.awt.Color(71, 0, 14)));
        triangle3.setMaterial(new Material(0.8 ,2,30));
        //Upper side
        Triangle triangle4 = new Triangle(
                new Point3D(-30,75,-75),  //E    E ___H
                new Point3D(-30,55,-75),  //H      \ |
                new Point3D(-30,55,-55),  //G       \|G
                new Color(new java.awt.Color(71, 0, 14)));
        triangle4.setMaterial(new Material(0.8 ,2,30));
        //Front side
        Triangle triangle7 = new Triangle(
                new Point3D(-30,75,-55),  //F
                new Point3D(-30,55,-55),  //G
                new Point3D(-50,55,-55),
                new Color(new java.awt.Color(71, 0, 14)));
        triangle7.setMaterial(new Material(1 ,2,30));
        //Front side
        Triangle triangle8 = new Triangle(
                new Point3D(-30,75,-55),  //F
                new Point3D(-50,55,-55),
                new Point3D(-50,75,-55),
                new Color(new java.awt.Color(71, 0, 14)));
        triangle8.setMaterial(new Material(1 ,2  ,30));
        //Right side
        Triangle triangle5 = new Triangle(
                new Point3D(-30,55,-55),  //G
                new Point3D(-30,55,-75), //H
                new Point3D(-50,55,-55),
                new Color(new java.awt.Color(71, 0, 14)));
        triangle5.setMaterial(new Material(0.8 ,2,30));
        //Right side
        Triangle triangle6 = new Triangle(
                new Point3D(-30,55,-75), //H
                new Point3D(-50,55,-75),
                new Point3D(-50,55,-55),
                new Color(new java.awt.Color(71, 0, 14)));
        triangle6.setMaterial(new Material(0.8 ,2,30));
        //Left side
        Triangle triangle13 = new Triangle(
                new Point3D(-30,75,-75),  //E
                new Point3D(-30,75,-55),  //F
                new Point3D(-50,75,-55),
                new Color(new java.awt.Color(71, 0, 14)));
        triangle13.setMaterial(new Material(0.8 ,2,30));
        //Left side
        Triangle triangle14 = new Triangle(
                new Point3D(-30,75,-75),  //F
                new Point3D(-50,75,-55),
                new Point3D(-50,75,-75),
                new Color(new java.awt.Color(71, 0, 14)));
        triangle14.setMaterial(new Material(0.8 ,2,30));
        //endregion

        //region Walls
        //Right Wall
        Triangle triangle9 = new Triangle(
                new Point3D(-50,-80,-200),  //A      /|A
                new Point3D(-50,-80,-50),   //B     / |
                new Point3D(80, -80,-50),  //E    E---B
                new Color(new java.awt.Color(36, 42, 62)));
        triangle9.setMaterial(new Material(0.4 ,0.2,10, 0.6 ,1));
        //Right Wall
        Triangle triangle10 = new Triangle(
                new Point3D(-50,-80,-200), //A    F---A
                new Point3D(80, -80,-200), //F    | /
                new Point3D(80, -80,-50),  //E   E|/
                new Color(new java.awt.Color(36, 42, 62)));
        triangle10.setMaterial(new Material(0.4 ,0.2,10, 0.6 ,1));

        //Left Wall
        Triangle triangle11 = new Triangle(
                new Point3D(-50,80,-200),  //A      /|A
                new Point3D(-50,80,-50),   //B     / |
                new Point3D(80, 80,-50),  //E    E---B
                new Color(new java.awt.Color(36, 42, 62)));
        triangle11.setMaterial(new Material(0.01 ,2,10, 0.6 ,0));
        //Left Wall
        Triangle triangle12= new Triangle(
                new Point3D(-50,80,-200), //A    F---A
                new Point3D(80, 80,-200), //F    | /
                new Point3D(80, 80,-50),  //E   E|/
                new Color(new java.awt.Color(36, 42, 62)));
        triangle12.setMaterial(new Material(0.01 ,2,10, 0.6,0));

        //Back Wall
        Triangle triangle15 = new Triangle(
                new Point3D(-50,80,-200), //A     E|\
                new Point3D(-50,-80,-200),//D      | \
                new Point3D(80,80,-200),  //E     A ---D
                new Color(new java.awt.Color(36, 42, 62)));
        triangle15.setMaterial(new Material(0 ,0,10,0,1));
        //Back Wall
        Triangle triangle16= new Triangle(
                new Point3D(80,80,-200), //E     E ___F
                new Point3D(80,-80,-200), //F      \ |
                new Point3D(-50,-80,-200),//D       \|D
                new Color(new java.awt.Color(36, 42, 62)));
        triangle16.setMaterial(new Material(0 ,0,10,0,1));
        //endregion

        //region Ceiling
        //Triangle17
        Triangle triangle17 = new Triangle(
                new Point3D(80,80,-200), //A     A |\
                new Point3D(80,80,-50),  //B       | \
                new Point3D(80,-80,-50), //C     B ---C
                new Color(new java.awt.Color(36, 42, 62)));
        triangle17.setMaterial(new Material(1 ,2,30,0,1));
        //Triangle18
        Triangle triangle18 = new Triangle(
                new Point3D(80,80,-200), //A    A ___D
                new Point3D(80,-80,-50), //C      \ |
                new Point3D(80,-80,-200),//D       \|C
                new Color(new java.awt.Color(36, 42, 62)));
        triangle18.setMaterial(new Material(1 ,2,30,0,1));
        //endregion

        //region Lights
        //DirectionalLight
        DirectionalLight directionLight = new DirectionalLight(
                new Color(new java.awt.Color(255, 254, 63)),
                new Vector(-50,50, 20));
        //SpotLight1
        SpotLight spotLight1 = new SpotLight(
                new Point3D(0 ,60, -105),
                new Vector(0,-80,0),
                new Color(new java.awt.Color(33, 245, 255)),
                20,0.1, 0.01 ,0.001);
        //SpotLight2
        SpotLight spotLight2 = new SpotLight(
                new Point3D(-5, 50, 0),
                new Vector(-30,10,-40),
                new Color(new java.awt.Color(0, 255, 176)),
                0.1, 0.1 ,0.001);
        //endregion

        //Add To Scene
        scene.addGeometry(sphere1);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);
        scene.addGeometry(sphere4);

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);
        scene.addGeometry(triangle5);
        scene.addGeometry(triangle6);
        scene.addGeometry(triangle7);
        scene.addGeometry(triangle8);
        scene.addGeometry(triangle9);
        scene.addGeometry(triangle10);
        scene.addGeometry(triangle11);
        scene.addGeometry(triangle12);
        scene.addGeometry(triangle13);
        scene.addGeometry(triangle14);
//        scene.addGeometry(triangle15);
//        scene.addGeometry(triangle16);
//        scene.addGeometry(triangle17);
//        scene.addGeometry(triangle18);

        List<LightSource> lights = new ArrayList<>();
        lights.add(directionLight);
        lights.add(spotLight1);
        lights.add(spotLight2);
        scene.setLights(lights);

        //Render Image
        ImageWriter imageWriter = new ImageWriter("Scene3",501, 501, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }
}
