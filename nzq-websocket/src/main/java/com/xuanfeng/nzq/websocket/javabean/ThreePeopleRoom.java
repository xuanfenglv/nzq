package com.xuanfeng.nzq.websocket.javabean;

import com.xuanfeng.nzq.websocket.session.NzqGameSession;

import java.io.IOException;


public class ThreePeopleRoom {
	private String fangzhu;
	private NzqGameSession black = null;
	private NzqGameSession white = null;
	private NzqGameSession red = null;
	
	public String getMyLoc(NzqGameSession player) {
		if(player == black) {
			return "black";
		} else if(player == white) {
			return "white";
		} else {
			return "red";
		}
	}
	public void fangzhuJicheng() {
		if(black!=null) {
			setFangzhu("black");
		} else if(white!=null) {
			setFangzhu("white");
		} else {
			setFangzhu("red");
		}
	}
	public NzqGameSession getFangzhuPlayer() {
		if(fangzhu.equals("black")) {
			return this.black;
		} else if(fangzhu.equals("white")) {
			return this.white;
		} else {
			return this.red;
		} 
	}
	public void setFangzhuPlayerNull() {
		if(fangzhu.equals("black")) {
			this.black = null;
		} else if(fangzhu.equals("white")) {
			this.white = null;
		} else {
			this.red = null;
		} 
	}
	public String otherPlayerLocation(String color) {
		String location = fangzhu;
		if(color.equals("black")) {
			if(white!=null) {
				location += "."+this.white.getXf()+".white";
			}
			if(red!=null) {
				location += "."+this.red.getXf()+".red";
			}
		} else if(color.equals("white")) {
			if(black!=null) {
				location += "."+this.black.getXf()+".black";
			}
			if(red!=null) {
				location += "."+this.red.getXf()+".red";
			}
		} else if(color.equals("red")) {
			if(black!=null) {
				location += "."+this.black.getXf()+".black";
			}
			if(white!=null) {
				location += "."+this.white.getXf()+".white";
			}
		}
		return location;
	}
	public void sendMsgFrom(String color,String msg) {
		if(color.equals("black")) {
			try {
				if(white!=null) {
					white.sendMessage(msg);
				}
				if(red!=null) {
					red.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(color.equals("white")) {
			try {
				if(black!=null) {
					black.sendMessage(msg);
				}
				if(red!=null) {
					red.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(color.equals("red")) {
			try {
				if(black!=null) {
					black.sendMessage(msg);
				}
				if(white!=null) {
					white.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void sendMsgFrom(NzqGameSession player, String msg) {
		if(player == black) {
			try {
				if(white!=null) {
					white.sendMessage(msg);
				}
				if(red!=null) {
					red.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(player == white) {
			try {
				if(black!=null) {
					black.sendMessage(msg);
				}
				if(red!=null) {
					red.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(player == red) {
			try {
				if(black!=null) {
					black.sendMessage(msg);
				}
				if(white!=null) {
					white.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//=========================================================================
	public void transmitMsg(NzqGameSession player, String msg) {
		System.out.println("转发消息是"+msg);
		if(player==this.black) {
			try {
				this.white.sendMessage(msg);
				this.red.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(player==this.white) {
			try {
				this.black.sendMessage(msg);
				this.red.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.black.sendMessage(msg);
				this.white.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void exchangeLocation(NzqGameSession my, String color2) {
		if(my == black) {
			if(color2.equals("white")) {
				setBlack(getWhite());
				setWhite(my);
			} else {
				setBlack(getRed());
				setRed(my);
			}
		} else if(my == white) {
			if(color2.equals("black")) {
				setWhite(getBlack());
				setBlack(my);
			} else {
				setWhite(getRed());
				setRed(my);
			}
		} else {
			if(color2.equals("black")) {
				setRed(getBlack());
				setBlack(my);
			} else {
				setRed(getWhite());
				setWhite(my);
			}
		}
	}
	public boolean isFull() {
		if(black!=null&&white!=null&&red!=null) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isEmpty() {
		if(black==null&&white==null&&red==null) {
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
	public void beginGame(NzqGameSession player) {
		if(player==this.black) {
			try {
				this.white.sendMessage("26");
				this.red.sendMessage("26");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(player==this.white) {
			try {
				this.black.sendMessage("26");
				this.red.sendMessage("26");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.black.sendMessage("26");
				this.white.sendMessage("26");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void noticeDrop(NzqGameSession player, String msg) {
		System.out.println("in noticeDrop");
		if(player==this.black) {
			System.out.println("i am black");
			try {
				System.out.println("send msg to white");
				this.white.sendMessage(msg);
				System.out.println("send msg to black");
				this.red.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(player==this.white) {
			try {
				this.black.sendMessage(msg);
				this.red.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.black.sendMessage(msg);
				this.white.sendMessage(msg);
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
	public NzqGameSession getRed() {
		return red;
	}
	public void setRed(NzqGameSession red) {
		this.red = red;
	}
}
