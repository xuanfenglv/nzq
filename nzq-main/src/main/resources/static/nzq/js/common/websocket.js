function chatInitMethod() {

	// 初始化连接
	let msg = new InitChatMsg(imParam.myXf,"87DF6AS98DS9F98DF7")
    this.sendMsg(msg);
};

function gameInitMethod() {
    // 初始化连接
    let msg = new InitGameMsg(imParam.myXf,"87DF6AS98DS9F98DF7")
    this.sendMsg(msg);
};

class WebSocketUtil {
	constructor(name,address,handlerManager,initMethod) {
        this.logger = new Logger(name);
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
        this.logger.info("连接关闭")
	}
	onError() {
        this.logger.info("发生错误")
	}
	onMessage(event) {
		var msg = JSON.parse(event.data);
        this.logger.info('[接收:'+msg.msgId+']  '+event.data)
		this.handlerManager.handle(msg);
	}

	sendMsg(msg) {
		let msgStr = JSON.stringify(msg);
        this.logger.info('[发送:'+msg.msgId+'] '+msgStr)
		this.websocket.send(msgStr);
	}
}
