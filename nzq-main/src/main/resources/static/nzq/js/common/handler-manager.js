
class ChatHandlerManager {
	constructor() {
		this.handlerMapping = new Map();
		
		this.handlerMapping.set(1, new ChatInitHandler());
		this.handlerMapping.set(2, new ChatHandler());
	}
	handle(msg) {
		let handler = this.handlerMapping.get(msg.msgId);
		if(!handler) {
			alert("不存在的处理器");
			return false;
		}
		handler.handle(msg);
	}
}