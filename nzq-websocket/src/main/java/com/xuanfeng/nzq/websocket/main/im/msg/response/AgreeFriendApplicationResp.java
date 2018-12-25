package com.xuanfeng.nzq.websocket.main.im.msg.response;

import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;

/**
 * @description: 同意好友申请响应
 * @author: lvxianqing
 * @create: 2018/11/23 11:13
 */

public class AgreeFriendApplicationResp extends ResponseMsg {
    private Long applicationId;
    private Long xf;
    private Long groupId;
    private String nickname;

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
