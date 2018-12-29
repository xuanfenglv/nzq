package com.xuanfeng.nzq.websocket.main.game.constant;

/**
 * @description: 游戏消息号
 * @author: lvxianqing
 * @create: 2018/12/27 21:10
 */

public enum GameMsgId {
    初始化账号(1),
    创建房间(2),
    发送邀请(3),
    接受邀请(4),
    拒绝邀请(5),
    换到空位置(6),
    请求换位(7),
    接受换位(8),
    拒绝换位(9),
    退出房间(10),
    房主踢人(11),
    开始游戏(12),
    落子(13),
    发送表情(14),
    发送声音(15),
    发送消息(16),
    观战(17),
    发送弹幕(18),
    开始匹配(19),
    匹配成功确认(20),
    请求观战(21),
    下棋实况推送(22),
    观战棋盘校准(23),
    状态变化(24);

    private int msgId;

    GameMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getMsgId() {
        return msgId;
    }

}
