package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.CustomTwoPeopleRoom;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 创建2人房间
 * @author: lvxianqing
 * @create: 2018/12/21 17:16
 */

public class H2 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        CustomTwoPeopleRoom room = new CustomTwoPeopleRoom(xf,session);
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
