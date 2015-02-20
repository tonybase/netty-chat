package io.ganguo.chat.biz.user;

import io.ganguo.chat.biz.AbstractIntegrationTest;
import io.ganguo.chat.biz.entity.Login;
import io.ganguo.chat.biz.entity.User;
import io.ganguo.chat.biz.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

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
