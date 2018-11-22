package com.xuanfeng.nzq.websocket.main.chat.msg.response;

import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;

/**
 * @description: 发送文字消息响应
 * @author: lvxianqing
 * @create: 2018/10/03 10:11
 */

public class SendTextMsgResp extends ResponseMsg {
    private long timestamp;
    @Override
    protected int initMsgId() {
        return 2;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
