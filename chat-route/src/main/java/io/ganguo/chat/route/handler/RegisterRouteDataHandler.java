package io.ganguo.chat.route.handler;

import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.protocol.Commands;
import io.ganguo.chat.core.protocol.Handlers;
import io.ganguo.chat.core.transport.Header;
import io.ganguo.chat.core.transport.IMResponse;
import io.ganguo.chat.route.bean.Constants;
import io.ganguo.chat.route.bean.RouteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by Tony on 2/24/15.
 */
@Component
public class RegisterRouteDataHandler extends IMHandler<RouteData> {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public short getId() {
        return Handlers.ROUTE;
    }

    @Override
    public void dispatch(IMConnection connection, RouteData data) {
        Cache serverCache = cacheManager.getCache(Constants.CACHE_SERVERS);
        serverCache.put(data.getTo(), connection);

        IMResponse resp = new IMResponse();
        Header header = new Header();
        header.setHandlerId(Handlers.ROUTE);
        header.setCommandId(Commands.ROUTE_REGISTER_SUCCESS);

        resp.setHeader(header);
        connection.sendResponse(resp);
    }

}
