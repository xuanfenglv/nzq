package com.xuanfeng.nzq.websocket.main.im.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;

/**
 * @description: 发送好友申请
 * @author: lvxianqing
 * @create: 2018/10/03 10:42
 */

public class SendFriendApplicationReq extends RequestMsg {
    private Long receiveXf;
    private String text;

    private Long groupId;
    private String remark;

    @Override
    protected void doCheck(CheckParamResult result) {
        if (receiveXf < 0) {
            result.setErrMsg("无效的账号");
        } else if(receiveXf==0) {
            result.setErrMsg("无接收方账号");
        } else if (groupId<=0) {
            result.setErrMsg("非法的分组id："+groupId);
        }
    }

    public Long getReceiveXf() {
        return receiveXf;
    }

    public void setReceiveXf(Long receiveXf) {
        this.receiveXf = receiveXf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
