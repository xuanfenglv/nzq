// 校验ret
let CommonUtils = {
    checkRet: function (msg) {
        let ret = msg.ret;
        switch (ret) {
            case 1:
                return true;
            case 2:
                //未登录
                layer.msg(tip, {
                    icon: 0,
                    time: 1000
                });
                break;
            case 3:
                // 参数不正确
                layer.msg(tip, {
                    icon: 2,
                    time: 1000
                });
                break;
            case 4:
                // 权限不足
                layer.msg(tip, {
                    icon: 5,
                    time: 1000
                });
                break;
            case 5:
                // 需要备注
                layer.msg(tip, {
                    icon: 3,
                    time: 1000
                });
                break;
            case 6:
                // 已被封号
                layer.msg('已被封号', {
                    icon: 4,
                    time: 1000
                });
                break;
            case 7:
                // 登录失败
                layer.msg(tip, {
                    icon: 2,
                    time: 1000
                });
                break;
            case 8:
                // 服务器故障
                layer.msg(tip, {
                    icon: 2,
                    time: 1000
                });
                break;
            case 9:
                // 服务器故障
                layer.msg(tip, {
                    icon: 2,
                    time: 1000
                });
                break;
            default:
                break;
        }
        return false;
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
        let friendInfo = this.map.get(chatInfo.xf);
        if (!friendInfo) {
            friendInfo = new FriendInfo();
            this.map.set(chatInfo.xf, friendInfo);
        }
        return friendInfo;
    },
    addChatInfo: function (chatInfo) {
        let friendInfo = this.getFriendInfo(chatInfo.xf);
        // 向数组结尾放入一条新的聊天信息
        friendInfo.chatList.push(chatInfo);

        // 更新（创建）会话
        createOrUpdateSession(chatInfo);
    },
    getChatInfos: function (xf) {
        let friendInfo = this.getFriendInfo(chatInfo.xf);
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
        let friendInfo = this.getFriendInfo(chatInfo.xf);
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