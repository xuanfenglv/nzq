package com.xuanfeng.nzq.websocket.component;

/**
 * @description: id生成器
 * @author: lvxianqing
 * @create: 2018/12/24 19:06
 */

public class IdGenerator {
    private static volatile long id = 10001l;

    public static long getId() {
        return id++;
    }

}
