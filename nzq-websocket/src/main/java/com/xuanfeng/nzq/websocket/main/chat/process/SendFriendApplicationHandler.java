package com.xuanfeng.nzq.websocket.main.chat.process;

import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.chat.msg.request.SendFriendApplicationReq;
import com.xuanfeng.nzq.websocket.main.chat.msg.response.SendFriendApplicationResp;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 发送好友申请处理器
 * @author: lvxianqing
 * @create: 2018/10/03 10:55
 */

public class SendFriendApplicationHandler extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        SendFriendApplicationReq req = (SendFriendApplicationReq)message;
        SendFriendApplicationResp resp = new SendFriendApplicationResp();
        // 存库

        // 推送

        // 响应

        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return SendFriendApplicationReq.class;
    }
}
