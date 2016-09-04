package wiki.tony.chat.base.service;

import wiki.tony.chat.base.bean.Proto;
import wiki.tony.chat.base.exception.ConnectionAuthException;

/**
 * Created by Tony on 4/14/16.
 */
public interface AuthService {

    String auth(int serverId, Proto proto) throws ConnectionAuthException;

    boolean quit(int serverId, String key);

}
