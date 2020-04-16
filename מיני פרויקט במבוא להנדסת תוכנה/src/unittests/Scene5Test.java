package unittests;

import elements.*;
        import org.junit.Test;
        import geometries.*;
        import primitives.*;
        import renderer.ImageWriter;
        import renderer.Render;
        import scene.Scene;

public class Scene5Test {
    @Test
    public void basicRendering(){
        //Scene
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(30,-45,50), new Vector(0,10,2), new Vector(-3,2,-10)));
        scene.setDistance(350);
        scene.setBackground(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight());
        Geometries geometries = new Geometries();
        scene.setGeomtries(geometries);


        //Sphere1
        Sphere sphere1 = new Sphere(
                new Point3D(-39, -10, -40), 11,
                new Color(new java.awt.Color(29, 59, 16)));
        sphere1.setMaterial(new Material(0.8 ,0.6,40));

        //region Pyramid
        //Front Triangle
        Triangle triangle1 = new Triangle(
                new Point3D(-5, -35, -85), //A     A |\
                new Point3D(-50, -25, -75), //B       | \
                new Point3D(-50, -45, -75), //C     B ---C
                new Color(new java.awt.Color(37, 96, 101)));
        triangle1.setMaterial(new Material(0.8 ,0.8,20));
        //Back Triangle
        Triangle triangle2 = new Triangle(
                new Point3D(-5, -35, -85), //A     A |\
                new Point3D(-50, -25, -95), //D       | \
                new Point3D(-50, -45, -95), //E     D ---E
                new Color(new java.awt.Color(37, 96, 101)));
        triangle2.setMaterial(new Material(0.8 ,0.8,20));
        //Left Triangle
        Triangle triangle3 = new Triangle(
                new Point3D(-5, -35, -85), //A
                new Point3D(-50, -25, -75),
                new Point3D(-50, -25, -95),
                new Color(new java.awt.Color(37, 96, 101)));
        triangle3.setMaterial(new Material(0.8 ,0.8,20));
        //Right Triangle
        Triangle triangle4 = new Triangle(
                new Point3D(-5, -35, -85), //A
                new Point3D(-50, -45, -75),
                new Point3D(-50, -45, -95),
                new Color(new java.awt.Color(37, 96, 101)));
        triangle4.setMaterial(new Material(0.8 ,0.8,20));
        //endregion

        //region Cube
        //Upper side
        Triangle triangle5 = new Triangle(
                new Point3D(-26, 25, -110), //E     E |\
                new Point3D(-26, 25, -80),  //F       | \
                new Point3D(-26,-5, -80),  //G     F ---G
                new Color(new java.awt.Color(108, 0, 2)));
        triangle5.setMaterial(new Material(0.8 ,0.8,20));
        //Upper side
        Triangle triangle6 = new Triangle(
                new Point3D(-26, 25, -110), //E    E ___H
                new Point3D(-26, -5, -110),//H      \ |
                new Point3D(-26, -5, -80), //G       \|G
                new Color(new java.awt.Color(108, 0, 2)));
        triangle6.setMaterial(new Material(0.8 ,0.8,20));

        //Front side
        Triangle triangle7 = new Triangle(
                new Point3D(-26, 25, -80),  //F
                new Point3D(-26,-5, -80),  //G
                new Point3D(-50,-5, -80),
                new Color(new java.awt.Color(108, 0, 2)));
        triangle7.setMaterial(new Material(0.8 ,0.8,20));
        //Front side
        Triangle triangle8 = new Triangle(
                new Point3D(-26, 25, -80),  //F
                new Point3D(-50, 25, -80),
                new Point3D(-50,-5, -80),
                new Color(new java.awt.Color(108, 0, 2)));
        triangle8.setMaterial(new Material(0.8 ,0.8,20));

        //Right side
        Triangle triangle9 = new Triangle(
                new Point3D(-26, -5, -110),//H
                new Point3D(-26, -5, -80), //G
                new Point3D(-50, -5, -110),
                new Color(new java.awt.Color(108, 0, 2)));
        triangle9.setMaterial(new Material(0.8 ,0.8,20));
        //Right side
        Triangle triangle10= new Triangle(
                new Point3D(-26, -5, -80), //G
                new Point3D(-50, -5, -110),
                new Point3D(-50, -5, -80),
                new Color(new java.awt.Color(108, 0, 2)));
        triangle10.setMaterial(new Material(0.8 ,0.8,20));
        //endregion

        //Plane
        Plane plane = new Plane(
                new Point3D(-50,0,0),
                new Vector(-50,0,0),
                new Color(new java.awt.Color(9, 33, 61)));
        plane.setMaterial(new Material(0.6 ,0.7,30));

        //Light Sources
        PointLight pointLight1 = new PointLight(
                new Point3D(20, -100, -65),
                new Color(new java.awt.Color(249, 255, 15)),
                30,0.01, 0.01 ,0.001);




        //Add To Scene
        scene.addGeometry(sphere1);
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
        scene.addGeometry(plane);
        scene.getLights().add(pointLight1);


        //Render Image
        ImageWriter imageWriter = new ImageWriter("Scene5",501, 501, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
//        render.getImageWriter().printGrid(50);
        render.getImageWriter().writeToimage();
        System.out.println(imageWriter.PROJECT_PATH);
    }
}
