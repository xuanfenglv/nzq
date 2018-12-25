package com.xuanfeng.nzq.websocket.base.manager.base;

import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;

import java.util.HashMap;
import java.util.Map;

public class HandlerManager {
    // 消息列表
    private Map<Integer, IMsgHandler> handlerMapping =new HashMap<>(64);


    public void putAHandler(int msgId, IMsgHandler handler) {
        handlerMapping.put(msgId,handler);
    }

    public IMsgHandler getHandler(int msgId) {
        return handlerMapping.get(msgId);
    }
}