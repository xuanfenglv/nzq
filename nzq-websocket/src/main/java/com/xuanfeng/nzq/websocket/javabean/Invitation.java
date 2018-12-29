package com.xuanfeng.nzq.websocket.javabean;

/**
 * @description: 邀请
 * @author: lvxianqing
 * @create: 2018/12/28 18:29
 */

public class Invitation {
    // 发送邀请者账号
    private Long sendXf;
    // 被邀请者账号
    private Long receiveXf;
    private Long roomId;
    // 期限
    private long deadline = System.currentTimeMillis()+30* 1000;

    public boolean isValid() {
        if (System.currentTimeMillis() > deadline) {
            return false;
        }
        return true;
    }
    public Long getSendXf() {
        return sendXf;
    }

    public void setSendXf(Long sendXf) {
        this.sendXf = sendXf;
    }

    public Long getReceiveXf() {
        return receiveXf;
    }

    public void setReceiveXf(Long receiveXf) {
        this.receiveXf = receiveXf;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
