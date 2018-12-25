package com.xuanfeng.nzq.websocket.main.im.process;

import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.commons.WsResultUtil;
import com.xuanfeng.nzq.commons.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.commons.msg.request.RequestMsg;
import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.main.im.msg.push.SendTextMsgNotice;
import com.xuanfeng.nzq.websocket.main.im.msg.request.SendTextMsgReq;
import com.xuanfeng.nzq.websocket.main.im.msg.response.SendTextMsgResp;
import com.xuanfeng.nzq.websocket.util.ChatSessions;
import com.xuanfeng.nzq.websocket.util.UserManager;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Date;

/**
 * @description: 发送消息处理器
 * @author: lvxianqing
 * @create: 2018/10/03 10:15
 */
@Component
public class SendTextMsgHandler extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {

        SendTextMsgResp resp = new SendTextMsgResp();
        SendTextMsgReq req = (SendTextMsgReq)message;

        UserCache userCache = UserManager.getUserCache(xf);

        if (!userCache.getFriendXf().contains(req.getXf())) {
            return WsResultUtil.createFailedResult(RetEnum.非法请求,"对方不是你的好友，不可发送消息");
        }
        Date date = new Date();
        resp.setClientTime(req.getClientTime());
        resp.setXf(req.getXf());
        resp.setServerTime(date);
        resp.setText(req.getText());
        SendTextMsgNotice notice = new SendTextMsgNotice(xf,req.getText(),date);
        // TODO: 2018/11/23 异步存库

        ChatSessions.sendMsgToXf(req.getXf(),new NoticeMsg(req.getMsgId(),notice));

        return resp;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return SendTextMsgReq.class;
    }
}
