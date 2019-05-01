package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.CustomRooms;
import com.xuanfeng.nzq.websocket.main.game.javabean.room.CustomTwoPeopleRoom;
import com.xuanfeng.nzq.websocket.main.game.msg.request.CreateRoomRequest;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 创建2人房间
 * @author: lvxianqing
 * @create: 2018/12/21 17:16
 */

public class H2 extends IMsgHandler {
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        CreateRoomRequest request = (CreateRoomRequest) message;
        // todo 双人房间
        if (request.getType() == 2) {
            CustomTwoPeopleRoom room = new CustomTwoPeopleRoom(xf,session);
            CustomRooms.add(room);
        } else if(request.getType()==3) {
            // todo
        } else {
            // 不做处理
            return null;
        }


        return WsResultUtil.createRespSuccessResult(request.getType());
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return CreateRoomRequest.class ;
    }
}
