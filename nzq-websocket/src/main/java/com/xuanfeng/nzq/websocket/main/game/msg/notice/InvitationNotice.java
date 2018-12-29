package com.xuanfeng.nzq.websocket.main.game.msg.notice;

/**
 * @description: 邀请通知
 * @author: lvxianqing
 * @create: 2018/12/28 17:08
 */

public class InvitationNotice {
    // 发送邀请者账号
    private Long xf;
    // 房间号
    private Long invitationId;
    // 创建时间
    private Long createTime = System.currentTimeMillis();

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }
}
