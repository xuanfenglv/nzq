package com.xuanfeng.nzq.websocket.component;

import com.xuanfeng.nzq.websocket.javabean.Invitation;
import com.xuanfeng.nzq.websocket.main.game.component.IdGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 邀请管理器
 * @author: lvxianqing
 * @create: 2018/12/28 18:28
 */

public class Invitations {

    private static final Map<Long, Invitation> map = new HashMap<>(100);

    /**
     * 保存一条申请，并返回申请id
     * @param invitation
     * @return
     */
    public static Long add(Invitation invitation) {
        Long id = IdGenerator.getId();
        map.put(id, invitation);
        return id;
    }

    public static Invitation get(Long id) {
        return map.get(id);
    }
}
