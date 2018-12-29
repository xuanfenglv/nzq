package com.xuanfeng.nzq.websocket.main.im.msg.request;

import com.xuanfeng.nzq.websocket.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.msg.request.RequestMsg;
import org.springframework.util.StringUtils;

/**
 * @description: 发送文字消息
 * @author: lvxianqing
 * @create: 2018/10/03 10:04
 */

public class SendTextMsgReq extends RequestMsg {
    private long xf;
    private long clientTime;
    private String text;
    @Override
    protected void doCheck(CheckParamResult result) {
        if (xf < 0) {
            result.setErrMsg("无效的账号");
        } else if(xf ==0) {
            result.setErrMsg("无接收方账号");
        } else if (StringUtils.isEmpty(text)) {
            result.setErrMsg("不可发送空消息");
        }
    }

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

    public long getClientTime() {
        return clientTime;
    }

    public void setClientTime(long clientTime) {
        this.clientTime = clientTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
