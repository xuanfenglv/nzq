package com.xuanfeng.nzq.websocket.config;

import com.xuanfeng.nzq.websocket.main.chat.ChatHandlerManager;
import com.xuanfeng.nzq.websocket.main.chat.process.InitAccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 聊天相关处理器的配置
 * @author: lvxianqing
 * @create: 2018/09/30 18:05
 */
@Configuration
public class ChatConfig {

    @Bean
    public ChatHandlerManager manager() {
        ChatHandlerManager chatHandlerManager = new ChatHandlerManager();

        chatHandlerManager.putAHandler(1,h1());

        return chatHandlerManager;
    }
    @Bean
    public InitAccountHandler h1() {
        return new InitAccountHandler();
    }

}
