package com.xuanfeng.nzq.domain.model;

import java.util.Date;

public class ChatInfo {
    private Long id;

    private Long sendxf;

    private Long receivexf;

    private String text;

    private Date ctime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendxf() {
        return sendxf;
    }

    public void setSendxf(Long sendxf) {
        this.sendxf = sendxf;
    }

    public Long getReceivexf() {
        return receivexf;
    }

    public void setReceivexf(Long receivexf) {
        this.receivexf = receivexf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}