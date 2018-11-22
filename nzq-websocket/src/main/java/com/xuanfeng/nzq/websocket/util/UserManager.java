package com.xuanfeng.nzq.websocket.util;

import com.xuanfeng.nzq.websocket.javabean.UserCache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 用户管理器
 * @author: lvxianqing
 * @create: 2018/09/30 17:32
 */

public class UserManager {
    // xf号
    private static ConcurrentHashMap<Long, UserCache> users = new ConcurrentHashMap<Long, UserCache>(1000);

    public static boolean constains(long xf) {
        return users.contains(xf);
    }

    public static UserCache getUserCache(long xf) {
        return users.get(xf);
    }

    public static void add(long xf, UserCache userCache) {
        users.put(xf, userCache);
    }
    public static void remove(long xf) {
        users.remove(xf);
    }
}
