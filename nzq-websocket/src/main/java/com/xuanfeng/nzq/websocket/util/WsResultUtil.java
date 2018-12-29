package com.xuanfeng.nzq.websocket.util;

import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.constant.ImMsgId;
import com.xuanfeng.nzq.websocket.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.msg.response.ResponseMsg;

/**
 * @description: Websocket 响应消息工具类
 * @author: lvxianqing
 * @create: 2018/11/23 15:08
 */

public class WsResultUtil {
    private static ResponseMsg result= new ResponseMsg();

    public static ResponseMsg createRespSuccessResult() {
        return result;
    }

    public static ResponseMsg createRespSuccessResult(Object data) {
        return new ResponseMsg(data);
    }

    public static ResponseMsg createRespFailedResult(RetEnum retEnum, String errorDatil) {
        return new ResponseMsg(retEnum, errorDatil);
    }

    public static NoticeMsg createNoticeResult(int msgId, Object data) {
        return new NoticeMsg(msgId,data);
    }
    public static NoticeMsg createNoticeResult(GameMsgId gameMsgId, Object data) {
        return new NoticeMsg(gameMsgId,data);
    }

    public static NoticeMsg createNoticeResult(ImMsgId msgId, Object data) {
        return new NoticeMsg(msgId.getMsgId(),data);
    }

}
