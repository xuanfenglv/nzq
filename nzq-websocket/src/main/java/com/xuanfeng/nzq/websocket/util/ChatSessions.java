package com.xuanfeng.nzq.websocket.util;

import com.xuanfeng.nzq.websocket.base.msg.push.PushMsg;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 聊天会话连接
 * @author: lvxianqing
 * @create: 2018/09/30 15:17
 */

public class ChatSessions {
    // xf号
    private static ConcurrentHashMap<Long, Session> sessions = new ConcurrentHashMap<Long, Session>(1000);

    public static boolean constainsXf(long xf) {
        return sessions.contains(xf);
    }

    public static void sendMsgToXf(long xf, PushMsg msg)  throws IOException {
        Session session = sessions.get("xf");
        if (session!=null) {
            SendMsgUtil.sendMessage(session, msg);
        }
    }

    public static void sendMsgToXf(long xf, String msg)  throws IOException {
        Session session = sessions.get("xf");
        if (session!=null) {
            SendMsgUtil.sendMessage(session, msg);
        }
    }
    public static void addxf(long xf, Session session) {
        sessions.put(xf, session);
    }
    public static void removeXf(long xf) {
        sessions.remove(xf);
    }

    public static long getOnlineCount() {
        return sessions.size();
    }
}
