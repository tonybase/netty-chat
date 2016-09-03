package wiki.tony.chat.comet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import wiki.tony.chat.base.service.MessageService;

import javax.annotation.Resource;

/**
 * 程序入口
 */
@SpringBootApplication
//组件扫描
@ComponentScan("wiki.tony.chat")
public class ChatApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(ChatApplication.class);

    @Resource(name = "tcpChatServer")
    private ChatServer tcpChatServer;
    @Resource(name = "webSocketChatServer")
    private ChatServer webSocketChatServer;

    @Resource
    private MessageService service;


    @Override
    public void run(String... strings) throws Exception {
        try {
            tcpChatServer.start();
            webSocketChatServer.start();

            Thread.currentThread().join();
        } catch (Exception e) {
            LOG.error("startup error!", e);
        }
    }
}
