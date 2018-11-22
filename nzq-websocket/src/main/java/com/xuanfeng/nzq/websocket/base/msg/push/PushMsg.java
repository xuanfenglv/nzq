package com.xuanfeng.nzq.websocket.base.msg.push;

/**
 * @description: websocket推送消息
 * @author: lvxianqing
 * @create: 2018/09/30 15:55
 */

public abstract class PushMsg {
    private byte type=1;
    private int msgId;
    {
        setMsgId(initMsgId());
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    protected abstract int initMsgId();
}
