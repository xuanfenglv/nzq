package com.xuanfeng.nzq.websocket.main.im.handler;

import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;
import com.xuanfeng.nzq.commons.constant.ApplicationStatusEnum;
import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.domain.dao.ApplicationDao;
import com.xuanfeng.nzq.domain.dao.FriendDao;
import com.xuanfeng.nzq.domain.dao.UserDao;
import com.xuanfeng.nzq.domain.mapper.ApplicationMapper;
import com.xuanfeng.nzq.domain.mapper.FriendMapper;
import com.xuanfeng.nzq.domain.model.Application;
import com.xuanfeng.nzq.domain.model.Friend;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.im.msg.notice.AgreeFriendApplicationNotice;
import com.xuanfeng.nzq.websocket.main.im.msg.request.AgreeFriendApplicationReq;
import com.xuanfeng.nzq.websocket.main.im.msg.response.AgreeFriendApplicationResp;
import com.xuanfeng.nzq.websocket.util.ImSessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 同意好友申请处理器
 * @author: lvxianqing
 * @create: 2018/11/22 21:31
 */
@Component
public class AgreeFriendApplicationHandler extends IMsgHandler {
    @Autowired
    private ApplicationMapper mapper;
    @Autowired
    private ApplicationDao applicationDao;
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private UserDao userDao;
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        AgreeFriendApplicationReq req = (AgreeFriendApplicationReq)message;
        // 查询申请信息
        Application application = mapper.selectByPrimaryKey(req.getApplicationId());
        if (xf!=application.getReceiveXf()) {
            WsResultUtil.createRespFailedResult(RetEnum.非法请求, "这不是发送给你的好友申请");
        }
        // 修改状态
        applicationDao.changStatus(req.getApplicationId(), ApplicationStatusEnum.已同意.getStatus());
        // 存库
        // 请求方好友信息
        Friend friend1 = new Friend();
        friend1.setGroupId(application.getFriendGroupId());
        friend1.setFxf(application.getReceiveXf());
        friend1.setXf(application.getSendXf());
        friend1.setRemark(application.getRemark());

        // 接收方好友信息
        Friend friend2 = new Friend();
        friend2.setGroupId(req.getGroupId());
        friend2.setFxf(application.getReceiveXf());
        friend2.setXf(application.getSendXf());
        friend2.setRemark(req.getRemark());

        friendMapper.insertSelective(friend1);
        friendMapper.insertSelective(friend2);
        logger.info("[DB] insert friend,friend1:{},friend2:{}",friend1,friend2);
        // 推送
        String receiveNickname = userDao.queryNickname(xf);
        AgreeFriendApplicationNotice notice = new AgreeFriendApplicationNotice();
        notice.setApplicationId(req.getApplicationId());
        notice.setGroupId(req.getGroupId());
        notice.setXf(xf);
        notice.setNickname(receiveNickname);

        ImSessions.sendMsgToXf(application.getSendXf(),new NoticeMsg(req.getMsgId(),notice));
        // 响应
        String sendNickname = userDao.queryNickname(application.getSendXf());
        AgreeFriendApplicationResp resp = new AgreeFriendApplicationResp();
        resp.setApplicationId(req.getApplicationId());
        notice.setGroupId(application.getFriendGroupId());
        notice.setXf(application.getSendXf());
        notice.setNickname(sendNickname);

        return WsResultUtil.createRespSuccessResult(resp);
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return AgreeFriendApplicationReq.class;
    }
}
