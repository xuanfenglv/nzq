package com.xuanfeng.nzq.websocket.main.game.constant;

public enum ImMsgId {
    初始化账号(1),
    发送文字消息(2),
    发送大表情(3),
    发送图片(4),
    发送语音(5),
    语音通话(6),
    视频聊天(7),
    发送好友申请(8),
    同意好友申请(9),
    拒绝好友申请(10),
    删除好友(11),
    状态变化(12)
    ;

    private int msgId;

    ImMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getMsgId() {
        return msgId;
    }
}
