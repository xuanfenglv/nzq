package com.xuanfeng.nzq.websocket.session;

import com.xuanfeng.nzq.websocket.javabean.ThreePeopleRoom;
import com.xuanfeng.nzq.websocket.javabean.TwoPeopleRoom;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;


//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@Component
@ServerEndpoint("/nzqgameserver")
public class NzqGameSession {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	// xf号
	private String roomId;
	private static boolean match5System = false;
	private static boolean match4System = false;
	public String getRoomId() {
		return roomId;
	}
	
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	private static Vector<NzqGameSession> match5 = new Vector<NzqGameSession>();
	private static Vector<NzqGameSession> match4 = new Vector<NzqGameSession>();
	private static Hashtable<String, TwoPeopleRoom> twoRooms = new Hashtable<String, TwoPeopleRoom>();
	private static Hashtable<String, ThreePeopleRoom> threeRooms = new Hashtable<String, ThreePeopleRoom>();
	private String xf;
	private static Hashtable<String, NzqGameSession> nzqGameServerTable = new Hashtable<String, NzqGameSession>();
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("客户端原始消息>"+message);
		int index = Integer.parseInt(message.substring(0, 2));
		switch (index) {
		case 1:
			initXf(message);
			break;
		case 2:
			sendInvitationToFriend(message);
			break;
		case 3:
			acceptInvitation2(message);
			break;
		case 4:
			changeLocation();
			break;
		case 5:
			initToFangzhu();
			break;
		case 6:
			refuseInvitatation2(message);
			break;
		case 7:
			existRoom();
			break;
		case 8:
			tickOut();
			break;
		case 9:
			requestChangeLocation();
			break;
		case 10:
			changeLocationBetweenTwoPlayer(message);
			break;
		case 11:
			beginGy5();
			break;
		case 12:
			drop5(message);
			break;
		case 13:
			sendInnerMsg(message);
			break;
		case 14:
			sendEmoticon(message);
			break;
		case 15:
			sendVoice(message);
			break;
		case 16:
			destroyRoom();
			break;
		case 17:
			create3Room();
			break;
		case 18:
			sendInvitationToFriend3(message);
			break;
		case 19:
			acceptInvitation3(message);
			break;
		case 20:
			changeLocation3(message);
			break;
		case 21:
			refuseInvitatation3(message);
			break;
		case 22:
			existRoom3();
			break;
		case 23:
			tickOut(message);
			break;
		case 24:
			requestChangeLocation3(message);
			break;
		case 25:
			changeLocationBetweenTwoPlayer3(message);
			break;
		case 26:
			beginGy4();;
			break;
		case 27:
			drop4(message);
			break;
		case 28:
			sendInnerMsg3(message);
			break;
		case 29:
			sendEmoticon3(message);
			break;
		case 30:
			sendVoice3(message);
			break;
		case 32:
			enterMatch5();
			break;
		case 33:
			match5Confirm(message);
			break;
		case 34:
			destroyPp5Room();
			break;
		case 35:
			enterMatch4();
			break;
		case 36:
			match4Confirm(message);
			break;
		case 37:
			destroyPp4Room();
			break;
		default:
			break;
		}
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
//		error.printStackTrace();
	}
	//1
	public void initXf(String message) {
		String xf = message.substring(2);
		this.xf = xf;
		if(nzqGameServerTable.containsKey(xf)) {
			nzqGameServerTable.remove(xf);
		} 
		nzqGameServerTable.put(xf, this);
		System.out.println("nzq上线用户："+xf);
		try {
			nzqGameServerTable.get(xf).sendMessage("01NzqGame成功连接到服务器");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	//2
	public void sendInvitationToFriend(String message) {
		String[] msg = message.split("\\.");
		System.out.println(msg[0]+msg[1]+msg[2]);
		if (nzqGameServerTable.containsKey(msg[2])) {
			try {
				nzqGameServerTable.get(msg[2]).sendMessage(
						"02." + msg[1]+"."+this.roomId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//存入数据库代码
		}
	}
	//3
	public void acceptInvitation2(String message) {
		String roomId = message.substring(3);
		if (twoRooms.containsKey(roomId)) {//房间存在
			TwoPeopleRoom room = twoRooms.get(roomId);
			if(room.isFull()) {//房间已经满了
				try { 
					this.sendMessage("03.3");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {//房间未满
				this.roomId = roomId;
				if(room.getBlack()==null) {//我是黑色
					room.setBlack(this);
					try {
						room.getWhite().sendMessage("03.1."+this.xf);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						this.sendMessage("03.2."+room.getWhite().xf+".b");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {//我是白色
					room.setWhite(this);
					try {
						room.getBlack().sendMessage("03.1."+this.xf);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						this.sendMessage("03.2."+room.getBlack().xf+".w");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		} else {////房间不存在
			try {
				this.sendMessage("03.4");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//4
	public void changeLocation() {
		TwoPeopleRoom room = twoRooms.get(this.roomId);
		room.exchangeLocation();
		try {
			this.sendMessage("04");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//5
	public void initToFangzhu() {
//		this.status = 1;
//		this.location = 'b';
		long date = new Date().getTime();
		this.roomId = this.xf + date;
		System.out.println("创建房间编号为"+this.roomId);
		TwoPeopleRoom two = new TwoPeopleRoom();
		two.setBlack(this);
		two.setFangzhu("black");
		twoRooms.put(this.roomId, two); 
	}
	//6
	public void refuseInvitatation2(String message) {
//		String roomId = message.substring(3);
//		if (twoRooms.containsKey(roomId)) {//房间存在
//			TwoPeopleRoom room = twoRooms.get(roomId);
//			try {
//				twoRooms.get(roomId).getFangzhuPlayer().sendMessage("06."+this.xf);
//				System.out.println("发送结束");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		String fxf = message.substring(3);
		if (nzqGameServerTable.containsKey(fxf)) {//好友存在
			try {
				nzqGameServerTable.get(fxf).sendMessage("06."+this.xf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//7
	public void existRoom() {
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
		if(myRoom.getBlack()==this) {
			myRoom.setBlack(null);
			if(myRoom.isEmpty()) {
				System.out.println("房间已销毁");
				twoRooms.remove(this.roomId);
				System.out.println("销毁end");
			} else {
				System.out.println("房间有人，未销毁");
				myRoom.setFangzhu("white");
				try {
					myRoom.getWhite().sendMessage("07");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			myRoom.setWhite(null);
			if(myRoom.isEmpty()) {
				System.out.println("房间已销毁");
				twoRooms.remove(this.roomId);
			} else {
				System.out.println("房间有人，未销毁");
				myRoom.setFangzhu("black");
				try {
					myRoom.getBlack().sendMessage("07");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//8
	public void tickOut() {
//		System.out.println("开始踢人了");
//		System.out.println("踢人的房间号："+this.roomId);
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
//		System.out.println("房主是："+myRoom.getFangzhu());
		myRoom.tickOut();
//		System.out.println("踢人结束");
	}
	//9
	public void requestChangeLocation() {
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
		myRoom.sendRequestChangeLocation(this);
	}
	//10
	public void changeLocationBetweenTwoPlayer(String msg) {
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
		char flag = msg.charAt(3);
		if(flag == '1') {
			myRoom.changeLocationBetweenTwoPlayer(this);
		} else {
			myRoom.refuseChangeLocationBetweenTwoPlayer(this);
		}
		 
	}
	//11
	public void beginGy5() {
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
		myRoom.beginGame(this);
	}
	//12
	public void drop5(String msg) {
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
		myRoom.noticeDrop(this, msg);
	}
	//13
	public void sendInnerMsg(String msg) {
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
		myRoom.transmitMsg(this, msg);
	}
	//14
	public void sendEmoticon(String msg) {
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
		myRoom.transmitMsg(this, msg);
	}
	//15
	public void sendVoice(String msg) {
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
		myRoom.transmitMsg(this, msg);
	}
	//16
	public void destroyRoom() {
		twoRooms.remove(this.roomId);
		System.out.println("比赛结束，房间销毁！");
	}
	
	//17
	public void create3Room() {
		long date = new Date().getTime();
		this.roomId = this.xf + date;
		System.out.println("创建三人房间编号为"+this.roomId);
		ThreePeopleRoom three = new ThreePeopleRoom();
		three.setBlack(this);
		three.setFangzhu("black");
		threeRooms.put(this.roomId, three); 
	}
	//18
		public void sendInvitationToFriend3(String message) {
			String[] msg = message.split("\\.");
			System.out.println(msg[0]+msg[1]+msg[2]);
			if (nzqGameServerTable.containsKey(msg[2])) {
				try {
					nzqGameServerTable.get(msg[2]).sendMessage(
							"18." + msg[1]+"."+this.roomId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				//存入数据库代码
			}
		}
	//19
		public void acceptInvitation3(String message) {
			String roomId = message.substring(3);
			if (threeRooms.containsKey(roomId)) {//房间存在
				ThreePeopleRoom room = threeRooms.get(roomId);
				if(room.isFull()) {//房间已经满了
					try { 
						this.sendMessage("19.3");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {//房间未满
					this.roomId = roomId;
					if(room.getBlack()==null) {//我是黑色
						room.setBlack(this);
						room.sendMsgFrom("black","19.1."+this.xf+".b");
						try {
							this.sendMessage("19.2.b."+room.otherPlayerLocation("black"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if(room.getWhite()==null) {//我是白色
						room.setWhite(this);
						room.sendMsgFrom("white","19.1."+this.xf+".w");
						try {
							this.sendMessage("19.2.w."+room.otherPlayerLocation("white"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}  else if(room.getRed()==null) {//我是红色
						room.setRed(this);
						room.sendMsgFrom("red","19.1."+this.xf+".r");
						try {
							this.sendMessage("19.2.r."+room.otherPlayerLocation("red"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
					
				}
			} else {////房间不存在
				try {
					this.sendMessage("19.4");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	//20
		public void changeLocation3(String message) {
			String toLoc = message.substring(3);
			ThreePeopleRoom room = threeRooms.get(this.roomId);
			String myColor = room.getMyLoc(this);
			
			if(myColor.equals("black")) {
				room.setBlack(null);
			} else if(myColor.equals("white")) {
				room.setWhite(null);
			} else {
				room.setRed(null);
			}
			
			if(toLoc.equals("black")) {//去黑方空位
				room.setBlack(this);
				if(room.getBlack()==this) {
					System.out.println("成功放到黑方");
				}
				if(myColor.equals("white")) {//我是白方
					System.out.println("白到黑");
					if(room.getRed()!=null) {
						try {
							room.getRed().sendMessage("20.white.black");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {//我是红方
					System.out.println("红到黑");
					if(room.getWhite()!=null) {
						try {
							room.getWhite().sendMessage("20.red.black");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else if(toLoc.equals("white")) {//去白方空位
				room.setWhite(this);
				if(room.getWhite()==this) {
					System.out.println("成功放到白方");
				}
				if(myColor.equals("black")) {
					System.out.println("黑到白");
					if(room.getRed()!=null) {
						try {
							room.getRed().sendMessage("20.black.white");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					System.out.println("红到白");
					if(room.getBlack()!=null) {
						try {
							room.getBlack().sendMessage("20.red.white");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {//去红方空位
				room.setRed(this);
				if(room.getRed()==this) {
					System.out.println("成功放到红方");
				}
				if(myColor.equals("black")) {
					System.out.println("黑到红");
					if(room.getWhite()!=null) {
						try {
							room.getWhite().sendMessage("20.black.red");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					System.out.println("白到红");
					if(room.getBlack()!=null) {
						try {
							room.getBlack().sendMessage("20.white.red");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
			if(room.getFangzhuPlayer()==this) {
				room.setFangzhu(toLoc);
			}
			
			try {
				this.sendMessage("20");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//21
		public void refuseInvitatation3(String message) {
			String fxf = message.substring(3);
			if (nzqGameServerTable.containsKey(fxf)) {//好友存在
				try {
					nzqGameServerTable.get(fxf).sendMessage("21."+this.xf);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//22
		public void existRoom3() {
			ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
			if(myRoom.getBlack()==this) {
				myRoom.setBlack(null);
				if(myRoom.isEmpty()) {
					System.out.println("房间已销毁");
					threeRooms.remove(this.roomId);
					System.out.println("销毁end");
				} else {
					System.out.println("房间有人，未销毁");
					myRoom.fangzhuJicheng();
					myRoom.sendMsgFrom("black", "22.black");
				}
			} else if(myRoom.getWhite()==this) {
				myRoom.setWhite(null);
				if(myRoom.isEmpty()) {
					System.out.println("房间已销毁");
					twoRooms.remove(this.roomId);
				} else {
					System.out.println("房间有人，未销毁");
					myRoom.fangzhuJicheng();
					myRoom.sendMsgFrom("white", "22.white");
				}
			} else {
				myRoom.setRed(null);
				if(myRoom.isEmpty()) {
					System.out.println("房间已销毁");
					threeRooms.remove(this.roomId);
				} else {
					System.out.println("房间有人，未销毁");
					myRoom.fangzhuJicheng();
					myRoom.sendMsgFrom("red", "22.red");
				}
			}
		}
		
	//23
	public void	tickOut(String message) {
		String existColor = message.substring(3);
		ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
		if(existColor.equals("black")) {
			try {
				myRoom.getBlack().sendMessage("23");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myRoom.setBlack(null);
		} else if(existColor.equals("white")) {
			System.out.println("踢白");
			try {
				System.out.println("通知白方被踢");
				myRoom.getWhite().sendMessage("23");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("清空白方");
			myRoom.setWhite(null);
		} else {
			try {
				myRoom.getRed().sendMessage("23");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myRoom.setRed(null);
		}
	}
	//24
	public void requestChangeLocation3(String message) {
		String reqColor = message.substring(3);
		ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
		if(reqColor.equals("black")) {
			try {
				myRoom.getBlack().sendMessage("24."+myRoom.getMyLoc(this));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(reqColor.equals("white")) {
			try {
				myRoom.getWhite().sendMessage("24."+myRoom.getMyLoc(this));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				myRoom.getRed().sendMessage("24."+myRoom.getMyLoc(this));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//25
	public void changeLocationBetweenTwoPlayer3(String message) {
		char result = message.charAt(3);
		ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
		String player = message.substring(5);
		if(result == '1') {
//			myRoom.sendMsgFrom(this,"25.1."+myRoom.getMyLoc(this)+"."+player);
			
			if(this==myRoom.getBlack()) {//我是黑色
				if(player.equals("white")) {
					try {
						myRoom.getWhite().sendMessage("25.1."+myRoom.getMyLoc(this));
						
						myRoom.getRed().sendMessage("25.2."+myRoom.getMyLoc(this)+"."+player);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						myRoom.getRed().sendMessage("25.1."+myRoom.getMyLoc(this));
						
						myRoom.getWhite().sendMessage("25.2."+myRoom.getMyLoc(this)+"."+player);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if(this==myRoom.getWhite()) {//我是白色
				if(player.equals("black")) {
					try {
						myRoom.getBlack().sendMessage("25.1."+myRoom.getMyLoc(this));
						
						myRoom.getRed().sendMessage("25.2."+myRoom.getMyLoc(this)+"."+player);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						myRoom.getRed().sendMessage("25.1."+myRoom.getMyLoc(this));
						
						myRoom.getBlack().sendMessage("25.2."+myRoom.getMyLoc(this)+"."+player);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {//我是红色
				if(player.equals("black")) {
					try {
						myRoom.getBlack().sendMessage("25.1."+myRoom.getMyLoc(this));
						
						myRoom.getWhite().sendMessage("25.2."+myRoom.getMyLoc(this)+"."+player);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						myRoom.getWhite().sendMessage("25.1."+myRoom.getMyLoc(this));
						
						myRoom.getBlack().sendMessage("25.2."+myRoom.getMyLoc(this)+"."+player);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			myRoom.exchangeLocation(this, player);
		} else {
			if(player.equals("black")) {
				try {
					myRoom.getBlack().sendMessage("25.0."+myRoom.getMyLoc(this));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(player.equals("white")) {
				try {
					myRoom.getWhite().sendMessage("25.0."+myRoom.getMyLoc(this));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					myRoom.getRed().sendMessage("25.0."+myRoom.getMyLoc(this));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//26
	public void beginGy4() {
		ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
		myRoom.beginGame(this);
	}
	//27
	public void drop4(String message) {
		ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
		myRoom.noticeDrop(this, message);
	}
	//28
	public void sendInnerMsg3(String message) {
		ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
		myRoom.transmitMsg(this, message);
	}
	//29
	public void sendEmoticon3(String message) {
		ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
		myRoom.transmitMsg(this, message);
	}
	//30
	public void sendVoice3(String message) {
		ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
		myRoom.transmitMsg(this, message);
	}
	//32
	public void enterMatch5() {
		match5.add(this);
		System.out.println(this.xf+"已放入匹配队列");
		if(match5System == false) {
			System.out.println("匹配系统已关闭，重新开启中....");
			match5System = true;
			match5System();
		} else {
			System.out.println("匹配系统已经在运行中，清等待....");
		}
	}
	
	//33
	public void match5Confirm(String msg) {
		TwoPeopleRoom myRoom = twoRooms.get(this.roomId);
		myRoom.transmitMsg(this, msg);
	}
	
	//34
	public void destroyPp5Room() {
		if(twoRooms.contains(this.roomId)) {
			twoRooms.remove(this.roomId);
			System.out.println("匹配房间已销毁");
		}
		
	}
	
	public synchronized static void match5System() {
		System.out.println("已进入匹配系统....");
		while(match5System) {
		if(match5.size()>1) {
			System.out.println("匹配队列多于一人，匹配系统运行中...人数："+match5.size());
			System.out.println("创建房间中....");
			TwoPeopleRoom two = new TwoPeopleRoom();
			two.setBlack(match5.get(0));
			two.setWhite(match5.get(1));
			String roomId = match5.get(0).xf+match5.get(1).xf;
			match5.get(0).roomId = roomId;
			match5.get(1).roomId = roomId;
			twoRooms.put(roomId, two);
			System.out.println("创建房间完毕！");
			System.out.println("发送匹配成功消息中....");
			try {
				match5.get(0).sendMessage("32black."+match5.get(1).xf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				match5.get(1).sendMessage("32white."+match5.get(0).xf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("发送匹配成功消息完毕！");
			try {
				match5.remove(0);
				match5.remove(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("匹配成功玩家已从匹配队列移除！");
		} else {
			System.out.println("匹配队列不足2人，匹配系统关闭");
			match5System = false;
		}
		}
	}
	public synchronized static void match4System() {
		System.out.println("已进入四子棋匹配系统....");
		while(match4System) {
		if(match4.size()>2) {
			System.out.println("匹配队列满足3人，匹配系统运行中...人数："+match4.size());
			System.out.println("创建房间中....");
			ThreePeopleRoom three = new ThreePeopleRoom();
			three.setBlack(match4.get(0));
			three.setWhite(match4.get(1));
			three.setRed(match4.get(2));
			String roomId = match4.get(0).xf+match4.get(1).xf+match4.get(2);
			match4.get(0).roomId = roomId;
			match4.get(1).roomId = roomId;
			match4.get(2).roomId = roomId;
			threeRooms.put(roomId, three);
			System.out.println("创建3人房间完毕！");
			System.out.println("发送匹配成功消息中....");
			try {
				match4.get(0).sendMessage("35black."+match4.get(1).xf+"."+match4.get(2).xf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				match4.get(1).sendMessage("35white."+match4.get(0).xf+"."+match4.get(2).xf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				match4.get(2).sendMessage("35red."+match4.get(0).xf+"."+match4.get(1).xf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("发送匹配成功消息完毕！");
			try {
				match4.remove(0);
				match4.remove(0);
				match4.remove(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("匹配成功玩家已从匹配队列移除！");
		} else {
			System.out.println("匹配队列不足3人，匹配系统关闭");
			match4System = false;
		}
		}
	}
	//35
		public void enterMatch4() {
			match4.add(this);
			System.out.println(this.xf+"已放入匹配队列");
			if(match4System == false) {
				System.out.println("匹配系统已关闭，重新开启中....");
				match4System = true;
				match4System();
			} else {
				System.out.println("匹配系统已经在运行中，清等待....");
			}
		}
		//36
		public void match4Confirm(String msg) {
			ThreePeopleRoom myRoom = threeRooms.get(this.roomId);
			myRoom.transmitMsg(this, msg);
		}
		
		//37
		public void destroyPp4Room() {
			if(twoRooms.contains(this.roomId)) {
				twoRooms.remove(this.roomId);
				System.out.println("匹配房间已销毁");
			}
			
		}
	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		addOnlineCount(); // 在线数加1
//		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		nzqGameServerTable.remove(this.xf);
		subOnlineCount();           //在线数减1    
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}
	
	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		System.out.println("发送消息>"+message);
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("发送消息出错");
			e.printStackTrace();
		}
		// this.session.getAsyncRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		NzqGameSession.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		NzqGameSession.onlineCount--;
	}
}