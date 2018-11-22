package com.xuanfeng.nzq.domain.model;

public class Application {
    private Long id;

    private Long sendxf;

    private Long receivexf;

    private String text;

    private Long groupid;

    private String remark;

    private Byte status;

    private Boolean sendvisible;

    private Boolean receivevisible;

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

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getSendvisible() {
        return sendvisible;
    }

    public void setSendvisible(Boolean sendvisible) {
        this.sendvisible = sendvisible;
    }

    public Boolean getReceivevisible() {
        return receivevisible;
    }

    public void setReceivevisible(Boolean receivevisible) {
        this.receivevisible = receivevisible;
    }
}