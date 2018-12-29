package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.CustomRooms;
import com.xuanfeng.nzq.websocket.component.Invitations;
import com.xuanfeng.nzq.websocket.javabean.Invitation;
import com.xuanfeng.nzq.websocket.javabean.room.CustomTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.main.game.msg.request.AcceptInvitationRequest;
import com.xuanfeng.nzq.websocket.main.game.msg.response.AcceptInvitationResponse;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 接受申请-4
 * @author: lvxianqing
 * @create: 2018/12/28 17:07
 */

public class H4 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message,long xf, Session session) throws IOException {
        AcceptInvitationRequest request = (AcceptInvitationRequest)message;
        ResponseMsg responseMsg = null;
        Invitation invitation = Invitations.get(request.getInvitationId());
        if (invitation == null||!invitation.isValid()) {
            responseMsg = WsResultUtil.createRespFailedResult(RetEnum.其他错误,"邀请已过期");
        }
        CustomTwoPeopleRoom room = CustomRooms.get(invitation.getRoomId());

        // room is not exist
        if (room == null) {
            responseMsg = WsResultUtil.createRespFailedResult(RetEnum.其他错误,"房间已销毁");
        }
        // 房间在，邀请者已退出房间
        if (!room.constainsPlayer(invitation.getSendXf())) {
            responseMsg = WsResultUtil.createRespFailedResult(RetEnum.其他错误,"邀请者已退出房间");
        }

        int position = room.enterRoom(xf, session);

        if (position==0) {
            responseMsg = WsResultUtil.createRespFailedResult(RetEnum.其他错误,"房间已满");
        } else {
            AcceptInvitationResponse response = new AcceptInvitationResponse();
            response.setBlack(room.getBlack());
            response.setWhite(room.getWhite());
            response.setMyPosition(position);

            responseMsg = WsResultUtil.createRespSuccessResult();
        }

        return responseMsg;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
