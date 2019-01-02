package com.xuanfeng.nzq.websocket.util.base;

import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: session 映射工具类
 * @author: lvxianqing
 * @create: 2018/12/21 13:56
 */

public class SessionMapping {
    // xf号
    private static ConcurrentHashMap<Long, Session> sessions = new ConcurrentHashMap<Long, Session>(1000);

    public static boolean constainsXf(long xf) {
        return sessions.contains(xf);
    }

    public static Session get(Long xf) {
        return sessions.get(xf);
    }

    public static void sendMsgToXf(long xf, NoticeMsg msg)  throws IOException {
        Session session = sessions.get(xf);
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
