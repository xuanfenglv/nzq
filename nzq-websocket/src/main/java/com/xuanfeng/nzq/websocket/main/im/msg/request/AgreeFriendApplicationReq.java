package com.xuanfeng.nzq.websocket.main.im.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;

/**
 * @description: 同意好友申请处理器
 * @author: lvxianqing
 * @create: 2018/11/22 21:32
 */

public class AgreeFriendApplicationReq extends RequestMsg {

    private Long applicationId;
    private Long groupId;
    private String remark;
    @Override
    protected void doCheck(CheckParamResult result) {

    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
