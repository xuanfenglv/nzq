package com.xuanfeng.nzq.websocket.main.game.msg.common;

/**
 * @description: 下棋结果
 * @author: lvxianqing
 * @create: 2018/12/27 20:31
 */

public class DropChessResult {
    private byte row;
    private byte column;
    private int color;
    // 0=继续，1=胜利，2=平局
    private int type;

    public byte getRow() {
        return row;
    }

    public void setRow(byte row) {
        this.row = row;
    }

    public byte getColumn() {
        return column;
    }

    public void setColumn(byte column) {
        this.column = column;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
