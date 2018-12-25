package com.xuanfeng.nzq.websocket.main.game.pool;

import com.xuanfeng.nzq.websocket.main.game.javabean.XfPair;
import com.xuanfeng.nzq.websocket.main.game.task.MatchGameTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 匹配线程池
 * @author: lvxianqing
 * @create: 2018/11/30 10:56
 */
public class MatchThreadPool {

    private static Logger logger = LoggerFactory.getLogger(MatchThreadPool.class);

    // 线程池
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void execute(XfPair xfPair) {
        logger.info("[匹配线程池]=>[success] [black:{},white:{}]", xfPair.getBlack(), xfPair.getWhite());
        MatchGameTask matchGameTask = new MatchGameTask(xfPair);
        executorService.execute(matchGameTask);
    }
}
