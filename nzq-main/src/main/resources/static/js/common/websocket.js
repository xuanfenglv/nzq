chatInitMethod(webSocketUtil) {
	var xf = $("#id_container").html();
	// 初始化连接
	InitChatMsg msg = new InitChatMsg(xf,"87DF6AS98DS9F98DF7")
	webSocketUtil.sendMsg(msg);
};

class WebSocketUtil {
	constructor(name,address,handlerManager,initMethod) {
		this.name = name;
		this.websocket = new WebSocket(address);
		this.initMethod = initMethod;
		websocket.onclose=onClose;
		websocket.onerror=onError;
		websocket.onopen=onOpen;
		websocket.onmessage=onMessage;
		
	}
	onOpen() {
		this.initMethod(this);
	}
	
	onClose() {
		console.log(this.name + "连接关闭")
	}
	onError() {
		console.log(this.name + "发生错误")
	}
	onMessage(event) {
		var msg = event.data;
		handlerManager.handle(msg);
	}
	
	sendMsg(msg) {
		let msgStr = msg.stringify();
		this.websocket.send(msgStr);
	}
}
