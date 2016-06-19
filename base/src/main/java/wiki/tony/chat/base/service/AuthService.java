package wiki.tony.chat.base.service;

import wiki.tony.chat.base.bean.AuthToken;

/**
 * Created by Tony on 4/14/16.
 */
public interface AuthService {

    boolean auth(int serverId, AuthToken authToken);

    boolean quit(int serverId, AuthToken authToken);

}
