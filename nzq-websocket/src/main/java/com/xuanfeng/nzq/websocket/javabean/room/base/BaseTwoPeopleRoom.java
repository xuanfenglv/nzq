package com.xuanfeng.nzq.websocket.javabean.room.base;

import com.xuanfeng.nzq.domain.constant.UserStatusEnum;
import com.xuanfeng.nzq.websocket.component.Rooms;
import com.xuanfeng.nzq.websocket.component.ImStatusHandler;
import com.xuanfeng.nzq.websocket.constant.RoomType;
import com.xuanfeng.nzq.websocket.main.game.constant.ChessColor;
import com.xuanfeng.nzq.websocket.main.game.constant.DropResult;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.game.msg.common.DropChessResult;
import com.xuanfeng.nzq.websocket.main.game.pool.WatchThreadPool;
import com.xuanfeng.nzq.websocket.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.util.NzqUtil;
import com.xuanfeng.nzq.websocket.util.SendMsgUtil;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 二人房间
 * @author: lvxianqing
 * @create: 2018/12/21 14:40
 */
public abstract class BaseTwoPeopleRoom extends BaseRoom {
    // 房间id

    protected Long black;
    protected Long white;
    protected Session blackSession;
    protected Session whiteSession;

    // 游戏开始时间
    private long beginTime;
    // 游戏结束时间
    private long endTime;

    // 观众连接
    private List<Session> audience;
    // 棋盘数组
    protected int[][] board = new int[15][15];
    // 总棋数
    protected int total=0;
    // 修改次数
    protected transient int modCount = 0;
    // 下棋日志记录（用于存储日志，悔棋）
    protected List<DropChessResult> logs;

    public boolean isFull() {
        if(black!=null&&white!=null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean constainsPlayer(Long xf) {
        if (xf.equals(black)||xf.equals(white)) {
            return true;
        }
        return false;
    }

    @Override
    public void sendNoticeToOthers(Long xf, NoticeMsg noticeMsg) {
        if (black.equals(xf)) {
            SendMsgUtil.sendMessage(whiteSession, noticeMsg);
        } else {
            SendMsgUtil.sendMessage(blackSession,noticeMsg);
        }
    }

    /**
     * 房间销毁
     */
    private void destroy() {

        endTime = System.currentTimeMillis();
        
        // 修改玩家状态（匹配结束后玩家返回大厅，自定义房间比赛结束后返回房间）
        if (getRoomType() == RoomType.二人匹配房间 || getRoomType() == RoomType.三人匹配房间) {
            // 移除房间
            Rooms.delete(id);
            ImStatusHandler.changeStatus(black, UserStatusEnum.在线);
            ImStatusHandler.changeStatus(white, UserStatusEnum.在线);
        } else {
            // 房间复位（初始化为新房）
            // TODO: 2018/12/29  
            ImStatusHandler.changeStatus(black, UserStatusEnum.在线);
            ImStatusHandler.changeStatus(white, UserStatusEnum.在线);
        }
        // TODO: 2018/12/21 存储比赛记录
    }

    /**
     * 换位
     */
    protected void exchange() {
        Long mid = black;
        black = white;
        white = mid;

        Session midSession= blackSession;
        blackSession = whiteSession;
        whiteSession=midSession;
    }

    public void drop(Long xf, byte row, byte column) {
        if (!dropCheck(xf, row, column)) {
            return;
        }
        // 棋子类型
        int chessColor;
        Session noticeSession = null;
        Session responseSession = null;

        if (xf.equals(black)) {
            chessColor = ChessColor.BLACK.getId();
            responseSession = blackSession;
            noticeSession = whiteSession;
        } else {
            chessColor = ChessColor.WHITE.getId();
            responseSession = whiteSession;
            noticeSession = blackSession;
        }
        board[row][column] = chessColor;
        total++;
        modCount++;

        // 判断是否胜利
        boolean isWin = NzqUtil.isWin5(board, row, column);
        DropChessResult dropChessResult = new DropChessResult();
        dropChessResult.setRow(row);
        dropChessResult.setColumn(column);
        dropChessResult.setColor(chessColor);
        logs.add(dropChessResult);
        if (isWin) {
            dropChessResult.setType(DropResult.WIN.getId());
            destroy();
        } else if (total == 225) {
            // 平局
            dropChessResult.setType(DropResult.HE.getId());
            destroy();
        } else {
            dropChessResult.setType(DropResult.GO_ON.getId());
        }
        ResponseMsg responseMsg = WsResultUtil.createRespSuccessResult(dropChessResult);
        responseMsg.setMsgId(GameMsgId.落子.getMsgId());
        SendMsgUtil.sendMessage(responseSession, responseMsg);
        SendMsgUtil.sendMessage(noticeSession, WsResultUtil.createNoticeResult(GameMsgId.落子.getMsgId(), dropChessResult));

        // 向观战者推送
        WatchThreadPool.push(audience,dropChessResult);
    }


    private boolean dropCheck(Long xf, int row, int column) {
        int yu = total % 2;
        Long nowDropPlayer = null;
        if (yu == 0) {
            nowDropPlayer = black;
        } else {
            nowDropPlayer = white;
        }

        if (!xf.equals(nowDropPlayer)) {
            // TODO: 2018/12/21 推送
            return false;
        }
        if (board[row][column] != 0) {
            // TODO: 2018/12/21 此处已有棋子
            return false;
        }
        return true;
    }

    public void addAudience(Session session) {
        if (audience == null) {
            audience = new ArrayList<>();
        }
        audience.add(session);
    }
    //===========getter/setter================

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<Session> getAudience() {
        return audience;
    }

    public void setAudience(List<Session> audience) {
        this.audience = audience;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getBlack() {
        return black;
    }

    public void setBlack(Long black) {
        this.black = black;
    }

    public Long getWhite() {
        return white;
    }

    public void setWhite(Long white) {
        this.white = white;
    }

    public Session getBlackSession() {
        return blackSession;
    }

    public void setBlackSession(Session blackSession) {
        this.blackSession = blackSession;
    }

    public Session getWhiteSession() {
        return whiteSession;
    }

    public void setWhiteSession(Session whiteSession) {
        this.whiteSession = whiteSession;
    }

    public int getModCount() {
        return modCount;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }
}
