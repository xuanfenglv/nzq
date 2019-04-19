// 1
class ChatInitHandler extends BaseHandler {
    handleResponse(data) {
        // 渲染好友列表
        groupList = data.groupList;
        let friendList = data.friendList;
        // 插入分组
        groupList.forEach(group => {
            genFriendGroup(group);
        })
        // 插入好友
        friendList.forEach(friend => {
            genFriend(friend);
            friendInfoUtil.addInfo(friend);
        })
    }
}

// 2
class ChatHandler extends BaseHandler {
    handleResponse(data) {
        // 停止转动
        let chatInfo = new ChatInfo(data.xf, ChatInfoType.SEND, data.serverTime, data.text);
        friendInfoUtil.addChatInfo(chatInfo);
    }

    handleNotice(data) {
        // 收到一条消息
        let chatInfo = new ChatInfo(data.xf, ChatInfoType.RECEIVE, data.serverTime, data.text);
        friendInfoUtil.addChatInfo(chatInfo);
        // 如果收到消息的好友不是正在聊天的好友则增加未读消息数
        if (chatInfo.xf != imParam.onChatXf) {
            // 更新未读消息数
            friendInfoUtil.addUnreadMsgNo(chatInfo.xf);
        }
    }
}

// 8. 发送好友申请
class AppHandler extends BaseHandler {
    handleResponse(data) {
        // 发送好友申请成功
        genMyApp(data);
        // 关一下发送申请页面
        $("#add_friend").css("display", "none");
    }

    handleNotice(data) {
        // 收到一条好友申请消息
        genReceiveApp(data);
    }
}

// 9. 同意好友申请
class Im_H9 extends BaseHandler {
    handleResponse(data) {
        // 插入新的好友
        genFriend(data);
        // 修改申请状态为已添加
        $("div[application_receive_id = " + data.applicationId + "] .a_r").html("已添加");
        // 关闭接受申请页面
        imDomObj.acceptApplicationPage.css("display", "none");

    }

    handleNotice(data) {
        // 插入新的好友
        genFriend(data);
        // 修改申请状态为已添加
        $("[application_send_id = " + data.applicationId + "] .app_condition").html("对方已添加你为好友");
    }
}

// 10. 拒绝申请
class Im_H10 extends BaseHandler {
    handleResponse(data) {
        // 修改申请状态
        $("[application_receive_id = "+data.applicationId+"] .a_r").html("已拒绝");
    }

    handleNotice(data) {
        // 修改申请状态
        $("[application_send_id = " +data.applicationId+ "] .app_condition").html("拒绝了你的好友申请");
    }
}

// 11. 删除好友
class Im_H11 extends BaseHandler{
    handleResponse(data) {

    }

    handleNotice(data) {

    }
}
