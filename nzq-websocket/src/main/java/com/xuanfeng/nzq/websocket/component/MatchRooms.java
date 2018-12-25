package com.xuanfeng.nzq.websocket.component;

import com.xuanfeng.nzq.websocket.javabean.room.MatchTwoPeopleRoom;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 匹配房间s
 * @author: lvxianqing
 * @create: 2018/12/21 17:56
 */
// TODO: 2018/12/21 room gc
public class MatchRooms {
    private static Map<Long, MatchTwoPeopleRoom> roomMap = new HashMap<>(100);

    public static void add(MatchTwoPeopleRoom room) {
        roomMap.put(room.getId(), room);
        // 房间开始工作
        room.run();
    }

    public static MatchTwoPeopleRoom get(Long roomId) {
        return roomMap.get(roomId);
    }

    public static void delete(Long roomId) {
        roomMap.remove(roomId);
    }
}
