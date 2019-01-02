package com.xuanfeng.nzq.websocket.main.game.task;

import com.alibaba.fastjson.JSON;
import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.msg.common.DropChessResult;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;

import javax.websocket.Session;
import java.util.List;

/**
 * @description: 观战信息推送任务
 * @author: lvxianqing
 * @create: 2018/12/28 15:54
 */

public class WatchNoticeTask extends Thread {

    // 观众连接
    private List<Session> audience;
    // 下棋结果
    private DropChessResult dropChessResult;

    public WatchNoticeTask(List<Session> audience, DropChessResult dropChessResult) {
        this.audience = audience;
        this.dropChessResult = dropChessResult;
    }

    @Override
    public void run() {
        NoticeMsg noticeMsg = new NoticeMsg(GameMsgId.下棋实况推送.getMsgId(),dropChessResult);
        String msg = JSON.toJSONString(noticeMsg);
        audience.forEach(session -> {
            SendMsgUtil.sendMessage(session,msg);
        });
    }
}
