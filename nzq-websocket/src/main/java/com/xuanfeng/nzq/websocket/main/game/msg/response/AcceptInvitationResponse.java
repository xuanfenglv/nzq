package com.xuanfeng.nzq.websocket.main.game.msg.response;

/**
 * @description: 接受邀请响应
 * @author: lvxianqing
 * @create: 2018/12/29 14:36
 */

public class AcceptInvitationResponse {
    // 1=黑，2=白，3=红
    private int myPosition;
    private Long black;
    private Long white;

    public int getMyPosition() {
        return myPosition;
    }

    public void setMyPosition(int myPosition) {
        this.myPosition = myPosition;
    }

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
