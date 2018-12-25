package com.xuanfeng.nzq.websocket.javabean;

import java.util.Set;

/**
 * @description: 用户在线缓存（可移到redis）
 * @author: lvxianqing
 * @create: 2018/09/30 17:33
 */

public class UserCache {
    private long xf;
    private Set<Long> friendXf;
    private Long roomId;
    // 禁赛截止时间
    private Long suspendDeadline;

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

    public Set<Long> getFriendXf() {
        return friendXf;
    }

    public void setFriendXf(Set<Long> friendXf) {
        this.friendXf = friendXf;
    }

    public Long getSuspendDeadline() {
        return suspendDeadline;
    }

    public void setSuspendDeadline(Long suspendDeadline) {
        this.suspendDeadline = suspendDeadline;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
