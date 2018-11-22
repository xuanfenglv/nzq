package com.xuanfeng.nzq.websocket.main.chat.msg.push;

import com.xuanfeng.nzq.websocket.base.msg.push.PushMsg;

/**
 * @description: 下线通知
 * @author: lvxianqing
 * @create: 2018/09/30 18:29
 */

public class OfflinePush extends PushMsg {

    private long xf;

    public OfflinePush(long xf) {
        this.xf = xf;
    }

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

    @Override
    protected int initMsgId() {
        return 13;
    }
}
