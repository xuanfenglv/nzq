package com.xuanfeng.nzq.websocket.main.chat.process;

import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.constant.ResponseEnum;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.main.chat.msg.push.SendTextMsgPush;
import com.xuanfeng.nzq.websocket.main.chat.msg.request.SendTextMsgReq;
import com.xuanfeng.nzq.websocket.main.chat.msg.response.SendTextMsgResp;
import com.xuanfeng.nzq.websocket.util.ChatSessions;
import com.xuanfeng.nzq.websocket.util.UserManager;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 发送消息处理器
 * @author: lvxianqing
 * @create: 2018/10/03 10:15
 */

public class SendTextMsgHandler extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        SendTextMsgResp resp = new SendTextMsgResp();
        SendTextMsgReq req = (SendTextMsgReq)message;

        resp.setTimestamp(req.getTimestamp());
        UserCache userCache = UserManager.getUserCache(xf);

        if (!userCache.getFriendXf().contains(req.getReceiveXf())) {
            resp.setResponseTypeWithDetail(ResponseEnum.非法请求,"对方不是你的好友，不可发送消息");
            return resp;
        }
        SendTextMsgPush push = new SendTextMsgPush(xf,req.getText());
        // 存库

        ChatSessions.sendMsgToXf(req.getReceiveXf(),push);
        return resp;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return SendTextMsgReq.class;
    }
}
