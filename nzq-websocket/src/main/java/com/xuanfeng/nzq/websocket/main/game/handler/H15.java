package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.Rooms;
import com.xuanfeng.nzq.websocket.javabean.NzqGameCache;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.base.BaseRoom;
import com.xuanfeng.nzq.websocket.main.game.msg.notice.SendVoiceNotice;
import com.xuanfeng.nzq.websocket.main.game.msg.request.SendVoiceRequest;
import com.xuanfeng.nzq.websocket.util.NzqGameCacheManager;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 发送快捷语音
 * @author: lvxianqing
 * @create: 2018/12/29 15:48
 */

public class H15 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        SendVoiceRequest request = (SendVoiceRequest)message;
        SendVoiceNotice notice = new SendVoiceNotice();
        notice.setXf(xf);
        notice.setId(request.getId());
        NoticeMsg<SendVoiceNotice> realNotice = WsResultUtil.createNoticeResult(GameMsgId.发送声音, notice);

        NzqGameCache cache = NzqGameCacheManager.get(xf);
        BaseRoom room = Rooms.get(cache.getRoomId());
        room.sendNoticeToOthers(xf,realNotice);
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return SendVoiceRequest.class;
    }
}
