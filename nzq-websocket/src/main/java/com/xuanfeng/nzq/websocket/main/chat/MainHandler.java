package com.xuanfeng.nzq.websocket.main.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuanfeng.nzq.commons.utils.CommonUtil;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsgImpl;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.constant.ResponseEnum;
import com.xuanfeng.nzq.websocket.session.ChatSession;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:36 2018/4/19
 * @Modified By:
 */
@Component
public class MainHandler {
	@Autowired
	private ChatHandlerManager handlerManager;

	public void process(String message, ChatSession chatSession, Session session) throws IOException {
		JSONObject reqMsg = JSON.parseObject(message);
		// gateway处已经校验过了，可以放心使用（不必担心空指针）
		int msgId = CommonUtil.parseInt(reqMsg.getString("msgId"),-1);

		// 加载对应的处理器
		IMsgHandler msgHandler = handlerManager.getHandler(msgId);
		if (msgHandler == null) {
			SendMsgUtil.sendMessage(session,new ResponseMsgImpl(ResponseEnum.参数错误,"不存在的消息号"));
			return;
		}
		if (msgId == 1) {
			long xf = CommonUtil.parseLong(reqMsg.getString("msgId"),0);
			chatSession.setXf(xf);
		}
		// 处理
		msgHandler.process(reqMsg,chatSession.getXf(),session);

	}

}
