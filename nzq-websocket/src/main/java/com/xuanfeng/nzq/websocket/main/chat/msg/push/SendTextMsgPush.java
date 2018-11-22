package com.xuanfeng.nzq.websocket.main.chat.msg.push;

import com.xuanfeng.nzq.websocket.base.msg.push.PushMsg;

/**
 * @description: 发送文字消息推送
 * @author: lvxianqing
 * @create: 2018/10/03 10:28
 */

public class SendTextMsgPush extends PushMsg {
    private long sendXf;
    private String text;

    public SendTextMsgPush(long sendXf, String text) {
        this.sendXf = sendXf;
        this.text = text;
    }

    public long getSendXf() {
        return sendXf;
    }

    public void setSendXf(long sendXf) {
        this.sendXf = sendXf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected int initMsgId() {
        return 2;
    }
}
