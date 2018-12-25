package com.xuanfeng.nzq.websocket.main.im.process;

import com.xuanfeng.nzq.commons.constant.ApplicationStatusEnum;
import com.xuanfeng.nzq.commons.msg.request.RequestMsg;
import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;
import com.xuanfeng.nzq.domain.dao.UserDao;
import com.xuanfeng.nzq.domain.model.Application;
import com.xuanfeng.nzq.service.ApplicationService;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.main.im.msg.push.SendFriendApplicationNotice;
import com.xuanfeng.nzq.websocket.main.im.msg.request.SendFriendApplicationReq;
import com.xuanfeng.nzq.websocket.main.im.msg.response.SendFriendApplicationResp;
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
public class SendFriendApplicationHandler extends IMsgHandler {

    @Autowired
    private ApplicationService service;
    @Autowired
    private UserDao userDao;
    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) throws IOException {
        SendFriendApplicationReq req = (SendFriendApplicationReq)message;
        // TODO: 2018/11/23 不能是自己和好友

        // 存库
        Application application = new Application();
        BeanUtils.copyProperties(req, application);
        application.setSendVisible(true);
        application.setReceiveVisible(true);
        application.setSendXf(xf);
        application.setStatus(ApplicationStatusEnum.已发送申请.getStatus());
        service.createOrUpdate(application);
        // 查询昵称
        String sendNickname = userDao.queryNickname(xf);
        String receiveNickname = userDao.queryNickname(req.getReceiveXf());

        // 推送
        SendFriendApplicationNotice push = new SendFriendApplicationNotice();
        push.setXf(xf);
        push.setNickname(sendNickname);
        push.setText(req.getText());
        push.setApplicationId(application.getId());
        // 响应
        SendFriendApplicationResp resp = new SendFriendApplicationResp();
        resp.setApplicationId(application.getId());
        resp.setXf(req.getReceiveXf());
        resp.setNickname(receiveNickname);
        return resp;
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return SendFriendApplicationReq.class;
    }
}
