package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.im.msg.request.InitAccountReq;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.msg.response.ResponseMsg;

import javax.websocket.Session;

/**
 * @description: 初始化账号
 * @author: lvxianqing
 * @create: 2018/09/30 16:31
 */
public class H1 extends IMsgHandler {

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) {
        InitAccountReq req = (InitAccountReq) message;
        // TODO: 2018/11/23 token校验

        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return InitAccountReq.class;
    }
}
