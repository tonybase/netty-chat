package io.ganguo.chat.route.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * startup chat server
 * <p/>
 * Created by Tony on 2/18/15.
 */
@Configuration
@ComponentScan("io.ganguo.chat")
public class Bootstrap {
    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        ChatServer chatServer = ChatContext.getBean(ChatServer.class);
        try {
            chatServer.run();
        } catch (Exception e) {
            logger.error("startup ChatServer error!!!", e);
        }
    }

}
