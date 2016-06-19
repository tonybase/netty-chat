package wiki.tony.chat.comet.bean;

import io.netty.util.AttributeKey;
import wiki.tony.chat.base.bean.AuthToken;

/**
 * Created by Tony on 4/14/16.
 */
public class Constants {

    public static final AttributeKey<AuthToken> KEY_USER_TOKEN = AttributeKey.valueOf("user_token");

}
