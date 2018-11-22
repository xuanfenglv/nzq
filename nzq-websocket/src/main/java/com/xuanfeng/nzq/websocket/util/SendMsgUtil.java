package com.xuanfeng.nzq.websocket.util;

import com.alibaba.fastjson.JSON;
import com.xuanfeng.nzq.websocket.base.msg.push.PushMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 发送消息工具类
 * @author: lvxianqing
 * @create: 2018/09/30 17:10
 */

public class SendMsgUtil {

    public static void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public static void sendMessage(Session session, ResponseMsg message) throws IOException {
        sendMessage(session, JSON.toJSONString(message));
    }

    public static void sendMessage(Session session, PushMsg message) throws IOException {
        sendMessage(session, JSON.toJSONString(message));
    }
}
