package com.xuanfeng.nzq.websocket.javabean;

import com.xuanfeng.nzq.domain.constant.UserStatusEnum;

import java.util.Set;

/**
 * @description: 用户在线缓存（可移到redis）
 * @author: lvxianqing
 * @create: 2018/09/30 17:33
 */

public class IMCache {
    private long xf;
    private Set<Long> friendXf;

    // 当前状态
    private UserStatusEnum imStatus;

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

    public UserStatusEnum getImStatus() {
        return imStatus;
    }

    public void setImStatus(UserStatusEnum imStatus) {
        this.imStatus = imStatus;
    }
}
