package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.Rooms;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.javabean.room.base.BaseRoom;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.msg.notice.SendEmotionNotice;
import com.xuanfeng.nzq.websocket.main.game.msg.request.SendEmotionRequest;
import com.xuanfeng.nzq.websocket.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.util.UserManager;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 发送表情
 * @author: lvxianqing
 * @create: 2018/12/29 15:48
 */

public class H14 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        SendEmotionRequest request = (SendEmotionRequest)message;
        SendEmotionNotice notice = new SendEmotionNotice();
        notice.setXf(xf);
        notice.setId(request.getId());
        NoticeMsg<SendEmotionNotice> realNotice = WsResultUtil.createNoticeResult(GameMsgId.发送表情, notice);

        UserCache userCache = UserManager.getUserCache(xf);
        BaseRoom room = Rooms.get(userCache.getRoomId());
        room.sendNoticeToOthers(xf,realNotice);
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return SendEmotionRequest.class;
    }
}
