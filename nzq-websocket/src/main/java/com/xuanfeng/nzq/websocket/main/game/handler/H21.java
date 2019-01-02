package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.request.WithXfRequest;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.javabean.NzqGameCache;
import com.xuanfeng.nzq.websocket.util.NzqGameCacheManager;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 请求观战
 * @author: lvxianqing
 * @create: 2019/01/02 17:56
 */

public class H21 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        WithXfRequest request = (WithXfRequest)message;
        request.getXf();
        NzqGameCache cache = NzqGameCacheManager.get(request.getXf());
        if (cache == null) {
            return WsResultUtil.createRespFailedResult(RetEnum.非法请求,"请求观战者已离线");
        }
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
