package com.xuanfeng.nzq.websocket.main.game.javabean.room;

import com.xuanfeng.nzq.domain.constant.NzqStatusEnum;
import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.component.CustomRooms;
import com.xuanfeng.nzq.websocket.component.IdGenerator;
import com.xuanfeng.nzq.websocket.component.NzqGameStatusHandler;
import com.xuanfeng.nzq.websocket.constant.RoomType;
import com.xuanfeng.nzq.websocket.javabean.NzqGameCache;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.constant.Position;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.base.BaseTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.base.CustomRoom;
import com.xuanfeng.nzq.websocket.main.game.msg.notice.ComeInRoomNotice;
import com.xuanfeng.nzq.websocket.util.NzqGameCacheManager;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 自定义二人房间
 * @author: lvxianqing
 * @create: 2018/12/21 14:57
 */

public class CustomTwoPeopleRoom extends BaseTwoPeopleRoom implements CustomRoom {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // 黑色方请求交换截止时间
    private long blackExchange;
    // 白色方请求交换截止时间
    private long whiteExchange;

    // 最后一次修改时间（用来回收长时间不用的房间）
    private long lastModifyTime;
    // 房主
    private long owner;

    private ReentrantLock enterRoomLock = new ReentrantLock();

    // 传入房主
    public CustomTwoPeopleRoom(Long xf, Session session) {
        super.id = IdGenerator.getId();
        super.black = xf;
        super.blackSession = session;
        this.owner = xf;

        NzqGameCache cache = NzqGameCacheManager.get(xf);
        cache.setRoomId(id);
        NzqGameStatusHandler.changeStatus(xf, NzqStatusEnum.房间中);
    }

    @Override
    public RoomType getRoomType() {
        return RoomType.二人自定义房间;
    }

    @Override
    public boolean tickOut(Long owner, Long xf) {
        if (owner.equals(this.owner)) {
            if (owner.equals(black)) {
                SendMsgUtil.sendMessage(blackSession, new ResponseMsg(GameMsgId.房主踢人));
                SendMsgUtil.sendMessage(whiteSession, new NoticeMsg(GameMsgId.房主踢人));
                white = null;
                whiteSession = null;
                whiteExchange = 0;
            } else {
                SendMsgUtil.sendMessage(whiteSession, new ResponseMsg(GameMsgId.房主踢人));
                SendMsgUtil.sendMessage(blackSession, new NoticeMsg(GameMsgId.房主踢人));
                black = null;
                blackSession = null;
                blackExchange = 0;
            }
        }
        return false;
    }

    public void exist(Long xf) {
        enterRoomLock.lock();
        if (black.equals(xf)) {
            black = null;
            blackSession = null;
            blackExchange = 0;
            if (white == null) {
                // 房间销毁
                CustomRooms.delete(id);
            } else {
                // 房主换人
                owner = white;
                // 通知房间存在的人
                SendMsgUtil.sendMessage(whiteSession, new NoticeMsg(GameMsgId.退出房间));

            }
        } else if (white.equals(xf)) {
            white = null;
            whiteSession = null;
            whiteExchange = 0;
            if (black == null) {
                // 房间销毁
                CustomRooms.delete(id);
            } else {
                // 房主换人
                owner = black;
                // 通知房间存在的人
                SendMsgUtil.sendMessage(blackSession, new NoticeMsg(GameMsgId.退出房间));
            }

        }
        enterRoomLock.lock();
    }

    public void requestExchange(Long xf) {
        long deadline = System.currentTimeMillis() + 5000;
        if (black.equals(xf)) {
            SendMsgUtil.sendMessage(whiteSession, new NoticeMsg(GameMsgId.请求换位));
            blackExchange = deadline;
        } else if (white.equals(xf)) {
            SendMsgUtil.sendMessage(blackSession, new NoticeMsg(GameMsgId.请求换位));
            whiteExchange = deadline;
        }
    }

    public void confirmExchange(Long xf) {
        enterRoomLock.lock();
        long now = System.currentTimeMillis();
        if (black.equals(xf)) {
            if (whiteExchange < now) {
                logger.info("确认换位超时");
                return;
            }
            SendMsgUtil.sendMessage(blackSession, new ResponseMsg(GameMsgId.接受换位));
            SendMsgUtil.sendMessage(whiteSession, new NoticeMsg(GameMsgId.接受换位));
        } else {
            if (blackExchange < now) {
                logger.info("确认换位超时");
                return;
            }
            SendMsgUtil.sendMessage(whiteSession, new ResponseMsg(GameMsgId.接受换位));
            SendMsgUtil.sendMessage(blackSession, new NoticeMsg(GameMsgId.接受换位));
        }

        exchange();
        enterRoomLock.unlock();
    }

    public void rejectExchange(Long xf) {
        long now = System.currentTimeMillis();
        if (black.equals(xf)) {
            if (whiteExchange >= now) {
                SendMsgUtil.sendMessage(whiteSession, new NoticeMsg(GameMsgId.拒绝换位));
            }
        } else {
            if (blackExchange >= now) {
                SendMsgUtil.sendMessage(blackSession, new NoticeMsg(GameMsgId.拒绝换位));
            }
        }
    }

    /**
     * 进入房间
     *
     * @param xf
     * @return 返回进入的位置
     */
    public Position enterRoom(Long xf, Session session) {
        if (isFull()) {
            return Position.NOTHING;
        }
        Position position;

        enterRoomLock.lock();
        Session noticeSession = null;
        if (white == null) {
            super.white = xf;
            super.whiteSession = session;
            noticeSession = blackSession;
            position = Position.WHITE;
        } else if (black == null) {
            super.black = xf;
            super.blackSession = session;
            noticeSession = whiteSession;
            position = Position.BLACK;
        } else {
            // 竞争到锁后没有空位

            position = Position.NOTHING;
        }
        // 成功进入房间，发送推送通知房间的其他人
        if (position!=Position.NOTHING) {
            NzqGameCache cache = NzqGameCacheManager.get(xf);
            cache.setRoomId(id);
            // 推送
            ComeInRoomNotice notice = new ComeInRoomNotice();
            notice.setXf(xf);
            SendMsgUtil.sendMessage(noticeSession, WsResultUtil.createNoticeResult(GameMsgId.接受邀请, notice));
            // 修改状态
            NzqGameStatusHandler.changeStatus(xf, NzqStatusEnum.房间中);

        }
        enterRoomLock.unlock();

        return position;
    }

    public void beginGame(long xf) {
        if (owner == xf) {
            SendMsgUtil.sendMessage(blackSession, new ResponseMsg(GameMsgId.开始游戏));
            SendMsgUtil.sendMessage(whiteSession, new NoticeMsg(GameMsgId.开始游戏));
        }
    }

}
