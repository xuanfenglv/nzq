package com.xuanfeng.nzq.websocket.main.game.constant;

public enum NzqStatus {
//    0=离线 1=闲逛 2=房间中 3=匹配中 4=游戏中 5=观战中;

    离线(0),
    闲逛(1),
    房间中(2),
    匹配中(3),
    游戏中(4),
    观战中(5),
    ;

    private int status;

    NzqStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


}
