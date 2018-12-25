package com.xuanfeng.nzq.websocket.main.im.msg.response;

import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/11/23 11:23
 */

public class DeleteFriendResp extends ResponseMsg {
    private Long xf;

    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }
}
