package io.ganguo.chat.route.biz.user;

import io.ganguo.chat.route.biz.AbstractIntegrationTest;
import io.ganguo.chat.route.biz.entity.Login;
import io.ganguo.chat.route.biz.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Tony on 2/20/15.
 */
public class TestUserService extends AbstractIntegrationTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testLogin() {

        Login login = userService.login("test", "test");
        System.out.println("##### " + login);
        assertNotNull(login);

    }

}
