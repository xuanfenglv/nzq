package com.xuanfeng.nzq.websocket.util;

import com.xuanfeng.nzq.websocket.javabean.IMCache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 用户管理器
 * @author: lvxianqing
 * @create: 2018/09/30 17:32
 */

public class IMCacheManager {
    // xf号
    private static ConcurrentHashMap<Long, IMCache> users = new ConcurrentHashMap<Long, IMCache>(1000);

    public static boolean constains(long xf) {
        return users.contains(xf);
    }

    public static IMCache get(long xf) {
        return users.get(xf);
    }

    public static void add(long xf, IMCache IMCache) {
        users.put(xf, IMCache);
    }
    public static void remove(long xf) {
        users.remove(xf);
    }
}
