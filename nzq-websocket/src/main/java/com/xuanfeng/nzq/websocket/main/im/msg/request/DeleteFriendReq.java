package com.xuanfeng.nzq.websocket.main.im.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;

/**
 * @description: 删除好友请求
 * @author: lvxianqing
 * @create: 2018/11/22 22:32
 */

public class DeleteFriendReq extends RequestMsg {
    // 被删除者炫封号
    private Long xf;

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }

    @Override
    protected void doCheck(CheckParamResult result) {

    }
}
