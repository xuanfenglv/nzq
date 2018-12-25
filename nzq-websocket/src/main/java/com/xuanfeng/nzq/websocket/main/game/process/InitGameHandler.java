package com.xuanfeng.nzq.websocket.main.game.process;

import com.xuanfeng.nzq.commons.WsResultUtil;
import com.xuanfeng.nzq.commons.msg.request.RequestMsg;
import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.im.msg.request.InitAccountReq;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * @description: 初始化账号
 * @author: lvxianqing
 * @create: 2018/09/30 16:31
 */
@Component
public class InitGameHandler extends IMsgHandler {

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) {
        InitAccountReq req = (InitAccountReq) message;
        // TODO: 2018/11/23 token校验

        return WsResultUtil.createSuccessResult(null);
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return InitAccountReq.class;
    }
}
