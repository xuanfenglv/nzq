package com.xuanfeng.nzq.commons.msg.request;

import com.xuanfeng.nzq.commons.msg.CheckParamResult;
import com.xuanfeng.nzq.commons.msg.base.WsMsg;
/**
 * @description: 请求消息
 * @author: lvxianqing
 * @create: 2018/09/30 15:28
 */

public abstract class RequestMsg extends WsMsg {

    public CheckParamResult checkParams() {
        CheckParamResult result = new CheckParamResult();
        doCheck(result);
        return result;
    }
    protected abstract void doCheck(CheckParamResult result);

}
