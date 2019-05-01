package com.xuanfeng.nzq.websocket.main.game.constant;

public enum Position {
    NOTHING(0),
    BLACK(1),
    WHITE(2),
    RED(3),
    ;


    private int id;

    Position(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
