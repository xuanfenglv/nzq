class Msg {
	constructor(msgId) {
		this.msgId = msgId;
	}
}
class InitChatMsg extends Msg {
	constructor(xf,token) {
		super(1);
		this.xf = xf;
		this.token = token;
	}
}
