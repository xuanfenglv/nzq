// 1
class ChatInitHandler extends BaseHandler {
	handleResponse(data) {
		// 渲染好友列表
		let groupList = data.groupList;
		let friendList = data.friendList;
		// 插入分组
		groupList.forEach(group=>{
            var groupHtml = genFriendGroup(group);
			
			$("#fl_group").html($("#fl_group").html() + groupHtml);
		})
		// 插入好友
		friendList.forEach(friend=>{
            let friendHtml = genFriend(friend);
            if (friend.status == 0) {
                $("[groupid="+ friend.groupId+ "] .offline").html(
                    $("[groupid="+ friend.groupId+ "] .offline").html()+ friendHtml);
            } else {
                $("[groupid="+ friend.groupId+ "] .online").html(
                    $("[groupid="+ friend.groupId+ "] .online").html()+ friend);
			}

		})
	}
}
// 2
class ChatHandler extends BaseHandler {
	handleResponse(data) {
		// 停止转动
		let chatInfo = new ChatInfo(data.receiveXf,0,data.serverTime,data.text);
		chatInfoUtil.add(chatInfo);
	}
	handleNotice(data) {
		// 收到一条消息
		let chatInfo = new ChatInfo(data.sendXf,1,data.serverTime,data.text);
		chatInfoUtil.add(chatInfo);
		// 视图发生变化
	}
}