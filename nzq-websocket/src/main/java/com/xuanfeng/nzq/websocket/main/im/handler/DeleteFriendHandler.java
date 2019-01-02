package com.xuanfeng.nzq.websocket.main.im.handler;

import com.xuanfeng.nzq.websocket.util.WsResultUtil;
import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.domain.dao.FriendDao;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.im.msg.notice.DeleteFriendNotice;
import com.xuanfeng.nzq.websocket.main.im.msg.request.DeleteFriendReq;
import com.xuanfeng.nzq.websocket.main.im.msg.response.DeleteFriendResp;
import com.xuanfeng.nzq.websocket.util.ImSessions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 删除好友处理器
 * @author: lvxianqing
 * @create: 2018/11/23 19:00
 */

public class DeleteFriendHandler extends IMsgHandler {

    @Autowired
    private FriendDao friendDao;

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        DeleteFriendReq req = (DeleteFriendReq)message;
        friendDao.deleteFriend(xf, req.getXf());
        // 推送

        DeleteFriendNotice notice = new DeleteFriendNotice();
        notice.setXf(xf);
        ImSessions.sendMsgToXf(req.getXf(),new NoticeMsg(req.getMsgId(),notice));
        // 响应
        DeleteFriendResp resp = new DeleteFriendResp();
        resp.setXf(req.getXf());
        return WsResultUtil.createRespSuccessResult(resp);
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return DeleteFriendReq.class;
    }
}
