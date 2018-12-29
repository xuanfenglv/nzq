package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.Rooms;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.javabean.room.MatchTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.main.game.msg.request.DropRequest;
import com.xuanfeng.nzq.websocket.util.UserManager;

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
        UserCache userCache = UserManager.getUserCache(xf);
        MatchTwoPeopleRoom room = (MatchTwoPeopleRoom)Rooms.get(userCache.getRoomId());
        room.drop(xf,request.getRow(),request.getColumn());
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return DropRequest.class;
    }
}
