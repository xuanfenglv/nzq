package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.CustomRooms;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.CustomTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.util.NzqGameCacheManager;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Optional;

/**
 * @description: 换位处理器
 * @author: lvxianqing
 * @create: 2018/12/29 12:07
 */

public class H7 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        Long roomId = Optional.ofNullable(NzqGameCacheManager.get(xf)).map(cache -> cache.getRoomId()).orElse(0l);
        CustomTwoPeopleRoom room = CustomRooms.get(roomId);
        room.requestExchange(xf);
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
