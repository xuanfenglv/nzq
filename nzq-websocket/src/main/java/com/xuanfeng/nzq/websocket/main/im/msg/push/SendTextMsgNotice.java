package com.xuanfeng.nzq.websocket.main.im.msg.push;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @description: 发送文字消息推送
 * @author: lvxianqing
 * @create: 2018/10/03 10:28
 */

public class SendTextMsgNotice {
    private long xf;
    private String text;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serverTime;

    public SendTextMsgNotice(long xf, String text, Date serverTime) {
        this.xf = xf;
        this.text = text;
        this.serverTime = serverTime;
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

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

}
