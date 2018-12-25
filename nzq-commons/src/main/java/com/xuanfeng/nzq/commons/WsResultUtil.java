package com.xuanfeng.nzq.commons;

import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;

/**
 * @description: Websocket 响应消息工具类
 * @author: lvxianqing
 * @create: 2018/11/23 15:08
 */

public class WsResultUtil {
    private static ResponseMsg result= new ResponseMsg();

    public static ResponseMsg createSuccessResult() {
        return result;
    }

    public static ResponseMsg createSuccessResult(Object data) {
        return new ResponseMsg(data);
    }

    public static ResponseMsg createFailedResult(RetEnum retEnum, String errorDatil) {
        return new ResponseMsg(retEnum, errorDatil);
    }
}
