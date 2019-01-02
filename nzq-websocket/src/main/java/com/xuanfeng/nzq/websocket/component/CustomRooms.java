package com.xuanfeng.nzq.websocket.component;

import com.xuanfeng.nzq.websocket.main.game.javabean.room.CustomTwoPeopleRoom;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 自定义房间
 * @author: lvxianqing
 * @create: 2018/12/28 16:27
 */

public class CustomRooms {
    private static Map<Long, CustomTwoPeopleRoom> roomMap = new HashMap<>(100);

    public static void add(CustomTwoPeopleRoom room) {
        roomMap.put(room.getId(), room);
    }

    public static CustomTwoPeopleRoom get(Long roomId) {
        return roomMap.get(roomId);
    }

    public static void delete(Long roomId) {
        roomMap.remove(roomId);
    }
}
