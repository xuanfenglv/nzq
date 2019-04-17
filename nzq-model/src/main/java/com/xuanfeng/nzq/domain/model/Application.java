package com.xuanfeng.nzq.domain.model;

public class Application {
    private Long id;

    private Long sendXf;

    private Long receiveXf;

    private String text;

    private Long groupId;

    private String remark;

    private Byte status;

    private Boolean sendVisible;

    private Boolean receiveVisible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendXf() {
        return sendXf;
    }

    public void setSendXf(Long sendXf) {
        this.sendXf = sendXf;
    }

    public Long getReceiveXf() {
        return receiveXf;
    }

    public void setReceiveXf(Long receiveXf) {
        this.receiveXf = receiveXf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public Boolean getSendVisible() {
        return sendVisible;
    }

    public void setSendVisible(Boolean sendVisible) {
        this.sendVisible = sendVisible;
    }

    public Boolean getReceiveVisible() {
        return receiveVisible;
    }

    public void setReceiveVisible(Boolean receiveVisible) {
        this.receiveVisible = receiveVisible;
    }
}