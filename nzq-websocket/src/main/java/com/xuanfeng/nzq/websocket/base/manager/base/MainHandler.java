package com.xuanfeng.nzq.websocket.base.manager.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.commons.utils.CommonUtil;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.session.base.WsServer;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 主处理器
 * @author: lvxianqing
 * @create: 2018/12/21 17:04
 */

public class MainHandler {

    private HandlerManager handlerManager;

    public void process(String message, WsServer wsServer, Session session) throws IOException {
        JSONObject reqMsg = JSON.parseObject(message);
        // gateway处已经校验过了，可以放心使用（不必担心空指针）
        int msgId = CommonUtil.parseInt(reqMsg.getString("msgId"),-1);
        // 没有消息号，不作处理
        if (msgId == -1) {
            return;
        }

        // 加载对应的处理器
        IMsgHandler msgHandler = handlerManager.getHandler(msgId);
        if (msgHandler == null) {
            SendMsgUtil.sendMessage(session,new ResponseMsg(RetEnum.PARAM_ERROR,"不存在的消息号"));
            return;
        }
        if (msgId == 1) {
            long xf = CommonUtil.parseLong(reqMsg.getString("xf"),0);
            wsServer.setXf(xf);
        }
        // 处理
        msgHandler.process(reqMsg,wsServer.getXf(),session,msgId);

    }

    public void setHandlerManager(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }
}
