package wiki.tony.chat.comet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wiki.tony.chat.comet.bean.Proto;

import java.io.IOException;

/**
 * Created by Tony on 4/14/16.
 */
public class JsonTest {
    private final Logger logger = LoggerFactory.getLogger(JsonTest.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test() throws IOException {
        Proto proto = new Proto();
        proto.setPacketLen(1);
        proto.setBody("hello".getBytes());

        String json = mapper.writeValueAsString(proto);
        logger.debug(json);

        proto = mapper.readValue(json, Proto.class);
        logger.debug(proto.toString());
    }

}
