package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.util.WsResultUtil;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.Rooms;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.MatchTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.main.game.msg.request.WatchRequest;
import com.xuanfeng.nzq.websocket.main.game.msg.response.WatchResponse;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 观战处理器
 * @author: lvxianqing
 * @create: 2018/12/28 15:08
 */

public class H17 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        WatchRequest request = (WatchRequest)message;
        MatchTwoPeopleRoom room = (MatchTwoPeopleRoom)Rooms.get(request.getRoomId());
        int[][] board = room.getBoard();
        int modCount = room.getModCount();
        int total = room.getTotal();
        WatchResponse response = new WatchResponse(board,total,modCount);
        room.addAudience(session);
        return WsResultUtil.createRespSuccessResult(response);
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return null;
    }
}
