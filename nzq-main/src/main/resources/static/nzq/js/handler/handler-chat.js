// 1
class ChatInitHandler extends BaseHandler {
	handleResponse(data) {
		// 渲染好友列表
		let groupList = data.groupList;
		let friendList = data.friendList;
		// 插入分组
		groupList.forEach(group=>{
            let groupHtml = genFriendGroup(group);
			
			// $("#fl_group").html($("#fl_group").html() + groupHtml);
			$("#fl_group").append(groupHtml)
		})
		// 插入好友
		friendList.forEach(friend=>{
            let friendHtml = genFriend(friend);
            if (friend.status == 0) {
                $("[groupid="+ friend.groupId+ "] .offline").append(friendHtml);
            } else {
                $("[groupid=" + friend.groupId + "] .online").append(friendHtml);
                groupNoInfo.addOnline(friend.groupId);
			}
            groupNoInfo.addTotal(friend.groupId);
		})
	}
}
// 2
class ChatHandler extends BaseHandler {
	handleResponse(data) {
		// 停止转动
		let chatInfo = new ChatInfo(data.receiveXf,ChatInfoType.SEND,data.serverTime,data.text);
        friendInfoUtil.addChatInfo(chatInfo);
	}
	handleNotice(data) {
		// 收到一条消息
		let chatInfo = new ChatInfo(data.sendXf,ChatInfoType.RECEIVE,data.serverTime,data.text);
        friendInfoUtil.addChatInfo(chatInfo);
		// 如果收到消息的好友不是正在聊天的好友则增加未读消息数
        if (chatInfo.xf != onChatXf) {
        	// 更新未读消息数
            friendInfoUtil.addUnreadMsgNo(chatInfo.xf);
        }
	}
}