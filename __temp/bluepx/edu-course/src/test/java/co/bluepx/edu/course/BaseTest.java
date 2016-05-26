package co.bluepx.edu.course;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericGroovyXmlContextLoader;

/**
 * Created by bison on 12/29/15.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.groovy", loader = GenericGroovyXmlContextLoader.class)
public class BaseTest {
}
