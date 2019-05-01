package com.xuanfeng.nzq.websocket.util;

import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 聊天会话连接
 * @author: lvxianqing
 * @create: 2018/09/30 15:17
 */

public class ImSessions {
    // xf号
    private static ConcurrentHashMap<Long, Session> sessions = new ConcurrentHashMap<Long, Session>(1000);

    public static boolean constainsXf(long xf) {
        return sessions.contains(xf);
    }

    public static Session get(Long xf) {
        return sessions.get(xf);
    }

    public static void sendMsgToXf(long xf, NoticeMsg msg) {
        Session session = sessions.get(xf);
        if (session!=null) {
            SendMsgUtil.sendMessage(session, msg);
        }
    }

    public static void sendMsgToXf(long xf, String msg) {
        Session session = sessions.get(xf);
        if (session!=null) {
            SendMsgUtil.sendMessage(session, msg);
        }
    }
    public static void addxf(Long xf, Session session) {
        sessions.put(xf, session);
    }
    public static void removeXf(Long xf) {
        sessions.remove(xf);
    }

    public static long getOnlineCount() {
        return sessions.size();
    }
}
