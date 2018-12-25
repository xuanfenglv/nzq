package com.xuanfeng.nzq.commons.msg.notice;

import com.xuanfeng.nzq.commons.msg.base.PushMsg;

/**
 * @description: websocket推送消息
 * @author: lvxianqing
 * @create: 2018/09/30 15:55
 */

public class NoticeMsg<T> extends PushMsg<T> {
    private byte type=1;

    public NoticeMsg(int msgId) {
        setMsgId(msgId);
    }

    public NoticeMsg(int msgId, T data) {
        setMsgId(msgId);
        setData(data);
    }

    public byte getType() {
        return type;
    }
}
