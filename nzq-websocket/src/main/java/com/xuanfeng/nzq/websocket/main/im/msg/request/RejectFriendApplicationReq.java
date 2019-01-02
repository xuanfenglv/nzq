package com.xuanfeng.nzq.websocket.main.im.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;

/**
 * @description: 拒绝好友申请请求
 * @author: lvxianqing
 * @create: 2018/11/22 22:19
 */

public class RejectFriendApplicationReq extends RequestMsg {
    // 好友申请id
    private Long applicationId;

    @Override
    protected void doCheck(CheckParamResult result) {

    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
