package com.xuanfeng.nzq.websocket.session;

import com.alibaba.fastjson.JSON;
import com.xuanfeng.nzq.domain.constant.UserStatusEnum;
import com.xuanfeng.nzq.service.UserService;
import com.xuanfeng.nzq.websocket.javabean.UserCache;
import com.xuanfeng.nzq.websocket.main.chat.MainHandler;
import com.xuanfeng.nzq.websocket.main.chat.msg.push.OfflinePush;
import com.xuanfeng.nzq.websocket.main.chat.msg.push.OnlinePush;
import com.xuanfeng.nzq.websocket.util.ChatSessions;
import com.xuanfeng.nzq.websocket.util.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@Component
@ServerEndpoint("/chatserver")
public class ChatSession {

    // xf号
    private long xf;

    @Autowired
    private MainHandler mainHandler;
    @Autowired
    private UserService loginService;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {

        System.out.println("有新连接加入！当前在线人数为" + ChatSessions.getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println(this.xf + "下线了");
        selfOffline(session);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
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

    public void selfOffline(Session session) throws IOException {
        System.out.println("下线用户是：" + this.xf);
        ChatSessions.removeXf(this.xf);
        sendOfflineNoticeToFriend(session,this.xf);
        System.out.println("有一连接关闭！当前在线人数为" + ChatSessions.getOnlineCount());
        loginService.changeStatus(this.xf, UserStatusEnum.离线);
    }

    private void sendOfflineNoticeToFriend(Session session,long offlineXf) throws IOException {
        UserCache userCache = UserManager.getUserCache(offlineXf);
        // 避免多次序列化
        String offLineMessage = JSON.toJSONString(new OfflinePush(offlineXf));
        userCache.getFriendXf().forEach(friendXf -> {
            try {
                ChatSessions.sendMsgToXf(friendXf, offLineMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static void sendOnlineNoticeToFriend(long xf) {
        UserCache userCache = UserManager.getUserCache(xf);
        String onLineMessage = JSON.toJSONString(new OnlinePush(xf));
        userCache.getFriendXf().forEach(friendXf -> {
            try {
                ChatSessions.sendMsgToXf(friendXf, onLineMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

    //		System.out.println("客户端原始消息>"+message);
//		int index = Integer.parseInt(message.substring(0, 1));
//		switch (index) {
//		case 1:
//			initXf(message);
//			break;
//		case 2:
//			sendMessageToFriend(message);
//			break;
//		case 3:
//			//废除
////			sendOnlineNoticeToFriend(message);
//			break;
//		case 4:
//			//废除
////			sendOfflineNoticeToFriend(message);
//			break;
//		case 5:
//			sendApplicationFriend(message);
//			break;
//		case 6:
//			addNewFriend(message);
//			break;
//		case 8:
//			refuseFriend(message);
//			break;
//		case 9:
//			deleteFriend(message);
//			break;
//		case 0:
////			selfOffline();
//			break;
//		default:
//			break;
//		}

    //
//    public void sendApplicationFriend(String message) {
//        String[] msg = message.split("\\.");
//        if (chatServerTable.containsKey(msg[2])) {
//            try {
//                chatServerTable.get(msg[2]).sendMessage(
//                        "5" + msg[1]);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//
//    //6.
//    public void addNewFriend(String message) {
//        String[] msg = message.split("\\.");
//        if (chatServerTable.containsKey(msg[2])) {
//            try {
//                chatServerTable.get(msg[2]).sendMessage(
//                        "6" + msg[1]);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//
//    //8.
//    public void refuseFriend(String message) {
//        String[] msg = message.split("\\.");
//        if (chatServerTable.containsKey(msg[2])) {
//            try {
//                chatServerTable.get(msg[2]).sendMessage(
//                        "8" + msg[1]);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//
//    //9.
//    public void deleteFriend(String message) {
//        String[] msg = message.split("\\.");
//        if (chatServerTable.containsKey(msg[2])) {
//            try {
//                chatServerTable.get(msg[2]).sendMessage(
//                        "9" + msg[1]);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }

}