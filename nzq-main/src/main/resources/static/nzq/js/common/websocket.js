function chatInitMethod() {
	var xf = $("#id_container").val();
	// 初始化连接
	let msg = new InitChatMsg(xf,"87DF6AS98DS9F98DF7")
    this.sendMsg(msg);
};

class WebSocketUtil {
	constructor(name,address,handlerManager,initMethod) {
		this.name = name;
		this.websocket = new WebSocket(address);
		this.initMethod = initMethod;
		this.handlerManager = handlerManager;

		this.websocket.onclose=this.onClose.bind(this);
        this.websocket.onerror=this.onError.bind(this);
        this.websocket.onmessage=this.onMessage.bind(this);
        this.websocket.onopen=this.onOpen.bind(this);
	}
	onOpen() {
		this.initMethod();
	}
	
	onClose() {
		console.log(this.name + "连接关闭")
	}
	onError() {
		console.log(this.name + "发生错误")
	}
	onMessage(event) {
		var msg = JSON.parse(event.data);
		console.log('[收到消息:'+msg.msgId+']	    '+event.data)
		this.handlerManager.handle(msg);
	}
	
	sendMsg(msg) {
		let msgStr = JSON.stringify(msg);
		this.websocket.send(msgStr);
	}
}
