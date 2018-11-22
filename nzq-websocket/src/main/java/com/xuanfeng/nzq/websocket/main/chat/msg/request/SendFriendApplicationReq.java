package com.xuanfeng.nzq.websocket.main.chat.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.result.CheckParamResult;

/**
 * @description: 发送好友申请
 * @author: lvxianqing
 * @create: 2018/10/03 10:42
 */

public class SendFriendApplicationReq extends RequestMsg {
    private int receiveXf;
    private String text;
    private int groupId;
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

    public int getReceiveXf() {
        return receiveXf;
    }

    public void setReceiveXf(int receiveXf) {
        this.receiveXf = receiveXf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
