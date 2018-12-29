package com.xuanfeng.nzq.websocket.msg.base;

/**
 * @description: websocket 推送消息
 * @author: lvxianqing
 * @create: 2018/11/23 14:41
 */

public class PushMsg<T> extends WsMsg{
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
