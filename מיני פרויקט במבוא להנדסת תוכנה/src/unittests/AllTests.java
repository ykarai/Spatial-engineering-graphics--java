package unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
                RenderTest.class, ImageWriterTestCase.class,
                SpotLightTest.class, PointLightTest.class, AllLightsTest.class,
                Scene1Test.class, Scene2Test.class, Scene3Test.class, Scene4Test.class, Scene5Test.class,
                ShadowTest.class ,ReflectionTest.class, RefractionTest.class
                })

public class AllTests {

}
