// 1
class GameInitHandler extends BaseHandler {
    handleResponse(data) {
        let online=[],offline=[];
        // 渲染好友列表
        data.forEach(friend=>{
            if (NzqStatus.离线 == friend.status) {
                offline.push(friend);
            } else {
                online.push(friend);
            }
            gameFriendMap.set(friend.xf, friend);
        })
        gameVue.onlineFriendList=online;
        gameVue.offlineFriendList=offline;
    }
}

// 2
class CreateRoomHandler extends BaseHandler {
    handleResponse(data) {
        // 创建2人自定义房间
        if (RoomSize.TWO == data) {
            create2Room();
        } else if (RoomSize.THREE == data) { // 创建3人自定义房间
            create3Room();
        }
    }
}

// 3. 发送邀请
class SendInvitationHandler extends BaseHandler {
    handleResponse(data) {
        // 按钮改为已发送（不可点击）

    }
    handleNotice(data) {
        // 打开邀请面板
    }
}

class AcceptInvitationHandler extends BaseHandler {
    handleResponse(data) {
        // 按钮改为已发送（不可点击）
        // todo
    }
    handleNotice(data) {
        // 打开邀请面板
    }
}

// 5. 拒绝邀请
class RejectInvitationHandler extends BaseHandler {
    handleResponse(data) {
        // 按钮改为已发送（不可点击）

    }
    handleNotice(data) {
        // 打开邀请面板
    }
}
// 10. 退出房间
class QuitRoomHandler extends BaseHandler {
    handleResponse(data) {
        gameVue.showCustomRoom=RoomSize.NOTHING;
    }
}

// 24. nzq 状态变化
class NzqGameFriendStatusChangeHandler extends BaseHandler {
    handleNotice(data) {
        let friend = gameFriendMap.get(data.xf);
        // 状态没变不处理
        if (friend.status == data.status) {
            return;
        } else {
            if (friend.status == NzqStatus.离线) {
                // 移出离线数组
                let reomveOne = gameVue.offlineFriendList.remove(friend);
                // 移入在线数组
                gameVue.onlineFriendList.push(reomveOne);
            } else {
                if (data.status == NzqStatus.离线) {
                    // 移出在线数组
                    let reomveOne = gameVue.onlineFriendList.remove(friend);
                    // 移入离线数组
                    gameVue.offlineFriendList.push(reomveOne);
                }
            }
        }
        // 状态变化
        friend.status=data.status;
    }
}

Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};

Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        let removeOne = this[index];
        this.splice(index, 1);
        return removeOne;
    }
};
