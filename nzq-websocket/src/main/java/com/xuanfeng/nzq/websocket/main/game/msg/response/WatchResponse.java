package com.xuanfeng.nzq.websocket.main.game.msg.response;

/**
 * @description: 观战通知
 * @author: lvxianqing
 * @create: 2018/12/28 15:24
 */

public class WatchResponse {
    private int[][] board;
    private int total;
    private int modCount;

    public WatchResponse(int[][] board, int total, int modCount) {
        this.board = board;
        this.total = total;
        this.modCount = modCount;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getModCount() {
        return modCount;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }
}
