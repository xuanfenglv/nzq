package com.xuanfeng.nzq.websocket.main.game.process;

import com.xuanfeng.nzq.commons.msg.request.RequestMsg;
import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;
import com.xuanfeng.nzq.domain.constant.UserStatusEnum;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.game.constant.MatchGameQueue;
import com.xuanfeng.nzq.websocket.main.im.component.StatusHandler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 开始匹配处理器
 * @author: lvxianqing
 * @create: 2018/12/21 17:34
 */

public class BeginMatchHandler extends IMsgHandler {
    @Autowired
    private StatusHandler statusHandler;

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        // TODO: 2018/12/21 判断是否有早退惩罚 

        // 放进匹配队列
        MatchGameQueue.push(xf);
        // TODO: 2018/12/21 修改状态为匹配中
        statusHandler.changeStatus(xf, UserStatusEnum.匹配中);

        return null;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
