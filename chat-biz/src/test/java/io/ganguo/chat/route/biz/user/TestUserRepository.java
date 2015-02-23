package io.ganguo.chat.route.biz.user;

import io.ganguo.chat.route.biz.AbstractIntegrationTest;
import io.ganguo.chat.route.biz.entity.User;
import io.ganguo.chat.route.biz.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

/**
 * Created by Tony on 2/20/15.
 */
public class TestUserRepository extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        User user = new User();
        user.setUin(2L);
        user.setAccount("test2");
        user.setPassword("test2");
        user = userRepository.save(user);

        System.out.println("##### " + user);
    }

    @Test
    public void testFind() {
        User user = userRepository.findByAccount("test");

        System.out.println("### " + user);
    }

    @Test
    public void testFindAll() {
        Iterable<User> users = userRepository.findAll();
        Iterator it = users.iterator();
        while (it.hasNext()) {
            System.out.println("##### " + it.next());
        }
    }


}
