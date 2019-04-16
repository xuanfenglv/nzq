// websocket基类
class Msg {
	constructor(msgId) {
		this.msgId = msgId;
	}
}
// websocket 处理器基类
class BaseHandler {
	handle(msg) {
		let success = CommonUtils.checkRet(msg);
		if(success) {
			if(msg.type == 0) {
				this.handleResponse(msg.data)
			} else {
				this.handleNotice(msg.data)
			}
		}

	}
	handleResponse(msg) {}
	handleNotice(msg) {}
}