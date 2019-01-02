package com.xuanfeng.nzq.websocket.main.game.config;

import com.xuanfeng.nzq.websocket.base.manager.base.HandlerManager;
import com.xuanfeng.nzq.websocket.base.manager.base.MainHandler;
import com.xuanfeng.nzq.websocket.main.game.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 聊天相关处理器的配置
 * @author: lvxianqing
 * @create: 2018/09/30 18:05
 */
@Configuration
public class NzqGameConfig {

    @Bean("nzqGameMainHandler")
    public MainHandler mainHandler() {
        MainHandler mainHandler = new MainHandler();
        mainHandler.setHandlerManager(manager());
        return mainHandler;
    }

    @Bean(name = "nzqGameHandlerManager")
    public HandlerManager manager() {
        HandlerManager handlerManager = new HandlerManager();

        handlerManager.putAHandler(1,h1());
        handlerManager.putAHandler(1,h2());
        handlerManager.putAHandler(1,h3());
        handlerManager.putAHandler(1,h4());
        handlerManager.putAHandler(1,h5());
        handlerManager.putAHandler(1,h6());
        handlerManager.putAHandler(1,h7());
        handlerManager.putAHandler(1,h8());
        handlerManager.putAHandler(1,h9());
        handlerManager.putAHandler(1,h10());
        handlerManager.putAHandler(1,h11());
        handlerManager.putAHandler(1,h12());
        handlerManager.putAHandler(1,h13());
        handlerManager.putAHandler(1,h14());
        handlerManager.putAHandler(1,h15());
        handlerManager.putAHandler(1,h16());
        handlerManager.putAHandler(1,h17());
        handlerManager.putAHandler(1,h18());
        handlerManager.putAHandler(1,h19());
        handlerManager.putAHandler(1,h20());

        return handlerManager;
    }
    @Bean
    public H1 h1() {
        return new H1();
    }
    @Bean
    public H2 h2() {
        return new H2();
    }
    @Bean
    public H3 h3() {
        return new H3();
    }
    @Bean
    public H4 h4() {
        return new H4();
    }
    @Bean
    public H5 h5() {
        return new H5();
    }
    @Bean
    public H6 h6() {
        return new H6();
    }
    @Bean
    public H7 h7() {
        return new H7();
    }
    @Bean
    public H8 h8() {
        return new H8();
    }
    @Bean
    public H9 h9() {
        return new H9();
    }
    @Bean
    public H10 h10() {
        return new H10();
    }
    @Bean
    public H11 h11() {
        return new H11();
    }
    @Bean
    public H12 h12() {
        return new H12();
    }
    @Bean
    public H13 h13() {
        return new H13();
    }
    @Bean
    public H14 h14() {
        return new H14();
    }
    @Bean
    public H15 h15() {
        return new H15();
    }
    @Bean
    public H16 h16() {
        return new H16();
    }
    @Bean
    public H17 h17() {
        return new H17();
    }
    @Bean
    public H18 h18() {
        return new H18();
    }
    @Bean
    public H19 h19() {
        return new H19();
    }
    @Bean
    public H20 h20() {
        return new H20();
    }

    public static void main(String[] args) {
//        for (int i = 1; i <= 20; i++) {
//            System.out.println("@Bean\n" +
//                    "    public H"+i+" h"+i+"() {\n" +
//                    "        return new H"+i+"();\n" +
//                    "    }");
//        }

        for (int i = 1; i <= 20; i++) {
            System.out.println("handlerManager.putAHandler(1,h"+i+"());");
        }

    }



}
