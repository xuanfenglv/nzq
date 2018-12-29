package com.xuanfeng.nzq.websocket.main.game.msg.notice;

/**
 * @description: 房间消息通知
 * @author: lvxianqing
 * @create: 2018/12/29 16:28
 */

public class RoomMsgNotice {
    private Long xf;
    private String text;

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
