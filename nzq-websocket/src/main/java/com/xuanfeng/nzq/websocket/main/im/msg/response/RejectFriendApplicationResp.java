package com.xuanfeng.nzq.websocket.main.im.msg.response;

/**
 * @description: 拒绝好友申请响应
 * @author: lvxianqing
 * @create: 2018/11/23 11:21
 */

public class RejectFriendApplicationResp {
    // 好友申请id
    private Long applicationId;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
