// 初始化IM连接
class InitChatMsg extends Msg {
	constructor(xf,token) {
		super(1);
		this.xf = xf;
		this.token = token;
	}
}
