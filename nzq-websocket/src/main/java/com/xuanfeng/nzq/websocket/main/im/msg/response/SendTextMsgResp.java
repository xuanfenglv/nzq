package com.xuanfeng.nzq.websocket.main.im.msg.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @description: 发送文字消息响应
 * @author: lvxianqing
 * @create: 2018/10/03 10:11
 */

public class SendTextMsgResp {
    private Long xf;
    private Long clientTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serverTime=new Date();
    private String text;

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    public Long getClientTime() {
        return clientTime;
    }

    public void setClientTime(Long clientTime) {
        this.clientTime = clientTime;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
