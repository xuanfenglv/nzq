package com.xuanfeng.nzq.websocket.javabean.room.base;

import com.xuanfeng.nzq.websocket.component.MatchRooms;
import com.xuanfeng.nzq.websocket.util.NzqUtil;

import javax.websocket.Session;
import java.util.Set;

/**
 * @description: 二人房间
 * @author: lvxianqing
 * @create: 2018/12/21 14:40
 */
public abstract class BaseTwoPeopleRoom {
    // 房间id
    protected Long id;
    protected Long black;
    protected Long white;
    protected Session blackSession;
    protected Session whiteSession;

    // 游戏开始时间
    private long beginTime;
    // 游戏结束时间
    private long endTime;

    // 观众连接
    private Set<Session> audience;
    // 棋盘数组
    protected int[][] board = new int[15][15];
    // 总棋数
    protected int total;
    //

    public void drop(Long xf, int row, int column) {
        if (!dropCheck(xf, row, column)) {
            return;
        }
        // 棋子类型
        int dropNum = xf.equals(black) ? 1 : 2;
        board[row][column] = dropNum;
        // 判断是否胜利
        boolean isWin = NzqUtil.isWin5(board, row, column);
        if (isWin) {
            endTime = System.currentTimeMillis();
            // TODO: 2018/12/21 存储比赛记录

            // TODO: 2018/12/21 销毁房间
            MatchRooms.delete(id);
        }
        // TODO: 2018/12/21 响应、推送 

        // TODO: 2018/12/21 向观战者推送

    }


    private boolean dropCheck(Long xf, int row, int column) {
        int yu = total % 2;
        Long nowDropPlayer = null;
        if (yu == 0) {
            nowDropPlayer = black;
        } else {
            nowDropPlayer = white;
        }

        if (!xf.equals(nowDropPlayer)) {
            // TODO: 2018/12/21 推送
            return false;
        }
        if (board[row][column] != 0) {
            // TODO: 2018/12/21 此处已有棋子
            return false;
        }
        return true;
    }

    //===========getter/setter================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Set<Session> getAudience() {
        return audience;
    }

    public void setAudience(Set<Session> audience) {
        this.audience = audience;
    }

    public Long getBlack() {
        return black;
    }

    public void setBlack(Long black) {
        this.black = black;
    }

    public Long getWhite() {
        return white;
    }

    public void setWhite(Long white) {
        this.white = white;
    }

    public Session getBlackSession() {
        return blackSession;
    }

    public void setBlackSession(Session blackSession) {
        this.blackSession = blackSession;
    }

    public Session getWhiteSession() {
        return whiteSession;
    }

    public void setWhiteSession(Session whiteSession) {
        this.whiteSession = whiteSession;
    }
}
