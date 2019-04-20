package com.xuanfeng.nzq.websocket.base.manager.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.commons.utils.CommonUtil;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.session.base.WsServer;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;

/**
 * @description: 主处理器
 * @author: lvxianqing
 * @create: 2018/12/21 17:04
 */

public class MainHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private HandlerManager handlerManager;

    public void process(String message, WsServer wsServer, Session session) {
        JSONObject reqMsg = null;
        try {
            reqMsg = JSON.parseObject(message);
        } catch (Exception e) {
            logger.info("消息格式错误");
            SendMsgUtil.sendMessage(session,new ResponseMsg(RetEnum.PARAM_ERROR,"消息格式错误"));
            return;
        }
        // gateway处已经校验过了，可以放心使用（不必担心空指针）
        int msgId = CommonUtil.parseInt(reqMsg.getString("msgId"),-1);
        logger.info("[chat][协议{}][message:{}]",msgId,message);
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
