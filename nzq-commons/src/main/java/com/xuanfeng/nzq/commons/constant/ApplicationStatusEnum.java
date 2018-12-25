package com.xuanfeng.nzq.commons.constant;

/**
 * @description: 好友申请状态枚举
 * @author: lvxianqing
 * @create: 2018/11/23 17:57
 */

public enum ApplicationStatusEnum {
    已发送申请((byte)0),
    已同意((byte)1),
    已拒绝((byte)2)
    ;

    private Byte status;

    public Byte getStatus() {
        return status;
    }

    ApplicationStatusEnum(Byte status) {
        this.status = status;
    }
}
