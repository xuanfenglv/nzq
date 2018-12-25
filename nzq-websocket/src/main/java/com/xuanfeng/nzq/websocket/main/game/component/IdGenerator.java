package com.xuanfeng.nzq.websocket.main.game.component;

/**
 * @description: id生成器
 * @author: lvxianqing
 * @create: 2018/12/24 19:06
 */

public class IdGenerator {
    private static volatile long matchRommId = 10001l;

    public static long getMatchRommId() {
        return matchRommId++;
    }

}
