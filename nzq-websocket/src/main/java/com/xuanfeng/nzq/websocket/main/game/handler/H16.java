package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.Rooms;
import com.xuanfeng.nzq.websocket.javabean.NzqGameCache;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.base.BaseRoom;
import com.xuanfeng.nzq.websocket.main.game.msg.notice.RoomMsgNotice;
import com.xuanfeng.nzq.websocket.main.game.msg.request.RoomMsgRequest;
import com.xuanfeng.nzq.websocket.util.NzqGameCacheManager;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 发送房间内聊天消息
 * @author: lvxianqing
 * @create: 2018/12/29 16:21
 */

public class H16 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message ,long xf, Session session) throws IOException {
        RoomMsgRequest request = (RoomMsgRequest)message;
        RoomMsgNotice noticeData = new RoomMsgNotice();
        noticeData.setXf(xf);
        noticeData.setText(request.getText());
        NoticeMsg noticeMsg = WsResultUtil.createNoticeResult(GameMsgId.发送消息, noticeData);

        NzqGameCache cache = NzqGameCacheManager.get(xf);
        BaseRoom room = Rooms.get(cache.getRoomId());
        room.sendNoticeToOthers(xf,noticeMsg);
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return RoomMsgRequest.class;
    }
}
