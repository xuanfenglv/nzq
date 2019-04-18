package com.xuanfeng.nzq.domain.entity;

/**
 * @description: 好友信息
 * @author: lvxianqing
 * @create: 2018/11/23 10:48
 */
public class FriendInfo {
    private Long xf;
    private String nickname;
    private String remark;
    private Byte status;
    private Long groupId;

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
