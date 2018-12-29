package com.xuanfeng.nzq.websocket.main.im.msg.notice;

/**
 * @description: 拒绝好友申请推送
 * @author: lvxianqing
 * @create: 2018/11/22 22:30
 */

public class RejectFriendApplicationNotice {
    private Long applicationId;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

}
