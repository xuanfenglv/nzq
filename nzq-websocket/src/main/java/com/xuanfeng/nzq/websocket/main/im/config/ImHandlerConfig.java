package com.xuanfeng.nzq.websocket.main.im.config;

import com.xuanfeng.nzq.websocket.base.manager.base.HandlerManager;
import com.xuanfeng.nzq.websocket.base.manager.base.MainHandler;
import com.xuanfeng.nzq.websocket.main.im.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 聊天相关处理器的配置
 * @author: lvxianqing
 * @create: 2018/09/30 18:05
 */
@Configuration
public class ImHandlerConfig {

    @Bean("imMainHandler")
    public MainHandler mainHandler() {
        MainHandler mainHandler = new MainHandler();
        mainHandler.setHandlerManager(manager());
        return mainHandler;
    }
    @Bean(name = "imHandlerManager")
    public HandlerManager manager() {
        HandlerManager chatHandlerManager = new HandlerManager();

        chatHandlerManager.putAHandler(1,h1_im());
        chatHandlerManager.putAHandler(2,h2_im());
        chatHandlerManager.putAHandler(8,h8_im());
        chatHandlerManager.putAHandler(9,h9_im());

        chatHandlerManager.putAHandler(10,h10_im());
        chatHandlerManager.putAHandler(11,h11_im());

        return chatHandlerManager;
    }
    @Bean
    public IM_H1 h1_im() {
        return new IM_H1();
    }
    @Bean
    public IM_H2 h2_im() {
        return new IM_H2();
    }
    @Bean
    public IM_H8 h8_im() {
        return new IM_H8();
    }
    @Bean
    public IM_H9 h9_im() {
        return new IM_H9();
    }
    @Bean
    public IM_H10 h10_im() {
        return new IM_H10();
    }

    @Bean
    public IM_H11 h11_im() {
        return new IM_H11();
    }

}
