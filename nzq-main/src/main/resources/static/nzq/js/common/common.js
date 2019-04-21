// 校验ret
let CommonUtils = {
    checkRet: function (msg) {
        let ret = msg.ret;
        if (ret == 1) {
            return true;
        } else {
            // todo 做一个通用的
            showError(msg.errorMsg);
            return false;
        }
    }
}

// 聊天信息类
class ChatInfo {
    constructor(xf, type, timestamp, text) {
        this.xf = xf;
        this.type = type;
        this.timestamp = timestamp;
        this.text = text;
    }
}

class FriendInfo {
    constructor() {
        // 聊天list
        this.chatList = [];
        // 未读消息数
        this.unreadMsgNo = 0;
        // 简要信息
        this.info = null;
    }

    getName() {
        return this.info.remark?this.info.remark:this.info.nickname;
    }

    getStatusDesc() {
        return this.info.status==UserStatus.OFFLINE?'[离线]':'[在线]';
    }

}

// 存放好友信息、聊天信息、未读消息数
let friendInfoUtil = {
    logger: new Logger("friendInfoUtil"),
    map: new Map(),
    totalUnreadMsgNo:0,
    getFriendInfo: function (xf) {
        let friendInfo = this.map.get(xf);
        if (!friendInfo) {
            friendInfo = new FriendInfo();
            this.map.set(xf, friendInfo);
        }
        return friendInfo;
    },
    addInfo: function (friend) {
        let friendInfo = this.getFriendInfo(friend.xf);
        friendInfo.info = friend;
    },
    getInfo(xf) {
        let friendInfo = this.getFriendInfo(xf);
        return friendInfo.info;
    },
    getName(xf) {
        return this.getFriendInfo(xf).getName();
    },
    getStatusDesc(xf) {
        return this.getFriendInfo(xf).getStatusDesc();
    },
    addChatInfo: function (chatInfo) {
        let friendInfo = this.getFriendInfo(chatInfo.xf);
        // 向数组结尾放入一条新的聊天信息
        friendInfo.chatList.push(chatInfo);
        // 如果正在聊天中，加一个气泡
        if (imParam.onChatXf == chatInfo.xf) {
            genTextByChatInfo(chatInfo);
        }
        // 更新（创建）会话
        createOrUpdateSession(chatInfo);
    },
    getChatInfos: function (xf) {
        let friendInfo = this.getFriendInfo(xf);
        // 向数组开头放入一条新的聊天信息
        return friendInfo.chatList;
    },
    clearUnreadMsgNo: function (xf) {
        this.logger.debug(`开始清空未读消息数,xf:${xf}`);
        let friendInfo = this.getFriendInfo(xf);
        this.logger.debug(`未读消息数:${friendInfo.unreadMsgNo}`);
        if (friendInfo.unreadMsgNo > 0) {
            // 更新总未读
            this.totalUnreadMsgNo -= friendInfo.unreadMsgNo;
            // 更新dom
            imDomObj.totalUnreadMsgNo.html(this.totalUnreadMsgNo);
            if (this.totalUnreadMsgNo <= 0) {
                imDomObj.totalUnreadMsgNo.css("display", "none");
            }
            // 清空当前好友未读
            friendInfo.unreadMsgNo = 0;
            // 更新dom
            let dom = $("div[friendid=" + xf + "] .number")
            dom.html("0");
            dom.css("display", "none");
        }


    },
    addUnreadMsgNo: function (xf) {
        let friendInfo = this.getFriendInfo(xf);

        // 更新总未读
        this.totalUnreadMsgNo++;
        // 更新dom
        imDomObj.totalUnreadMsgNo.html(this.totalUnreadMsgNo);
        if (this.totalUnreadMsgNo > 0) {
            imDomObj.totalUnreadMsgNo.css("display", "inline");
        }

        // 更新此好友未读
        friendInfo.unreadMsgNo++;
        // 更新dom
        let dom = $("div[friendid=" + xf + "] .number")
        dom.html(friendInfo.unreadMsgNo);
        // show 一下
        if (friendInfo.unreadMsgNo >0) {
            dom.css("display", "inline");
        }
    },
    getUnreadMsgNo: function (xf) {
        let friendInfo = this.getFriendInfo(xf);
        return friendInfo.unreadMsgNo;
    },
    remove: function (xf) {
        // 减少分组人数
        let friendInfo = friendInfoUtil.getFriendInfo(xf).info;
        let groupId = friendInfo.groupId;
        if (friendInfo.status == UserStatus.ONLINE) {
            groupNoInfo.subOnline(groupId);
        }
        groupNoInfo.subTotal(groupId);
        // 移除数据
        this.map.delete(xf);
        // 从dom中移除好友
        $("[xf=" + xf + "]").remove();

    }
}

let groupInfo={

}

class GroupNo {
    constructor() {
        this.total = 0;
        this.online = 0;
    }

}
// 分组在线离线数量
let groupNoInfo = {
    map: new Map(),
    getGroupNo: function (groupId) {
        let groupNo = this.map.get(groupId);
        if (!groupNo) {
            groupNo = new GroupNo();
            this.map.set(groupId, groupNo);
        }
        return groupNo;
    },
    addOnline: function (groupId) {
        let groupNo = this.getGroupNo(groupId);
        groupNo.online++;
        $("#" + groupId + " .f_online").html(groupNo.online);
    },
    subOnline: function (groupId) {
        let groupNo = this.getGroupNo(groupId);
        groupNo.online--;
        $("#" + groupId + " .f_online").html(groupNo.online);
    },
    addTotal: function (groupId) {
        let groupNo = this.getGroupNo(groupId);
        groupNo.total++;
        $("#" + groupId + " .f_total").html(groupNo.total);
    },
    subTotal: function (groupId) {
        let groupNo = this.getGroupNo(groupId);
        groupNo.total--;
        $("#" + groupId + " .f_total").html(groupNo.total);
    },
    move(deleteGroupId, defaultGroupId) { // 移动分组
        let deleteGroupNo = this.getGroupNo(deleteGroupId);
        let defaultGroupNo = this.getGroupNo(defaultGroupId);
        defaultGroupNo.total += deleteGroupNo.total;
        defaultGroupNo.online += deleteGroupNo.online;

        $("#" + defaultGroupId + " .f_online").html(defaultGroupNo.online);
        $("#" + defaultGroupId + " .f_total").html(defaultGroupNo.total);
    }
}

function getMyXf() {
    let thisURL = document.URL;
    let  key_val =thisURL.split('?')[1];
    let val= key_val.split("=")[1];
    imParam.myXf = val;
    imDomObj.idContainer.html(imParam.myXf);
    imDomObj.photo.attr("src","/nzq/photo/"+imParam.myXf+".jpg");
}

/**
 * 关闭蒙层
 */
function endRunning() {
    $("#running").css("display","none");
}
// 打开蒙层
function showRunning(title) {
//	console.log("showtime");
    $("#running_title").html(title+"...");
    $("#running").css("display","inline");
}
// 显示错误
function showError(msg) {
    $("#error").slideDown(600).delay(1500).slideUp(600);
    $("#error .error_title").html(msg);
}
