package com.xuanfeng.nzq.websocket.main.game.handler;

import com.xuanfeng.nzq.domain.constant.NzqStatusEnum;
import com.xuanfeng.nzq.domain.dao.FriendDao;
import com.xuanfeng.nzq.domain.entity.GameFriendInfo;
import com.xuanfeng.nzq.websocket.base.msg.request.RequestMsg;
import com.xuanfeng.nzq.websocket.base.msg.response.ResponseMsg;
import com.xuanfeng.nzq.websocket.base.process.base.IMsgHandler;
import com.xuanfeng.nzq.websocket.component.NzqGameStatusHandler;
import com.xuanfeng.nzq.websocket.javabean.NzqGameCache;
import com.xuanfeng.nzq.websocket.main.game.constant.GameMsgId;
import com.xuanfeng.nzq.websocket.main.im.msg.request.InitAccountReq;
import com.xuanfeng.nzq.websocket.util.ImSessions;
import com.xuanfeng.nzq.websocket.util.NzqGameCacheManager;
import com.xuanfeng.nzq.websocket.util.NzqGameSessions;
import com.xuanfeng.nzq.websocket.util.WsResultUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.util.List;
import java.util.Set;

/**
 * @description: 初始化账号
 * @author: lvxianqing
 * @create: 2018/09/30 16:31
 */
public class H1 extends IMsgHandler {

    @Autowired
    private FriendDao friendDao;

    @Override
    protected ResponseMsg handle(RequestMsg message, long xf, Session session) {
        InitAccountReq req = (InitAccountReq) message;
        // TODO: 2018/11/23 token校验

        if (NzqGameSessions.constainsXf(xf)) {
            NzqGameSessions.sendMsgToXf(xf, WsResultUtil.createNoticeResult(GameMsgId.初始化账号));

            NzqGameSessions.removeXf(xf);
        }
        // TODO: 2019/1/2 判断是否需要断线重连
        NzqGameCache cache = NzqGameCacheManager.get(xf);

        if (cache != null && cache.getNzqStatusEnum() == NzqStatusEnum.战斗中) {
            // TODO: 2019/1/2 如果房间还在
        }
        // 初始化用户信息
        Set<Long> fXfs = friendDao.selectFriendIds(xf);
        NzqGameCache NzqGameCache = new NzqGameCache();
        NzqGameCache.setXf(xf);
        NzqGameCache.setFriendXf(fXfs);
        NzqGameCacheManager.add(xf, NzqGameCache);

        NzqGameSessions.addxf(xf, session);

        NzqGameStatusHandler.changeStatus(xf, NzqStatusEnum.闲逛中);

        List<GameFriendInfo> gameOnlineFriendInfos = friendDao.selectGameFriendInfos(xf);

        logger.info("用户上线,xf:{}：", xf);

        return WsResultUtil.createRespSuccessResult(gameOnlineFriendInfos);
    }

    @Override
    protected Class<? extends RequestMsg> getRequestMessageType() {
        return InitAccountReq.class;
    }
}
