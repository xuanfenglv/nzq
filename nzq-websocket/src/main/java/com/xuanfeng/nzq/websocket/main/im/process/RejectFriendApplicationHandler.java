package com.xuanfeng.nzq.websocket.main.im.process;

import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.commons.WsResultUtil;
import com.xuanfeng.nzq.commons.constant.ApplicationStatusEnum;
import com.xuanfeng.nzq.commons.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.commons.msg.request.RequestMsg;
import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;
import com.xuanfeng.nzq.domain.dao.ApplicationDao;
import com.xuanfeng.nzq.domain.dao.FriendDao;
import com.xuanfeng.nzq.domain.mapper.ApplicationMapper;
import com.xuanfeng.nzq.domain.mapper.FriendMapper;
import com.xuanfeng.nzq.domain.model.Application;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.im.msg.push.RejectFriendApplicationNotice;
import com.xuanfeng.nzq.websocket.main.im.msg.request.RejectFriendApplicationReq;
import com.xuanfeng.nzq.websocket.main.im.msg.response.RejectFriendApplicationResp;
import com.xuanfeng.nzq.websocket.util.ChatSessions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 拒绝好友申请处理器
 * @author: lvxianqing
 * @create: 2018/11/23 18:39
 */

public class RejectFriendApplicationHandler extends IMsgHandler {
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private ApplicationMapper mapper;
    @Autowired
    private ApplicationDao applicationDao;

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        RejectFriendApplicationReq req = (RejectFriendApplicationReq)message;
        Application application = mapper.selectByPrimaryKey(req.getApplicationId());
        if (xf != application.getReceiveXf()) {
            return WsResultUtil.createFailedResult(RetEnum.非法请求, "这他妈不是发送给你的好友申请");
        }
        applicationDao.changStatus(req.getApplicationId(), ApplicationStatusEnum.已拒绝.getStatus());
        // 推送
        RejectFriendApplicationNotice notice = new RejectFriendApplicationNotice();
        notice.setApplicationId(req.getApplicationId());
        ChatSessions.sendMsgToXf(application.getSendXf(),new NoticeMsg(req.getMsgId(),notice));
        // 响应
        RejectFriendApplicationResp resp = new RejectFriendApplicationResp();
        resp.setApplicationId(req.getApplicationId());
        return WsResultUtil.createSuccessResult(resp);
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return RejectFriendApplicationReq.class;
    }
}
