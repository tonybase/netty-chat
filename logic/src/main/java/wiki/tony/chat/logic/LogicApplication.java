package wiki.tony.chat.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

/**
 * 程序入口
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("wiki.tony.chat.logic")
@ImportResource("classpath:dubbo-provider.xml")
public class LogicApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(LogicApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogicApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        try {
            Thread.currentThread().join();
        } catch (Exception e) {
            LOG.error("startup error!", e);
        }
    }
}
