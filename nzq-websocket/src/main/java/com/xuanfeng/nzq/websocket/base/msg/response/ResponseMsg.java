package com.xuanfeng.nzq.websocket.base.msg.response;

import com.xuanfeng.nzq.websocket.constant.ResponseEnum;

/**
 * @description: websocket响应消息
 * @author: lvxianqing
 * @create: 2018/09/30 15:53
 */

public abstract class ResponseMsg {
    private byte type=0;
    private int ret=1;
    private int msgId;
    private String errorMsg;
    private String errorDetail;
    {
        setMsgId(initMsgId());
    }
    public ResponseMsg() {
    }

    public ResponseMsg(ResponseEnum responseEnum) {
        ret=responseEnum.getRet();
        errorMsg=responseEnum.getTip();
    }

    public void setResponseType(ResponseEnum responseEnum) {
        ret=responseEnum.getRet();
        errorMsg=responseEnum.getTip();
    }

    public void setResponseTypeWithDetail(ResponseEnum responseEnum,String errorDetail) {
        ret=responseEnum.getRet();
        errorMsg=responseEnum.getTip();
        this.errorDetail=errorDetail;
    }

    protected abstract int initMsgId();
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
