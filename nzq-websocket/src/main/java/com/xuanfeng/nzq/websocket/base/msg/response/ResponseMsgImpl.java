package com.xuanfeng.nzq.websocket.base.msg.response;

import com.xuanfeng.nzq.websocket.constant.ResponseEnum;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/10/10 20:36
 */

public class ResponseMsgImpl extends ResponseMsg {
    public ResponseMsgImpl() {
    }

    public ResponseMsgImpl(ResponseEnum responseEnum) {
        super(responseEnum);
    }

    public ResponseMsgImpl(ResponseEnum responseEnum,String errorDetail) {
        setResponseTypeWithDetail(responseEnum,errorDetail);
    }

    @Override
    protected int initMsgId() {
        return 0;
    }
}
