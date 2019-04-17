// 显示某个分组的好友列表
function showFriends(id) {
    $("[groupid=" + id + "]").toggle();

    if ($("[groupid=" + id + "]").is(":visible")) {
        $("[imgid=" + id + "]").attr("src", "/nzq/img/down.png");
    } else {
        $("[imgid=" + id + "]").attr("src", "/nzq/img/right.png");
    }
}

// 显示好友聊天框
/**
 * 1.如果此好友有未读消息，更新此好友未读消息数和总未读消息数，如果未读消息数==0则隐藏
 * 2.设置当前聊天xf
 * 3.加载历史聊天信息
 * @param xf
 */
function showChatDetail(xf) {
    // 打开
    imDomObj.chatPage.css("display", "inline");
    // 此好友的未读消息数
    let thisFriendUnreadMsgNo = friendInfoUtil.getUnreadMsgNo(xf);

    if (thisFriendUnreadMsgNo > 0) {
        // 清空此好友的未读消息
        friendInfoUtil.clearUnreadMsgNo(xf);
    }

    // 设置当前聊天xf
    imParam.onChatXf = xf;

    // 聊天消息list
    let chatList = friendInfoUtil.getChatInfos(xf);
    chatList.forEach(chatInfo => {
        genTextByChatInfo(chatInfo);
    });
    // 也不知道管不管用

    $("[xf_no=" + xf + "]").scrollTop = $("[xf_no=" + xf + "]").scrollHeight;
}

// 发送消息
function sendMessage() {
    var text = $("#msg_input").val();

    if (text) {
        // 生成html
        genMyText(text);

        // 滚动到底部
        $("[xf_no=" + imParam.onChatXf + "]").scrollTop($("[xf_no=" + imParam.onChatXf + "]").height());

        // 发送消息
        let msg = new SendTextMsg(imParam.onChatXf, text);
        imWs.sendMsg(msg);

        // 清空输入框
        $("#msg_input").val(emptyStr);
    } else {
        showTip("不可发送空消息");
    }
}

/**
 * 创建或更新会话
 * @param chatInfo
 */
function createOrUpdateSession(chatInfo) {
    let session = $("[friendid=" + chatInfo.xf + "]");
    if (session.length == 0) {
        createSession(chatInfo);
    } else {
        $("div[friendid=" + chatInfo.xf + "] .content").html(chatInfo.text);
    }
}

/**
 * 显示添加好友页面
 * @param xf
 */
function showAddFriend(xf) {
    // 清空分组下拉列表
    $("#group").html("");
    // 给下拉列表赋值 todo 优化
    var groups = document.getElementsByClassName("group_detail");
    for(var i = 0; i < groups.length; i++) {
        let group = groups[i];
        let id = group.id;
        let name = group.getElementsByClassName("group_name")[0].innerHTML;
        $("#group").html($("#group").html()+'<option value="'+id+'">'+name+'</option>');
    }
    // 把一片dom copy过来
    $("#add_friend_data").html($("[search_friend_id = "+xf+"]").html());
    // 显示添加好友页面
    $("#add_friend").css("display", "inline");
    // 清空备注
    $("#remark").val("");
    // 设置全局变量
    imParam.receiveApplicationXf = xf;

}

/**
 * 初始化申请
 */
function initApplication() {
    getApp();
}