package com.xuanfeng.nzq.websocket.javabean;

import com.xuanfeng.nzq.domain.constant.NzqStatusEnum;
import com.xuanfeng.nzq.websocket.constant.RoomType;

import java.util.Set;

/**
 * @description: nzq游戏在线缓存（可移到redis）
 * @author: lvxianqing
 * @create: 2018/09/30 17:33
 */

public class NzqGameCache {
    private long xf;
    private Set<Long> friendXf;
    private Long roomId;
    private RoomType roomType;
    // 禁赛截止时间
    private Long suspendDeadline;
    // nzq游戏中的状态
    private NzqStatusEnum nzqStatusEnum;

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

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public NzqStatusEnum getNzqStatusEnum() {
        return nzqStatusEnum;
    }

    public void setNzqStatusEnum(NzqStatusEnum nzqStatusEnum) {
        this.nzqStatusEnum = nzqStatusEnum;
    }
}
