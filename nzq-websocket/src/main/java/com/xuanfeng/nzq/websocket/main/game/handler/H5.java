package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.CustomRooms;
import com.xuanfeng.nzq.websocket.component.Invitations;
import com.xuanfeng.nzq.websocket.javabean.Invitation;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.CustomTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.msg.notice.RejectInvitationNotice;
import com.xuanfeng.nzq.websocket.main.game.msg.request.RejectInvitationRequest;
import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.util.NzqGameSessions;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 拒绝邀请-5
 * @author: lvxianqing
 * @create: 2018/12/28 18:14
 */

public class H5 extends IMsgHandler {

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        RejectInvitationRequest request = (RejectInvitationRequest) message;

        Invitation invitation = Invitations.get(request.getInvitationId());
        if (invitation == null) {
            return null;
        }
        CustomTwoPeopleRoom room = CustomRooms.get(invitation.getRoomId());
        // 房间存在&&发送者还在这个房间中&&比赛未开始&&当前轮
        // TODO: 2018/12/29
        Long sendXf = invitation.getSendXf();

        // 推送
        RejectInvitationNotice notice = new RejectInvitationNotice();
        notice.setXf(xf);
        NzqGameSessions.sendMsgToXf(sendXf, new NoticeMsg(GameMsgId.拒绝邀请, notice));
        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
