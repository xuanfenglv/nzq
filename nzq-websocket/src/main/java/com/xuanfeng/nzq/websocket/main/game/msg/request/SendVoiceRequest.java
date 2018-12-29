package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;

/**
 * @description: 发送语音请求
 * @author: lvxianqing
 * @create: 2018/12/29 16:15
 */

public class SendVoiceRequest extends RequestMsg {
    // 语音id
    private int id;
    @Override
    protected void doCheck(CheckParamResult result) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
