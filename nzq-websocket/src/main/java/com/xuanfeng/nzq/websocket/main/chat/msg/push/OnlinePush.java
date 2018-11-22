package com.xuanfeng.nzq.websocket.main.chat.msg.push;

import com.xuanfeng.nzq.websocket.base.msg.push.PushMsg;

/**
 * @description: 上线推送
 * @author: lvxianqing
 * @create: 2018/09/30 18:30
 */

public class OnlinePush extends PushMsg {
    private long xf;

    @Override
    protected int initMsgId() {
        return 12;
    }

    public OnlinePush(long xf) {
        this.xf = xf;
    }

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }
}
