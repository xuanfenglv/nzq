package com.xuanfeng.nzq.websocket.main.im.msg.push;

/**
 * @description: 同意好友申请推送
 * @author: lvxianqing
 * @create: 2018/11/23 11:15
 */

public class AgreeFriendApplicationNotice {
    private Long applicationId;
    private Long xf;
    private Long groupId;
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
}
