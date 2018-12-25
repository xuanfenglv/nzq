package com.xuanfeng.nzq.websocket.main.game.task;


import com.xuanfeng.nzq.websocket.main.game.constant.MatchGameQueue;
import com.xuanfeng.nzq.websocket.main.game.javabean.XfPair;
import com.xuanfeng.nzq.websocket.component.MatchRooms;
import com.xuanfeng.nzq.websocket.javabean.room.MatchTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.util.GameSessions;

/**
 * @description: 匹配赛线程
 * @author: lvxianqing
 * @create: 2018/12/21 13:45
 */

public class MatchGameTask extends Thread {

    private XfPair xfPair;

    public MatchGameTask(XfPair xfPair) {
        this.xfPair = xfPair;
    }

    @Override
    public void run() {
        // 掉线了一个
        if (GameSessions.constainsXf(xfPair.getBlack())) {
            if (GameSessions.constainsXf(xfPair.getWhite())) {
                // 放入房间
                MatchTwoPeopleRoom matchTwoPeopleRoom = new MatchTwoPeopleRoom(xfPair.getBlack(), xfPair.getWhite());
                MatchRooms.add(matchTwoPeopleRoom);
            } else {
                // 重新放入匹配队列
                MatchGameQueue.jumpAQueue(xfPair.getBlack());
            }
        } else {
            if (GameSessions.constainsXf(xfPair.getWhite())) {
                // 重新放入匹配队列
                MatchGameQueue.jumpAQueue(xfPair.getWhite());
            }
        }
    }
}
