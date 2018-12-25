package com.xuanfeng.nzq.websocket.config;

import com.xuanfeng.nzq.websocket.base.manager.base.HandlerManager;
import com.xuanfeng.nzq.websocket.base.manager.base.MainHandler;
import com.xuanfeng.nzq.websocket.main.im.process.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 聊天相关处理器的配置
 * @author: lvxianqing
 * @create: 2018/09/30 18:05
 */
@Configuration
public class ChatConfig {

    @Bean("chatMainHandler")
    public MainHandler mainHandler() {
        MainHandler mainHandler = new MainHandler();
        mainHandler.setHandlerManager(manager());
        return mainHandler;
    }
    @Bean(name = "chatHandlerManager")
    public HandlerManager manager() {
        HandlerManager chatHandlerManager = new HandlerManager();

        chatHandlerManager.putAHandler(1,h1());
        chatHandlerManager.putAHandler(2,h2());
        chatHandlerManager.putAHandler(8,h8());
        chatHandlerManager.putAHandler(9,h9());

        chatHandlerManager.putAHandler(10,h10());
        chatHandlerManager.putAHandler(11,h11());

        return chatHandlerManager;
    }
    @Bean
    public InitAccountHandler h1() {
        return new InitAccountHandler();
    }
    @Bean
    public SendTextMsgHandler h2() {
        return new SendTextMsgHandler();
    }
    @Bean
    public SendFriendApplicationHandler h8() {
        return new SendFriendApplicationHandler();
    }
    @Bean
    public AgreeFriendApplicationHandler h9() {
        return new AgreeFriendApplicationHandler();
    }
    @Bean
    public RejectFriendApplicationHandler h10() {
        return new RejectFriendApplicationHandler();
    }

    @Bean
    public DeleteFriendHandler h11() {
        return new DeleteFriendHandler();
    }

}
