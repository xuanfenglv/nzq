package com.xuanfeng.nzq.websocket.main.game.process;

import com.xuanfeng.nzq.commons.msg.request.RequestMsg;
import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 创建2人房间
 * @author: lvxianqing
 * @create: 2018/12/21 17:16
 */

public class CreateRoomFor2Handler extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return RequestMsg.class;
    }
}
