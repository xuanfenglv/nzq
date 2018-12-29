package com.xuanfeng.nzq.websocket.component;

import com.alibaba.fastjson.JSON;
import com.xuanfeng.nzq.domain.constant.NzqStatusEnum;
import com.xuanfeng.nzq.service.UserService;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.msg.notice.StatusChangeNotice;
import com.xuanfeng.nzq.websocket.util.ImSessions;
import com.xuanfeng.nzq.websocket.util.UserManager;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
     *
     * 更新玩家状态
     * @param xf
     * @param statusEnum
     */
    // TODO: 2018/12/21 异步处理
    public static void changeStatus(long xf, NzqStatusEnum statusEnum) {
        // 修改状态
        userService.changeNzqStatus(xf, statusEnum);
        // 向好友推送状态变化
        UserCache userCache = UserManager.getUserCache(xf);
        userCache.setNzqStatusEnum(statusEnum);
        // 避免多次序列化
        String statusChangeMessage = JSON.toJSONString(WsResultUtil.createNoticeResult(GameMsgId.状态变化,new StatusChangeNotice(xf,statusEnum)));
        userCache.getFriendXf().forEach(friendXf -> {
            try {
                ImSessions.sendMsgToXf(friendXf, statusChangeMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
