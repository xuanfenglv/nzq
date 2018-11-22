package com.xuanfeng.nzq.websocket.main.chat.msg.response;

import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;

/**
 * @description: 发送好友申请
 * @author: lvxianqing
 * @create: 2018/10/03 10:47
 */

public class SendFriendApplicationResp extends ResponseMsg {
    private long xf;
    private String nickname;

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    protected int initMsgId() {
        return 8;
    }
}
