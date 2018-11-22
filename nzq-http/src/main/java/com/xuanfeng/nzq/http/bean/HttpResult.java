package com.xuanfeng.nzq.http.bean;

public class HttpResult<T> {
    private int ret;
    private String errMsg;
    private T data;

    public HttpResult(int ret, String errMsg, T data) {
        this.ret = ret;
        this.errMsg = errMsg;
        this.data = data;
    }

    public HttpResult(T data) {
        this.ret = 1;
        this.data = data;
    }

    public HttpResult() {
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
