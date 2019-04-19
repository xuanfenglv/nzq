package com.xuanfeng.nzq.websocket.main.im.msg.response;

/**
 * @description: 发送好友申请
 * @author: lvxianqing
 * @create: 2018/10/03 10:47
 */

public class SendFriendApplicationResp {
    private Long id;
    private Long xf;
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
