package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.websocket.base.msg.CheckParamResult;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import lombok.Data;

/**
 * @description: 创建房间请求
 * @author: lvxianqing
 * @create: 2019/04/30 17:06
 */
@Data
public class CreateRoomRequest extends RequestMsg {
    private int type;

    @Override
    protected void doCheck(CheckParamResult result) {

    }
}
