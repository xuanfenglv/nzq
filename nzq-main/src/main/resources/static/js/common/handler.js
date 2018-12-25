class BaseHandler {
	handle(msg) {
		let success = CommonUtils.checkRet(msg);
		if(success) {
			if(msg.type == 1) {
				this.handleResponse(msg)
			} else {
				this.handleNotice(msg)
			}
		}

	}
	handleResponse(msg) {}
	handleNotice(msg) {}
}
class ChatHandlerManager {
	GameHandlerManager
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
class ChatInitHandler extends BaseHandler {
	handleResponse(msg) {
		// 渲染好友列表
		let groupList = msg.groupList;
		let friendList = msg.friendList;
		groupList.forEach(group=>{
			var groupHtml = '<div class="group"><div class="group_detail" id="' +
				group.id +
				'" onclick="showFriends(this.id)"><div class="group_arrow"><img src="/nzq/img/right.png" style="width: 100%;" imgid="' +
				group.id +
				'" /></div><div class="group_name">' +
				group.name +
				'</div><div class="online_number"><span class="f_online" >' +
				0 +
				'</span>/<span class="f_total" >' +
				0 +
				'</span></div></div><div class="panel" style="display: none;" groupid="' +
				group.id +
				'"><div class="online"></div><div class="offline"></div></div></div>';
			
			$("#fl_group").html($("#fl_group").html() + groupHtml);
		})
	}
}
class ChatHandler extends BaseHandler {
	handleResponse(msg) {
		// 停止转动
		ChatInfo chatInfo = new ChatInfo(msg.receiveXf,0,msg.serverTime,msg.text);
		chatInfoUtil.add(chatInfo);
	}
	handleNotice(msg) {
		// 收到一条消息
		ChatInfo chatInfo = new ChatInfo(msg.sendXf,1,msg.serverTime,msg.text);
		chatInfoUtil.add(chatInfo);
		// 视图发生变化
	}
}