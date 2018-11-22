package com.xuanfeng.nzq.websocket.main.chat.process;

import com.xuanfeng.nzq.domain.constant.UserStatusEnum;
import com.xuanfeng.nzq.service.UserService;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsgImpl;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.main.chat.msg.push.InitAccountPush;
import com.xuanfeng.nzq.websocket.main.chat.msg.request.InitAccountReq;
import com.xuanfeng.nzq.websocket.session.ChatSession;
import com.xuanfeng.nzq.websocket.util.ChatSessions;
import com.xuanfeng.nzq.websocket.util.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 初始化账号
 * @author: lvxianqing
 * @create: 2018/09/30 16:31
 */

public class InitAccountHandler extends IMsgHandler {
    @Autowired
    private UserService userService;

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) {
        InitAccountReq req = (InitAccountReq) message;
        ResponseMsgImpl responseMsg = new ResponseMsgImpl();

        if (ChatSessions.constainsXf(xf)) {
            try {
                ChatSessions.sendMsgToXf(xf, new InitAccountPush());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ChatSessions.removeXf(xf);
        }
        // 初始化用户信息
        UserCache userCache = new UserCache();
        // add thing

        UserManager.add(xf, userCache);
        ChatSessions.addxf(xf, session);
        ChatSession.sendOnlineNoticeToFriend(xf);
        // TODO: 2018/11/22 状态恢复 
        userService.changeStatus(xf, UserStatusEnum.闲逛中);
        System.out.println("上线用户：" + xf);

        return responseMsg;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return InitAccountReq.class;
    }
}
