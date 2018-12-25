package com.xuanfeng.nzq.websocket.main.im.component;

import com.alibaba.fastjson.JSON;
import com.xuanfeng.nzq.domain.constant.UserStatusEnum;
import com.xuanfeng.nzq.service.UserService;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.main.im.msg.push.StatusChangeNotice;
import com.xuanfeng.nzq.websocket.util.ChatSessions;
import com.xuanfeng.nzq.websocket.util.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description: 状态变化处理器
 * @author: lvxianqing
 * @create: 2018/11/23 11:43
 */
@Component
public class StatusHandler {

    @Autowired
    private UserService userService;

    /**
     *
     * 更新玩家状态
     * @param xf
     * @param statusEnum
     */
    // TODO: 2018/12/21 异步处理
    public void changeStatus(long xf, UserStatusEnum statusEnum) {
        // 修改状态
        userService.changeStatus(xf, statusEnum);
        // 向好友推送状态变化
        UserCache userCache = UserManager.getUserCache(xf);
        // 避免多次序列化
        String statusChangeMessage = JSON.toJSONString(new StatusChangeNotice(xf,statusEnum));
        userCache.getFriendXf().forEach(friendXf -> {
            try {
                ChatSessions.sendMsgToXf(friendXf, statusChangeMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
