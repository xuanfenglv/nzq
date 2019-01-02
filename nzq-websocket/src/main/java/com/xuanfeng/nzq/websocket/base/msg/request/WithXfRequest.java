package com.xuanfeng.nzq.websocket.base.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.CheckParamResult;

/**
 * @description: 带有一个账号的请求
 * @author: lvxianqing
 * @create: 2019/01/02 17:58
 */

public class WithXfRequest extends RequestMsg{

    private Long xf;

    @Override
    protected void doCheck(CheckParamResult result) {
        if (xf == null) {
            result.setErrMsg("请求参数 'xf' 不能为空");
        }
    }

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }
}
