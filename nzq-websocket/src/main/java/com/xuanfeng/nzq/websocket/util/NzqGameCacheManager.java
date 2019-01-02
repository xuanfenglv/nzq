package com.xuanfeng.nzq.websocket.util;

import com.xuanfeng.nzq.websocket.javabean.NzqGameCache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: nzq游戏会话缓存
 * @author: lvxianqing
 * @create: 2019/01/02 18:28
 */

public class NzqGameCacheManager {
    // xf号
    private static ConcurrentHashMap<Long, NzqGameCache> users = new ConcurrentHashMap<Long, NzqGameCache>(1000);

    public static boolean constains(long xf) {
        return users.contains(xf);
    }

    public static NzqGameCache get(long xf) {
        return users.get(xf);
    }

    public static void add(long xf, NzqGameCache NzqGameCache) {
        users.put(xf, NzqGameCache);
    }
    public static void remove(long xf) {
        users.remove(xf);
    }
}
