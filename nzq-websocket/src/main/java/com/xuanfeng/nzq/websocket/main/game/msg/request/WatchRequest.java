package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;

/**
 * @description: 观战请求
 * @author: lvxianqing
 * @create: 2018/12/28 15:08
 */

public class WatchRequest extends RequestMsg {
    private Long roomId;

    @Override
    protected void doCheck(CheckParamResult result) {

    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
