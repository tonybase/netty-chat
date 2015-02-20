package io.ganguo.chat.biz;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Tony on 2/20/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public abstract class AbstractIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Before
    public void setUp() {
    
    }
    
}
