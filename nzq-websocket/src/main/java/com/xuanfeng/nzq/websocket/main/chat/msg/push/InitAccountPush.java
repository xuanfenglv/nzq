package com.xuanfeng.nzq.websocket.main.chat.msg.push;

import com.xuanfeng.nzq.websocket.base.msg.push.PushMsg;

/**
 * @description: 初始化账号被顶掉线
 * @author: lvxianqing
 * @create: 2018/09/30 16:54
 */

public class InitAccountPush extends PushMsg {
    @Override
    protected int initMsgId() {
        return 1;
    }
}
