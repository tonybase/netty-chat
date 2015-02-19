package io.ganguo.chat.server;

import io.ganguo.chat.core.handler.IMHandlerManager;
import io.ganguo.chat.server.handler.UserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
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
    private static ApplicationContext CONTEXT;

    public static ApplicationContext getContext() {
        if (CONTEXT == null) {
            CONTEXT = new AnnotationConfigApplicationContext(Bootstrap.class);
        }
        return CONTEXT;
    }

    public static void main(String[] args) {
        ChatServer chatServer = getContext().getBean(ChatServer.class);
        try {
            chatServer.run();
        } catch (Exception e) {
            logger.error("startup ChatServer error!!!", e);
        }
    }

}
