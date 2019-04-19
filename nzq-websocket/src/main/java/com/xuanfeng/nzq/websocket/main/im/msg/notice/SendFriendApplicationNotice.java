package com.xuanfeng.nzq.websocket.main.im.msg.notice;

/**
 * @description: 发送好友申请
 * @author: lvxianqing
 * @create: 2018/10/03 10:48
 */

public class SendFriendApplicationNotice {
    private Long id;
    private Long xf;
    private String text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
