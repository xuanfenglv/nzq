package com.xuanfeng.nzq.websocket.main.game.msg.notice;

/**
 * @description: 发送表情通知
 * @author: lvxianqing
 * @create: 2018/12/29 15:50
 */

public class SendEmotionNotice {
    private Long xf;
    private int id;

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
