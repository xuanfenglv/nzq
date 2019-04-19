package com.xuanfeng.nzq.websocket.main.im.handler;

import com.xuanfeng.nzq.commons.constant.ApplicationStatusEnum;
import com.xuanfeng.nzq.domain.dao.UserDao;
import com.xuanfeng.nzq.domain.model.Application;
import com.xuanfeng.nzq.service.ApplicationService;
import com.xuanfeng.nzq.websocket.base.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.im.msg.notice.SendFriendApplicationNotice;
import com.xuanfeng.nzq.websocket.main.im.msg.request.SendFriendApplicationReq;
import com.xuanfeng.nzq.websocket.main.im.msg.response.SendFriendApplicationResp;
import com.xuanfeng.nzq.websocket.util.ImSessions;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @description: 发送好友申请处理器
 * @author: lvxianqing
 * @create: 2018/10/03 10:55
 */
@Component
public class IM_H8 extends IMsgHandler {

    @Autowired
    private ApplicationService service;
    @Autowired
    private UserDao userDao;
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        SendFriendApplicationReq req = (SendFriendApplicationReq)message;
        // TODO: 2018/11/23 不能是自己和好友

        // 存库
        Application applicationd = new Application();
        BeanUtils.copyProperties(req, applicationd);
        applicationd.setSendVisible(true);
        applicationd.setReceiveVisible(true);
        applicationd.setSendXf(xf);
        applicationd.setStatus(ApplicationStatusEnum.已发送申请.getStatus());
        service.createOrUpdate(applicationd);
        // 查询昵称
        String sendNickname = userDao.queryNickname(xf);
        String receiveNickname = userDao.queryNickname(req.getReceiveXf());

        // 推送
        SendFriendApplicationNotice notice = new SendFriendApplicationNotice();
        notice.setXf(xf);
        notice.setNickname(sendNickname);
        notice.setText(req.getText());
        notice.setId(applicationd.getId());

        ImSessions.sendMsgToXf(req.getReceiveXf(),new NoticeMsg(req.getMsgId(),notice));
        // 响应
        SendFriendApplicationResp resp = new SendFriendApplicationResp();
        resp.setId(applicationd.getId());
        resp.setXf(req.getReceiveXf());
        resp.setNickname(receiveNickname);
        return WsResultUtil.createRespSuccessResult(resp);
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return SendFriendApplicationReq.class;
    }
}
