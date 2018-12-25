package com.xuanfeng.nzq.websocket.main.im.msg.response;

import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;

/**
 * @description: 发送好友申请
 * @author: lvxianqing
 * @create: 2018/10/03 10:47
 */

public class SendFriendApplicationResp extends ResponseMsg {
    private Long applicationId;
    private Long xf;
    private String nickname;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
