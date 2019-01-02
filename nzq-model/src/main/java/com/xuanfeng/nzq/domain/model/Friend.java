package com.xuanfeng.nzq.domain.model;

import java.util.Date;

public class Friend {
    private Long id;

    private Long xf;

    private Long fxf;

    private Long groupId;

    private String remark;

    private Date ctime;

    private Date mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    public Long getFxf() {
        return fxf;
    }

    public void setFxf(Long fxf) {
        this.fxf = fxf;
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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}