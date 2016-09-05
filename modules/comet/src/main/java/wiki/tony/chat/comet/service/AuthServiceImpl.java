package wiki.tony.chat.comet.service;

import org.springframework.stereotype.Service;
import wiki.tony.chat.base.bean.Proto;
import wiki.tony.chat.base.exception.ConnectionAuthException;
import wiki.tony.chat.base.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String auth(int serverId, Proto proto) throws ConnectionAuthException {
        return null;
    }

    @Override
    public boolean quit(int serverId, String key) {
        return false;
    }
}
