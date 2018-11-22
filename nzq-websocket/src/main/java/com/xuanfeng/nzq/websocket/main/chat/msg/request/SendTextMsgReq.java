package com.xuanfeng.nzq.websocket.main.chat.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.result.CheckParamResult;
import org.springframework.util.StringUtils;

/**
 * @description: 发送文字消息
 * @author: lvxianqing
 * @create: 2018/10/03 10:04
 */

public class SendTextMsgReq extends RequestMsg {
    private long receiveXf;
    private long timestamp;
    private String text;
    @Override
    protected void doCheck(CheckParamResult result) {
        if (receiveXf < 0) {
            result.setErrMsg("无效的账号");
        } else if(receiveXf==0) {
            result.setErrMsg("无接收方账号");
        } else if (StringUtils.isEmpty(text)) {
            result.setErrMsg("不可发送空消息");
        }
    }

    public long getReceiveXf() {
        return receiveXf;
    }

    public void setReceiveXf(long receiveXf) {
        this.receiveXf = receiveXf;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
