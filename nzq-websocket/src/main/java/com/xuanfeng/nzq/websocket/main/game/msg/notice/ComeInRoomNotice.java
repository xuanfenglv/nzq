package com.xuanfeng.nzq.websocket.main.game.msg.notice;

/**
 * @description: 进入房间通知
 * @author: lvxianqing
 * @create: 2018/12/29 14:26
 */

public class ComeInRoomNotice {
    // 进入房间玩家id
    private Long xf;

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }
}
