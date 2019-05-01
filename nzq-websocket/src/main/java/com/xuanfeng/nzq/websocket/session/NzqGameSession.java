package com.xuanfeng.nzq.websocket.session;

import com.xuanfeng.nzq.domain.constant.NzqStatusEnum;
import com.xuanfeng.nzq.websocket.base.manager.base.MainHandler;
import com.xuanfeng.nzq.websocket.component.NzqGameStatusHandler;
import com.xuanfeng.nzq.websocket.javabean.NzqGameCache;
import com.xuanfeng.nzq.websocket.session.base.WsServer;
import com.xuanfeng.nzq.websocket.util.NzqGameCacheManager;
import com.xuanfeng.nzq.websocket.util.NzqGameSessions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@Component
@ServerEndpoint("/nzqgameserver")
public class NzqGameSession extends WsServer {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static MainHandler mainHandler;

	public void setXf(long xf) {
		this.xf = xf;
	}

	@Autowired
	@Qualifier("nzqGameMainHandler")
	public void setNzqGameMainHandler(MainHandler chatMainHandler) {
		NzqGameSession.mainHandler = chatMainHandler;
	}

	/**
	 * 连接建立成功调用的方法
	 *
	 * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {

		logger.info("有新连接加入！当前在线人数为" + NzqGameSessions.getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) throws IOException {
		selfOffline();
	}


	@OnError
	public void onError(Session session, Throwable error) {
		logger.info("发生错误");
//		error.printStackTrace();
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		mainHandler.process(message, this, session);
	}


	public void selfOffline() throws IOException {
		// 推送下线消息
		NzqGameStatusHandler.changeStatus(this.xf, NzqStatusEnum.离线);

		NzqGameSessions.removeXf(this.xf);

		NzqGameCache cache = NzqGameCacheManager.get(this.xf);
		// 如果在战斗中，先不清缓存，等待玩家断线重连使用
		if (cache.getNzqStatusEnum() != NzqStatusEnum.战斗中) {
			NzqGameCacheManager.remove(this.xf);
			// TODO: 2019/1/2 断线不重连的垃圾回收
		}
		logger.info("下线用户是：{},当前在线人数为:{}", this.xf, NzqGameSessions.getOnlineCount());

	}
}
