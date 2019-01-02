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
 * @description: 同意换位
 * @author: lvxianqing
 * @create: 2018/12/29 13:46
 */

public class H8 extends IMsgHandler {

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        Long roomId = Optional.ofNullable(NzqGameCacheManager.get(xf)).map(cache -> cache.getRoomId()).orElse(0l);
        CustomTwoPeopleRoom room = CustomRooms.get(roomId);
        room.confirmExchange(xf);
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
