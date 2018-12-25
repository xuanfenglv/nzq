package com.xuanfeng.nzq.websocket.main.game.process;

import com.xuanfeng.nzq.commons.msg.request.RequestMsg;
import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.MatchRooms;
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

public class ConfirmMatchHandler extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        UserCache userCache = UserManager.getUserCache(xf);
        MatchTwoPeopleRoom room = MatchRooms.get(userCache.getRoomId());
        // 匹配确认
        room.confirm(xf);
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
