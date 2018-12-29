package com.xuanfeng.nzq.domain.constant;

public enum UserStatusEnum {
    离线((byte)0),
    在线((byte)1)
    ;
    private Byte status;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    UserStatusEnum(Byte status) {
        this.status = status;
    }
}
