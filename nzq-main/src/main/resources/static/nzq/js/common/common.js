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
    constructor(props) {
        // 聊天list
        this.chatList = [];
        // 未读消息数
        this.unreadMsgNo = 0;
    }

}

// 用一个map存放所有人的聊天信息
let friendInfoUtil = {
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
        let friendInfo = this.getFriendInfo(xf);

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
    }
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