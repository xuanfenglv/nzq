package com.xuanfeng.nzq.websocket.main.game.pool;

import com.xuanfeng.nzq.websocket.main.game.msg.common.DropChessResult;
import com.xuanfeng.nzq.websocket.main.game.task.WatchNoticeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 匹配线程池
 * @author: lvxianqing
 * @create: 2018/11/30 10:56
 */
public class WatchThreadPool {

    private static Logger logger = LoggerFactory.getLogger(WatchThreadPool.class);

    // 线程池
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void push(List<Session> audience, DropChessResult dropChessResult) {
        WatchNoticeTask task = new WatchNoticeTask(audience, dropChessResult);
        executorService.execute(task);
    }
}
