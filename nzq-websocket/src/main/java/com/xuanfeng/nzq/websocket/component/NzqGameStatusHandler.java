package com.xuanfeng.nzq.websocket.component;

import com.alibaba.fastjson.JSON;
import com.xuanfeng.nzq.domain.constant.NzqStatusEnum;
import com.xuanfeng.nzq.service.UserService;
import com.xuanfeng.nzq.websocket.javabean.NzqGameCache;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.msg.notice.StatusChangeNotice;
import com.xuanfeng.nzq.websocket.util.NzqGameCacheManager;
import com.xuanfeng.nzq.websocket.util.NzqGameSessions;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 状态变化处理器
 * @author: lvxianqing
 * @create: 2018/11/23 11:43
 */
@Component
public class NzqGameStatusHandler {


    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        NzqGameStatusHandler.userService = userService;
    }

    /**
     * 更新玩家状态
     *
     * @param xf
     * @param statusEnum
     */
    // TODO: 2018/12/21 异步处理
    public static void changeStatus(long xf, NzqStatusEnum statusEnum) {
        // 修改状态
        userService.changeNzqStatus(xf, statusEnum);
        // 向好友推送状态变化
        NzqGameCache cache = NzqGameCacheManager.get(xf);
        cache.setNzqStatusEnum(statusEnum);
        // 避免多次序列化
        String statusChangeMessage = JSON.toJSONString(WsResultUtil.createNoticeResult(GameMsgId.状态变化, new StatusChangeNotice(xf, statusEnum)));
        cache.getFriendXf().forEach(friendXf -> {
            NzqGameSessions.sendMsgToXf(friendXf, statusChangeMessage);

        });

    }
}
