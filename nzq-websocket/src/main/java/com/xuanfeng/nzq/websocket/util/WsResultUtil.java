package com.xuanfeng.nzq.websocket.util;

import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.im.constant.ImMsgId;
import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;

/**
 * @description: Websocket 响应消息工具类
 * @author: lvxianqing
 * @create: 2018/11/23 15:08
 */

public class WsResultUtil {
    private static ResponseMsg result= new ResponseMsg();

    // 正确响应-无参数
    public static ResponseMsg createRespSuccessResult() {
        return result;
    }

    // 正确响应-有参数
    public static ResponseMsg createRespSuccessResult(Object data) {
        return new ResponseMsg(data);
    }

    // 错误响应
    public static ResponseMsg createRespFailedResult(RetEnum retEnum, String errorDatil) {
        return new ResponseMsg(retEnum, errorDatil);
    }
    // 游戏通知-无参数
    public static NoticeMsg createNoticeResult(GameMsgId gameMsgId) {
        return new NoticeMsg(gameMsgId);
    }

    // 游戏通知-有参数
    public static NoticeMsg createNoticeResult(GameMsgId gameMsgId, Object data) {
        return new NoticeMsg(gameMsgId,data);
    }

    // IM通知-无参数
    public static NoticeMsg createNoticeResult(ImMsgId msgId) {
        return new NoticeMsg(msgId);
    }

    // IM通知-有参数
    public static NoticeMsg createNoticeResult(ImMsgId msgId, Object data) {
        return new NoticeMsg(msgId.getMsgId(),data);
    }

}
