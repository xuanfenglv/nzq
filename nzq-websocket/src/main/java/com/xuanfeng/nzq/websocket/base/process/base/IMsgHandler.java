package com.xuanfeng.nzq.websocket.base.process.base;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsgImpl;
import com.xuanfeng.nzq.websocket.constant.ResponseEnum;
import com.xuanfeng.nzq.websocket.result.CheckParamResult;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;

import javax.websocket.Session;
import java.io.IOException;

public abstract class IMsgHandler {
	public ResponseMsg process(JSONObject parms,long xf, Session session) throws IOException {
		RequestMsg message = null;
		ResponseMsg result = null;
		// B2SMessage 为空时表示只有消息号的消息，不需要反序列化和校验参数
		if (getRequestMessageType() != null) {
			boolean hasException = false;
			try {
				message = parms.toJavaObject(getRequestMessageType());
			} catch (JSONException e) {
//				e.printStackTrace();
				result = new ResponseMsgImpl(ResponseEnum.参数错误,"请求消息不是正确的json");
				hasException = true;
			}
			if (!hasException) {
				// 先校验参数
				CheckParamResult checkParmResult = message.checkParams();
				if (!checkParmResult.isValid()) {

					result = new ResponseMsgImpl(ResponseEnum.参数错误,checkParmResult.getErrmsg());
				}
			}

		} else {
			result = new ResponseMsgImpl(ResponseEnum.服务器故障,"服务器未声明请求消息类型");
		}

		result = result==null?handle(message,xf,session):result;
		result.setMsgId(message.getMsgId());
		SendMsgUtil.sendMessage(session,result);
		return result;
	}

	// 需要具体业务实现的
	protected abstract ResponseMsg handle(RequestMsg message,long xf,Session session) throws IOException;

	protected abstract Class<? extends RequestMsg> getRequestMessageType();
}
