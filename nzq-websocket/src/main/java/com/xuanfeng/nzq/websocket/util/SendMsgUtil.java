package com.xuanfeng.nzq.websocket.util;

import com.alibaba.fastjson.JSON;
import com.xuanfeng.nzq.commons.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 发送消息工具类
 * @author: lvxianqing
 * @create: 2018/09/30 17:10
 */

public class SendMsgUtil {
    private static Logger logger = LoggerFactory.getLogger(SendMsgUtil.class);

    public static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.info("websocket 发送消息异常");
            e.printStackTrace();
        }
    }

    public static void sendMessage(Session session, ResponseMsg message) {
        sendMessage(session, JSON.toJSONString(message));
    }

    public static void sendMessage(Session session, NoticeMsg message) {
        sendMessage(session, JSON.toJSONString(message));
    }
}
