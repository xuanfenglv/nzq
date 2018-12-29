package com.xuanfeng.nzq.websocket.msg.response;

import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.websocket.msg.base.PushMsg;

/**
 * @description: websocket响应消息
 * @author: lvxianqing
 * @create: 2018/09/30 15:53
 */

public class ResponseMsg<T> extends PushMsg<T> {
    private byte type = 0;
    private int ret = 1;
    private String errorMsg;
    private String errorDetail;

    public ResponseMsg() {
    }

    public ResponseMsg(int msgId,T data) {
        setMsgId(msgId);
        setData(data);
    }
    public ResponseMsg(T data) {
        setData(data);
    }


    public ResponseMsg(RetEnum responseEnum) {
        ret = responseEnum.getRet();
        errorMsg = responseEnum.getDesc();
    }


    public ResponseMsg(RetEnum responseEnum, String errorDetail) {
        ret = responseEnum.getRet();
        errorMsg = responseEnum.getDesc();
        this.errorDetail = errorDetail;
    }


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
