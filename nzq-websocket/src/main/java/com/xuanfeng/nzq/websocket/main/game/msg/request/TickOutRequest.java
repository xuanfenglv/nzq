package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;

/**
 * @description: 房主踢人请求
 * @author: lvxianqing
 * @create: 2018/12/29 15:26
 */

public class TickOutRequest extends RequestMsg {

    // 被踢的玩家账号
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
