package io.ganguo.chat.route.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * Created by Tony on 2/24/15.
 */
public class ChatContext {

    private final ApplicationContext applicationContext;
    private final static ChatContext singleton = new ChatContext();

    private static ChatContext me() {
        return singleton;
    }

    private ChatContext() {
        applicationContext = new AnnotationConfigApplicationContext(Bootstrap.class);
    }

    public static <T> T getBean(Class<T> clazz) {
        return singleton.applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name) {
        return (T) singleton.applicationContext.getBean(name);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return singleton.applicationContext.getBeansOfType(type);
    }
}
