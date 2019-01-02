package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;

/**
 * @description: 发送房间内消息
 * @author: lvxianqing
 * @create: 2018/12/29 16:23
 */

public class RoomMsgRequest extends RequestMsg {
    private String text;
    @Override
    protected void doCheck(CheckParamResult result) {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
