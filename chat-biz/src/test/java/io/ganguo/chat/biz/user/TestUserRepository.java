package io.ganguo.chat.biz.user;

import io.ganguo.chat.biz.AbstractIntegrationTest;
import io.ganguo.chat.biz.entity.User;
import io.ganguo.chat.biz.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tony on 2/20/15.
 */
public class TestUserRepository extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        User user = new User();
        user.setUin(0L);
        user.setAccount("test");
        user.setPassword("test");
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
