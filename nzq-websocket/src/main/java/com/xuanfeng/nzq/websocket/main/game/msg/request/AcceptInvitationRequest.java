package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;

/**
 * @description: 接受邀请
 * @author: lvxianqing
 * @create: 2018/12/28 17:12
 */

public class AcceptInvitationRequest extends RequestMsg {
    // 房间号
    private Long invitationId;

    @Override
    protected void doCheck(CheckParamResult result) {

    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }
}
