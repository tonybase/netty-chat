package io.ganguo.chat.route.biz;

import java.util.UUID;

/**
 * Created by Tony on 2/20/15.
 */
public class Test {

    @org.junit.Test
    public void testUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid: " + uuid);
        System.out.println("timestamp: " + uuid.timestamp());

    }

}
