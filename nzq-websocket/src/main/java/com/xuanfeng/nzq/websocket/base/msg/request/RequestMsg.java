package com.xuanfeng.nzq.websocket.base.msg.request;

import com.xuanfeng.nzq.websocket.result.CheckParamResult;

/**
 * @description: 请求消息
 * @author: lvxianqing
 * @create: 2018/09/30 15:28
 */

public abstract class RequestMsg {
    private int msgId;
    private String token;

    public CheckParamResult checkParams() {
        CheckParamResult result = new CheckParamResult();
        doCheck(result);
        return result;
    }
    protected abstract void doCheck(CheckParamResult result);

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
