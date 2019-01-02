package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;

/**
 * @description: 发表情请求
 * @author: lvxianqing
 * @create: 2018/12/29 15:48
 */

public class SendEmotionRequest extends RequestMsg {
    // 表情id
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
