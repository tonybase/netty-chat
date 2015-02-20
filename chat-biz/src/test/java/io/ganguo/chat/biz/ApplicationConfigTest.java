package io.ganguo.chat.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Tony on 2/20/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ApplicationConfigTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void bootstrapAppFromJavaConfig() {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        assertNotNull(context);
    }

    @Test
    public void bootstrapAppFromXml() {

        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-context.xml");
        assertNotNull(context);
    }

}
