
class ChatHandlerManager {
	constructor() {
		this.handlerMapping = new Map();

		this.handlerMapping.set(1, new ChatInitHandler());
		this.handlerMapping.set(2, new ChatHandler());
		this.handlerMapping.set(8, new AppHandler());
		this.handlerMapping.set(9, new Im_H9());
		this.handlerMapping.set(10, new Im_H10());
		this.handlerMapping.set(11, new Im_H11());
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
