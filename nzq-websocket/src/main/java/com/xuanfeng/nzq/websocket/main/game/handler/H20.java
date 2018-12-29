package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.Rooms;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.javabean.room.MatchTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.util.UserManager;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 确认匹配结果
 * @author: lvxianqing
 * @create: 2018/12/21 17:51
 */

public class H20 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        UserCache userCache = UserManager.getUserCache(xf);
        MatchTwoPeopleRoom room = (MatchTwoPeopleRoom) Rooms.get(userCache.getRoomId());
        // 匹配确认
        room.confirm(xf);
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
