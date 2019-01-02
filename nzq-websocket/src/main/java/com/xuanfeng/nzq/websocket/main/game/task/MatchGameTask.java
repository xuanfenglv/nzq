package com.xuanfeng.nzq.websocket.main.game.task;


import com.xuanfeng.nzq.websocket.main.game.constant.MatchGameQueue;
import com.xuanfeng.nzq.websocket.main.game.javabean.XfPair;
import com.xuanfeng.nzq.websocket.component.Rooms;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.MatchTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.util.NzqGameSessions;

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
        if (NzqGameSessions.constainsXf(xfPair.getBlack())) {
            if (NzqGameSessions.constainsXf(xfPair.getWhite())) {
                // 放入房间
                MatchTwoPeopleRoom matchTwoPeopleRoom = new MatchTwoPeopleRoom(xfPair.getBlack(), xfPair.getWhite());
                Rooms.add(matchTwoPeopleRoom);
                // 房间开始工作
                matchTwoPeopleRoom.run();
            } else {
                // 重新放入匹配队列
                MatchGameQueue.jumpAQueue(xfPair.getBlack());
            }
        } else {
            if (NzqGameSessions.constainsXf(xfPair.getWhite())) {
                // 重新放入匹配队列
                MatchGameQueue.jumpAQueue(xfPair.getWhite());
            }
        }
    }
}
