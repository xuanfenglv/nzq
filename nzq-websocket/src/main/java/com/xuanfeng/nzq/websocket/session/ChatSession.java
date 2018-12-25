package com.xuanfeng.nzq.websocket.session;

import com.xuanfeng.nzq.domain.constant.UserStatusEnum;
import com.xuanfeng.nzq.service.UserService;
import com.xuanfeng.nzq.websocket.base.manager.base.MainHandler;
import com.xuanfeng.nzq.websocket.main.im.component.StatusHandler;
import com.xuanfeng.nzq.websocket.util.ChatSessions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@Component
@ServerEndpoint("/chatserver")
public class ChatSession {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // xf号
    private long xf;


    private static MainHandler chatMainHandler;

    private static UserService loginService;

    private static StatusHandler statusHandler;

    @Autowired
    @Qualifier("chatMainHandler")
    public void setChatMainHandler(MainHandler chatMainHandler) {
        ChatSession.chatMainHandler = chatMainHandler;
    }
    @Autowired
    public void setLoginService(UserService loginService) {
        ChatSession.loginService = loginService;
    }
    @Autowired
    public void setStatusHandler(StatusHandler statusHandler) {
        ChatSession.statusHandler = statusHandler;
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {

        System.out.println("有新连接加入！当前在线人数为" + ChatSessions.getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        selfOffline();
    }


    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
//		error.printStackTrace();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        chatMainHandler.process(message, this, session);
    }

    public void selfOffline() throws IOException {
        ChatSessions.removeXf(this.xf);
        logger.info("下线用户是：{},当前在线人数为:{}", this.xf,ChatSessions.getOnlineCount());
        // 推送下线消息
        statusHandler.changeStatus(this.xf,UserStatusEnum.离线);
    }



    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

}