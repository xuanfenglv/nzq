// websocket基类
class Msg {
    constructor(msgId) {
        this.msgId = msgId;
    }
}

// websocket 处理器基类
class BaseHandler {
    handle(msg) {
        // 如果是推送，直接处理
        if (PushMsgType.NOTICE == msg.type) {
            this.handleNotice(msg.data);
        } else {
            // 先判断响应是否成功
            let success = CommonUtils.checkRet(msg);
            if (success) {
                this.handleResponse(msg.data)
            }
        }


    }

    handleResponse(msg) {
    }

    handleNotice(msg) {
    }
}