package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 换位请求
 * @author: lvxianqing
 * @create: 2018/12/29 12:06
 */
// TODO: 2018/12/29
public class TranspositionRequest extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
