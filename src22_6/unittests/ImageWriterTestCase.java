package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import renderer.*;
import scene.Scene;

import java.awt.*;

public class ImageWriterTestCase {

	@Test
	public void test() {

		ImageWriter imageWriter = new ImageWriter(" ImageWriterTestCase", 501, 501, 500, 500);
		imageWriter.printGrid(50);
		imageWriter.writeToimage();
		System.out.println(imageWriter.PROJECT_PATH);


	}

}
