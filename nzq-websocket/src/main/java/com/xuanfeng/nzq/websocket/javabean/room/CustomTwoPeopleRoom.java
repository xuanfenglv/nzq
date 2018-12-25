package com.xuanfeng.nzq.websocket.javabean.room;

import com.xuanfeng.nzq.websocket.javabean.room.base.BaseTwoPeopleRoom;

/**
 * @description: 自定义二人房间
 * @author: lvxianqing
 * @create: 2018/12/21 14:57
 */

public class CustomTwoPeopleRoom extends BaseTwoPeopleRoom {

    // 最后一次修改时间（用来回收长时间不用的房间）
    private long lastModifyTime;

    /**
     * 进入房间
     * @param xf
     */
    public void enterRoom(Long xf) {

    }

}
