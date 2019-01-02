package com.xuanfeng.nzq.websocket.main.game.javabean.room.base;

/**
 * @description: 自定义房间
 * @author: lvxianqing
 * @create: 2018/12/29 15:28
 */

public interface CustomRoom {
    boolean tickOut(Long owner, Long xf);
}
