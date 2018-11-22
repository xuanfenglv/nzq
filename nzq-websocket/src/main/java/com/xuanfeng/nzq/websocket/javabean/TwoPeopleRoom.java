package com.xuanfeng.nzq.websocket.javabean;

import com.xuanfeng.nzq.websocket.session.NzqGameSession;

import java.io.IOException;


public class TwoPeopleRoom {
	private String fangzhu;
	private NzqGameSession black = null;
	private NzqGameSession white = null;
	public NzqGameSession getFangzhuPlayer() {
		if(fangzhu.equals("black")) {
			System.out.println("房主是黑");
			return this.black;
		} else {
			System.out.println("房主是白");
			return this.white;
		} 
	}
	public NzqGameSession getBlack() {
		return black;
	}
	public void setBlack(NzqGameSession black) {
		this.black = black;
	}
	public NzqGameSession getWhite() {
		return white;
	}
	public void setWhite(NzqGameSession white) {
		this.white = white;
	}
	public void transmitMsg(NzqGameSession player, String msg) {
		System.out.println("转发消息是"+msg);
		if(player==this.black) {
			try {
				this.white.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.black.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void exchangeLocation() {
		if(this.fangzhu.equals("black")) {
			this.fangzhu = "white";
		} else {
			this.fangzhu = "black";
		}
		NzqGameSession agency = this.black;
		this.black = this.white;
		this.white = agency;
	}
	public boolean isFull() {
		if(black!=null&&white!=null) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isEmpty() {
		if(black==null&&white==null) {
			return true;
		} else {
			return false;
		}
	}
	public void tickOut() {
		if(fangzhu.equals("black")) {
			white.setRoomId(null);
			try {
				white.sendMessage("08");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setWhite(null);
		} else {
			black.setRoomId(null);
			try {
				black.sendMessage("08");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setBlack(null);
		}
	}
	public void sendRequestChangeLocation(NzqGameSession player) {
		if(player==this.black) {
			try {
				this.white.sendMessage("09");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.black.sendMessage("09");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void changeLocationBetweenTwoPlayer(NzqGameSession player) {
		if(player==this.black) {
			try {
				this.white.sendMessage("10.1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.black.sendMessage("10.1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		exchangeLocation();
	}
	public void refuseChangeLocationBetweenTwoPlayer(NzqGameSession player) {
		if(player==this.black) {
			try {
				this.white.sendMessage("10.0");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.black.sendMessage("10.0");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void beginGame(NzqGameSession player) {
		if(player==this.black) {
			try {
				this.white.sendMessage("11");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.black.sendMessage("11");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void noticeDrop(NzqGameSession player, String msg) {
		if(player==this.black) {
			try {
				this.white.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.black.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getFangzhu() {
		return fangzhu;
	}
	public void setFangzhu(String fangzhu) {
		this.fangzhu = fangzhu;
	}
}
