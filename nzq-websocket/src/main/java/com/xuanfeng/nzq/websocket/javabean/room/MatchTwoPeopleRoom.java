package com.xuanfeng.nzq.websocket.javabean.room;

import com.xuanfeng.nzq.commons.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.javabean.room.base.BaseTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.main.game.component.IdGenerator;
import com.xuanfeng.nzq.websocket.main.game.msg.notice.MatchNotice;
import com.xuanfeng.nzq.websocket.util.GameSessions;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;
import com.xuanfeng.nzq.websocket.util.UserManager;

/**
 * @description: 匹配二人房间
 * @author: lvxianqing
 * @create: 2018/12/21 14:48
 */

public class MatchTwoPeopleRoom extends BaseTwoPeopleRoom {

    private boolean blackConfirm;
    private boolean whiteConfirm;
    // 游戏是否开始
    private boolean begin;
    
    // 房间创建时间
    private long createTime;
    // 房间自动关闭时间
    private long autoCloseTime;
    // 房间销毁时间（房间晚5s销毁，适应网络延迟）
    private long destroyTime;
    public MatchTwoPeopleRoom(Long one, Long another) {
        super.id = IdGenerator.getMatchRommId();
        super.black = one;
        super.white=another;
        super.blackSession = GameSessions.get(one);
        super.whiteSession = GameSessions.get(another);
        UserCache black = UserManager.getUserCache(one);
        black.setRoomId(id);
        UserCache white = UserManager.getUserCache(another);
        white.setRoomId(id);

    }

    /**
     * 房间开始工作
     */
    public void run() {
        // TODO: 2018/12/21 发送确认push
        MatchNotice matchNotice = new MatchNotice();
        matchNotice.setBlack(black);
        matchNotice.setWhite(white);
        SendMsgUtil.sendMessage(blackSession, new NoticeMsg(20, matchNotice));
        SendMsgUtil.sendMessage(whiteSession, new NoticeMsg(20, matchNotice));
    }

    /**
     * 确认游戏
     * @param xf
     */
    public void confirm(Long xf) {
        if (xf.equals(this.black)) {
            blackConfirm = true;
        } else if (xf.equals(this.white)) {
            whiteConfirm = true;
        }

        if (isAllConfirm()) {
            // TODO: 2018/12/21 开始游戏通知
            SendMsgUtil.sendMessage(blackSession, new NoticeMsg(21));
            SendMsgUtil.sendMessage(whiteSession, new NoticeMsg(21));
        }
    }

    private boolean isAllConfirm() {
        return blackConfirm&&whiteConfirm;
    }
}
