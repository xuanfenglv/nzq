package com.xuanfeng.nzq.websocket.main.chat.msg.push;

import com.xuanfeng.nzq.websocket.base.msg.push.PushMsg;

/**
 * @description: 发送好友申请
 * @author: lvxianqing
 * @create: 2018/10/03 10:48
 */

public class SendFriendApplicationPush extends PushMsg {
    private long xf;
    private String text;
    private String nickname;

    @Override
    protected int initMsgId() {
        return 8;
    }

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
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
