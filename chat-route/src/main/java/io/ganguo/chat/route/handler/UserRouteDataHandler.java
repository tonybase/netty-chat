package io.ganguo.chat.route.handler;

import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.transport.IMResponse;
import io.ganguo.chat.route.bean.Constants;
import io.ganguo.chat.route.bean.RouteData;
import io.ganguo.chat.route.bean.RouteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by Tony on 2/24/15.
 */
@Component
public class UserRouteDataHandler extends IMHandler<RouteData> {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public short getId() {
        return Constants.ROUTE_TO_USER_TYPE;
    }

    @Override
    public void dispatch(IMConnection connection, RouteData data) {
        Cache onlineCache = cacheManager.getCache(Constants.CACHE_USER_ONLINE);
        Cache serverCache = cacheManager.getCache(Constants.CACHE_SERVERS);

        long serverId = onlineCache.get(data.getTo(), Long.class);
        IMConnection toConn = serverCache.get(serverId, IMConnection.class);
        if (toConn != null) {
            toConn.sendResponse(new RouteResponse(data.getData()));
        }
    }
}
