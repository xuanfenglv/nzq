// 初始化IM连接
class InitChatMsg extends Msg {
	constructor(xf,token) {
		super(1);
		this.xf = xf;
		this.token = token;
	}
}

// 2.发送文字消息
class SendTextMsg extends Msg {
    constructor(receiveXf,text) {
        super(2);
        this.xf = receiveXf;
        this.text=text;
        this.clientTime = new Date().getTime();
    }

}

// 8. 发送好友申请
class SendAppMsg extends Msg{
    constructor(receiveXf, text, groupId, remark) {
        super(8);
        this.receiveXf = receiveXf;
        this.text = text;
        this.groupId = groupId;
        this.remark = remark;
    }
}