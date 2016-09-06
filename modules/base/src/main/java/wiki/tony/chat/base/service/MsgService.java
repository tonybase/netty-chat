package wiki.tony.chat.base.service;

import wiki.tony.chat.base.bean.Proto;

/**
 * 消息处理接口
 */
public interface MsgService {

    /**
     * 接收消息
     *
     * @param proto 协议
     * @return 是否处理成功
     */
    boolean receive(Proto proto);
}
