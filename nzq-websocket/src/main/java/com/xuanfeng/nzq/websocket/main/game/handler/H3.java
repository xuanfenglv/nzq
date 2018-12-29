package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.CustomRooms;
import com.xuanfeng.nzq.websocket.component.Invitations;
import com.xuanfeng.nzq.websocket.javabean.Invitation;
import com.xuanfeng.nzq.websocket.javabean.room.CustomTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.msg.notice.InvitationNotice;
import com.xuanfeng.nzq.websocket.main.game.msg.request.InvitationRequest;
import com.xuanfeng.nzq.websocket.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.util.NzqGameSessions;
import com.xuanfeng.nzq.websocket.util.UserManager;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 发送邀请处理器-3
 * @author: lvxianqing
 * @create: 2018/12/28 16:40
 */

public class H3 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        InvitationRequest request = (InvitationRequest)message;
        // 添加一个受邀者
        Long roomId = UserManager.getUserCache(xf).getRoomId();
        CustomTwoPeopleRoom room = CustomRooms.get(roomId);
        if (room == null) {
            // 没有房间邀请你妹？
            return null;
        }
        Invitation invitation = new Invitation();
        invitation.setSendXf(xf);
        invitation.setReceiveXf(request.getXf());
        invitation.setRoomId(roomId);

        Long invitationId = Invitations.add(invitation);

        // 推送
        InvitationNotice notice = new InvitationNotice();
        notice.setXf(xf);
        notice.setInvitationId(invitationId);


        NzqGameSessions.sendMsgToXf(request.getXf(),new NoticeMsg(GameMsgId.发送邀请,notice));
        // TODO: 2018/12/28 暂时不做响应
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return InvitationRequest.class;
    }
}
