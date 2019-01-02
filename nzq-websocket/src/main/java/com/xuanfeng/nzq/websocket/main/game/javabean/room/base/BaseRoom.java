package com.xuanfeng.nzq.websocket.main.game.javabean.room.base;

import com.xuanfeng.nzq.websocket.constant.RoomType;
import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;

/**
 * @description: 房间基类
 * @author: lvxianqing
 * @create: 2018/12/28 16:22
 */

public abstract class BaseRoom {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract RoomType getRoomType();

    public abstract void sendNoticeToOthers(Long xf,NoticeMsg noticeMsg);
}
