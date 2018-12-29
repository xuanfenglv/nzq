package com.xuanfeng.nzq.websocket.main.game.constant;

public enum ChessColor {
    BLACK(1),
    WHITE(2),
    RED(3);
    private int id;

    ChessColor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
