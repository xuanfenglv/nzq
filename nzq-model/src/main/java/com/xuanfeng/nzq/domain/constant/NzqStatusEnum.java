package com.xuanfeng.nzq.domain.constant;

public enum NzqStatusEnum {
    离线((byte)0),
    闲逛中((byte)1),
    房间中((byte)2),
    匹配中((byte)3),
    战斗中((byte)4),
    观战中((byte)5)
    ;
    private Byte status;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    NzqStatusEnum(Byte status) {
        this.status = status;
    }
}
