package com.xuanfeng.nzq.websocket.base.process.base;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.websocket.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;

public abstract class IMsgHandler {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public void process(JSONObject parms,long xf, Session session,int msgId) throws IOException {

		RequestMsg message = null;
		ResponseMsg result = null;
		// RequestMsg 为空时表示只有消息号的消息，不需要反序列化和校验参数
		if (getRequestMessageType() != null) {
			boolean hasException = false;
			try {
				message = parms.toJavaObject(getRequestMessageType());
			} catch (JSONException e) {
//				e.printStackTrace();
				result = new ResponseMsg(RetEnum.PARAM_ERROR,"请求消息不是正确的json");
				hasException = true;
			}
			if (!hasException) {
				// 先校验参数
				CheckParamResult checkParmResult = message.checkParams();
				if (!checkParmResult.isValid()) {

					result = new ResponseMsg(RetEnum.PARAM_ERROR,checkParmResult.getErrmsg());
				}
			}

		}
		// 发生故障了就直接返回，不执行handle方法
		if (result == null) {
			logger.info("[chat][协议{}][message:{}]",message.getMsgId(),message);
			result = handle(message,xf,session);
		}

		if (result != null) {
			result.setMsgId(msgId);
			// 响应消息
			SendMsgUtil.sendMessage(session,result);
		}
	}

	// 需要具体业务实现的
	protected abstract ResponseMsg handle(RequestMsg message,long xf,Session session) throws IOException;

	protected abstract Class<? extends RequestMsg> getRequestMessageType();
}
