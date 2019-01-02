package com.xuanfeng.nzq.websocket.main.game.msg.notice;

/**
 * @description: 进入房间通知
 * @author: lvxianqing
 * @create: 2018/12/29 14:26
 */

public class ComeInRoomNotice {
    // 进入房间玩家id
    private Long xf;
    // 位置
    private int position;

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
