package com.xuanfeng.nzq.websocket.main.game.constant;

public enum DropResult {
    GO_ON(1,"继续"),
    WIN(2,"胜利"),
    HE(3,"和局");
    private int id;
    private String desc;

    DropResult(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
