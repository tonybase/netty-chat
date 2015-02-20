package io.ganguo.chat.client;

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
@ComponentScan("io.ganguo.chat.client")
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
        ChatClient chatClient = getContext().getBean(ChatClient.class);
        try {
            chatClient.run();
        } catch (Exception e) {
            logger.error("startup ChatServer error!!!", e);
        }
    }

}
