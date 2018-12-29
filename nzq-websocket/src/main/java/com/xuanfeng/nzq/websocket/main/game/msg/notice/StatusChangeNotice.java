package com.xuanfeng.nzq.websocket.main.game.msg.notice;

import com.xuanfeng.nzq.domain.constant.NzqStatusEnum;

/**
 * @description: 下线通知
 * @author: lvxianqing
 * @create: 2018/09/30 18:29
 */

public class StatusChangeNotice {

    private long xf;
    private Byte status;

    public StatusChangeNotice(long xf, NzqStatusEnum statusEnum) {
        this.xf = xf;
        this.status = statusEnum.getStatus();
    }

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

}
