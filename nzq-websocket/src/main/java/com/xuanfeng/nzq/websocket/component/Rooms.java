package com.xuanfeng.nzq.websocket.component;


import com.xuanfeng.nzq.websocket.main.game.javabean.room.base.BaseRoom;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 匹配房间s
 * @author: lvxianqing
 * @create: 2018/12/21 17:56
 */
// TODO: 2018/12/21 room gc
public class Rooms {
    private static Map<Long, BaseRoom> roomMap = new HashMap<>(100);

    public static void add(BaseRoom room) {
        roomMap.put(room.getId(), room);

    }

    public static BaseRoom get(Long roomId) {
        return roomMap.get(roomId);
    }

    public static void delete(Long roomId) {
        roomMap.remove(roomId);
    }
}
