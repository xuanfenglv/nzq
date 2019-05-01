// 1. 初始化GAME连接
class InitGameMsg extends Msg {
    constructor(xf, token) {
        super(1);
        this.xf = xf;
        this.token = token;
    }
}

// 2. 创建房间
class CreateRoomMsg extends Msg {
    constructor(type) {
        super(2);
        this.type = type;
    }
}

// 3. 发送邀请
class SendInvitationMsg extends Msg {
    constructor(xf) {
        super(3);
        this.xf = xf;
    }
}

// 4. 接受邀请
class AcceptInvitationMsg extends Msg {
    constructor(invitationId) {
        super(4);
        this.invitationId = invitationId;
    }
}

// 5. 拒绝邀请
class RejectInvitationMsg extends Msg {
    constructor(invitationId) {
        super(5);
        this.invitationId = invitationId;
    }
}

// 6. 换到空位
class MoveToVacancyMsg extends Msg {
    constructor(xf) {
        super(6);
        this.position = position;
    }
}

// 7. 请求换位
class RequestExchangePositionMsg extends Msg {
    constructor(position) {
        super(7);
        this.position = position;
    }
}

// 8. 接受换位
class AcceptExchangePositionMsg extends Msg {
    constructor(position) {
        super(8);
        this.position = position;
    }
}

// 9. 拒绝换位
class RejectExchangePositionMsg extends Msg {
    constructor(position) {
        super(9);
        this.position = position;
    }
}

// 10. 退出房间
class QuitRoomMsg extends Msg {
    constructor(type) {
        super(10);
    }
}

// 11. 房主踢人(踢的是位置还是人？)
class TickOutMsg extends Msg {
    constructor(position) {
        super(11);
        this.position = position;
    }
}

// 12. 开始游戏
class BeginMsg extends Msg {
    constructor(xf) {
        super(12);
    }
}

// 13. 落子
class DrowChessMsg extends Msg {
    constructor(row,column) {
        super(13);
        this.row = row;
        this.column = column;
    }
}
// 14. 发送局内表情
class SendInnerEmotionMsg extends Msg {
    constructor(id) {
        super(14);
        this.id = id;
    }
}

// 15. 发送声音
class SendInnerVoiceMsg extends Msg {
    constructor(id) {
        super(15);
        this.id = id;
    }
}

// 16. 发送内部消息
class SendInnerTextMsg extends Msg {
    constructor(text) {
        super(16);
        this.text = text;
    }
}

// 17. 观战
class WatchGameMsg extends Msg {
    constructor(xf) {
        super(17);
        this.xf = xf;
    }
}
// 暂时不做------------------------------------------------------------------------------------------------------------------
// 18. 发送弹幕
class SendDanmuMsg extends Msg {
    constructor(xf) {
        super(18);
        this.xf = xf;
    }
}

// 19. 开始匹配
class BeginMatchMsg extends Msg {
    constructor(xf) {
        super(19);
        this.xf = xf;
    }
}

// 20. 匹配成功确认
class ConfirmMatchMsg extends Msg {
    constructor(xf) {
        super(20);
        this.xf = xf;
    }
}

// 21. 请求观战
class WatchGameMsg extends Msg {
    constructor(xf) {
        super(21);
        this.xf = xf;
    }
}



