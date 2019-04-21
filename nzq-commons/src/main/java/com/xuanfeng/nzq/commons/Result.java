package com.xuanfeng.nzq.commons;

import com.alibaba.fastjson.JSON;

/**
 * @description: http请求的result
 * @author: lvxianqing
 * @create: 2018/11/22 14:22
 */

public class Result<T> {
    // 响应结果
    private int ret;
    // 错误消息
    private String errorMsg;
    // 错误细节
    private String errorDatil;
    // 返回数据
    private T data;

    public Result(RetEnum retEnum, String errorDatil) {
        this.ret = retEnum.getRet();
        this.errorMsg = retEnum.getDesc();
        this.errorDatil = errorDatil;
    }

    public Result(T data) {
        this.ret = 1;
        this.data = data;
    }

    public Result() {
        this.ret = 1;
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

    public String getErrorDatil() {
        return errorDatil;
    }

    public void setErrorDatil(String errorDatil) {
        this.errorDatil = errorDatil;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);

    }
}
