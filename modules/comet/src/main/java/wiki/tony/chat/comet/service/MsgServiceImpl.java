package wiki.tony.chat.comet.service;

import org.springframework.stereotype.Service;
import wiki.tony.chat.base.bean.Proto;
import wiki.tony.chat.base.service.MsgService;

@Service
public class MsgServiceImpl implements MsgService {
    @Override
    public boolean receive(Proto proto) {
        return false;
    }
}
