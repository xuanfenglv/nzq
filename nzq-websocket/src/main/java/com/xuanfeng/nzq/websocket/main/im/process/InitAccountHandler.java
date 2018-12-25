package com.xuanfeng.nzq.websocket.main.im.process;

import com.xuanfeng.nzq.commons.WsResultUtil;
import com.xuanfeng.nzq.commons.msg.notice.NoticeMsg;
import com.xuanfeng.nzq.commons.msg.request.RequestMsg;
import com.xuanfeng.nzq.commons.msg.response.ResponseMsg;
import com.xuanfeng.nzq.domain.constant.UserStatusEnum;
import com.xuanfeng.nzq.domain.dao.FriendDao;
import com.xuanfeng.nzq.domain.dao.FriendGroupDao;
import com.xuanfeng.nzq.domain.entity.FriendInfo;
import com.xuanfeng.nzq.domain.entity.GroupInfo;
import com.xuanfeng.nzq.service.UserService;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.main.im.component.StatusHandler;
import com.xuanfeng.nzq.websocket.main.im.msg.request.InitAccountReq;
import com.xuanfeng.nzq.websocket.main.im.msg.response.InitAccountResp;
import com.xuanfeng.nzq.websocket.util.ChatSessions;
import com.xuanfeng.nzq.websocket.util.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @description: 初始化账号
 * @author: lvxianqing
 * @create: 2018/09/30 16:31
 */
@Component
public class InitAccountHandler extends IMsgHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private StatusHandler statusHandler;
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private FriendGroupDao friendGroupDao;

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) {
        InitAccountReq req = (InitAccountReq) message;
        // TODO: 2018/11/23 token校验


        if (ChatSessions.constainsXf(xf)) {
            try {
                ChatSessions.sendMsgToXf(xf, new NoticeMsg(req.getMsgId()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ChatSessions.removeXf(xf);
        }
        // 初始化用户信息
        Set<Long> fXfs = friendDao.selectFriendIds(xf);
        UserCache userCache = new UserCache();
        userCache.setXf(xf);
        userCache.setFriendXf(fXfs);
        UserManager.add(xf, userCache);

        ChatSessions.addxf(xf, session);
        // TODO: 2018/11/23 短线重连
        statusHandler.changeStatus(xf,UserStatusEnum.闲逛中);

        logger.info("用户上线,xf:{}：" , xf);
        // 好友列表
        List<FriendInfo> friendInfos = friendDao.selectFriendInfos(xf);
        List<GroupInfo> groupInfos = friendGroupDao.selectGroupInfos(xf);
        InitAccountResp resp = new InitAccountResp();
        resp.setFriendList(friendInfos);
        resp.setGroupList(groupInfos);

        return WsResultUtil.createSuccessResult(resp);
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return InitAccountReq.class;
    }
}
