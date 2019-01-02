package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;

/**
 * @description: 拒绝邀请
 * @author: lvxianqing
 * @create: 2018/12/28 18:21
 */

public class RejectInvitationRequest extends RequestMsg {
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
