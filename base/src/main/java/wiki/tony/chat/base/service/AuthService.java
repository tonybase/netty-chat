package wiki.tony.chat.base.service;

/**
 * Created by Tony on 4/14/16.
 */
public interface AuthService {

    boolean auth(int serverId, long userId, String token);

}
