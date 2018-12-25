package com.xuanfeng.nzq.websocket.main.game.constant;


import com.xuanfeng.nzq.websocket.main.game.javabean.XfPair;
import com.xuanfeng.nzq.websocket.main.game.pool.MatchThreadPool;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 匹配赛队列
 * @author: lvxianqing
 * @create: 2018/12/21 12:27
 */

public class MatchGameQueue {
    private static final List<Long> queue = new LinkedList<>();

    public static int size() {
        return queue.size();
    }

    public static void match() {
        if (queue.size() >= 2) {
            XfPair pair = new XfPair();
            pair.setWhite(queue.remove(0));
            pair.setBlack(queue.remove(0));
            MatchThreadPool.execute(pair);
        }
    }
    /**
     * 插队
     * @param xf
     */
    public static void jumpAQueue(Long xf) {
        if (queue.contains(xf)) {
            // 已在队列中
        } else {
            queue.add(0,xf);
            match();
        }
    }

    /**
     * 入栈
     * @param xf
     */
    public static void push(Long xf) {
        if (queue.contains(xf)) {
            // 已在队列中
        } else {
            queue.add(xf);
            match();
        }
    }


}
