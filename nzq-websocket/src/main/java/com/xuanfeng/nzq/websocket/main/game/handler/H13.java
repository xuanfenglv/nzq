package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.Rooms;
import com.xuanfeng.nzq.websocket.javabean.NzqGameCache;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.MatchTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.main.game.msg.request.DropRequest;
import com.xuanfeng.nzq.websocket.util.NzqGameCacheManager;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 落子请求
 * @author: lvxianqing
 * @create: 2018/12/27 20:10
 */

public class H13 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        DropRequest request = (DropRequest)message;
        NzqGameCache cache = NzqGameCacheManager.get(xf);
        MatchTwoPeopleRoom room = (MatchTwoPeopleRoom)Rooms.get(cache.getRoomId());
        room.drop(xf,request.getRow(),request.getColumn());
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return DropRequest.class;
    }
}
