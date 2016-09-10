package wiki.tony.chat.comet;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import wiki.tony.chat.comet.operation.Operation;

import java.util.Map;

/**
 * 聊天操作
 */
@Component
public class ChatOperation {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<Integer, Operation> ops = Maps.newConcurrentMap();

    @Bean(name = "operations")
    public Map<Integer, Operation> operations() {
        Map<String, Operation> beans = applicationContext.getBeansOfType(Operation.class);
        for (Operation op : beans.values()) {
            ops.put(op.op(), op);
        }
        return ops;
    }

    public Operation find(Integer op) {
        return ops.get(op);
    }

}
