package com.xuanfeng.nzq.websocket.main.im.msg.request;

import com.xuanfeng.nzq.commons.msg.CheckParamResult;
import com.xuanfeng.nzq.commons.msg.request.RequestMsg;

/**
 * @description: 初始化账号请求消息
 * @author: lvxianqing
 * @create: 2018/09/30 16:33
 */

public class InitAccountReq extends RequestMsg {
    private long xf;
    private String token;

    @Override
    protected void doCheck(CheckParamResult result) {
        if (xf == 0) {
            result.setErrMsg("账号为空");
        }
    }

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
