package com.xuanfeng.nzq.websocket.main.game.msg.notice;

/**
 * @description: 匹配成功推送
 * @author: lvxianqing
 * @create: 2018/12/21 18:24
 */

public class MatchNotice {
    private Long black;
    private Long white;

    public Long getBlack() {
        return black;
    }

    public void setBlack(Long black) {
        this.black = black;
    }

    public Long getWhite() {
        return white;
    }

    public void setWhite(Long white) {
        this.white = white;
    }
}
