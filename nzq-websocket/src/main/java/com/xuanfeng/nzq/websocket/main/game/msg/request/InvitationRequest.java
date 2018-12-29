package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;

/**
 * @description: 邀请请求
 * @author: lvxianqing
 * @create: 2018/12/28 16:41
 */

public class InvitationRequest extends RequestMsg {
    // 邀请者账号
    private Long xf;

    @Override
    protected void doCheck(CheckParamResult result) {

    }

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }
}
