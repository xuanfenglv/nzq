package com.xuanfeng.nzq.http.javabean;

public class Application {
	private int send_xf;
	private int receive_xf;
	public Application(int send_xf, String nickname, int conditions, String text) {
		super();
		this.send_xf = send_xf;
		this.nickname = nickname;
		this.conditions = conditions;
		this.text = text;
	}
	public Application(int receive_xf, String nickname, int conditions) {
		super();
		this.receive_xf = receive_xf;
		this.nickname = nickname;
		this.conditions = conditions;
	}
	private int send_sno;
	private String send_remark;
	private String nickname;
	private int conditions;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getConditions() {
		return conditions;
	}
	public void setConditions(int conditions) {
		this.conditions = conditions;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private String text;
	public Application(int send_sno, String send_remark) {
		super();
		this.send_sno = send_sno;
		this.send_remark = send_remark;
	}
	public Application(String nickname, int conditions) {
		super();
		this.nickname = nickname;
		this.conditions = conditions;
	}
	public Application(String nickname, String text) {
		super();
		this.nickname = nickname;
		this.text = text;
	}
	public int getSend_sno() {
		return send_sno;
	}
	public void setSend_sno(int send_sno) {
		this.send_sno = send_sno;
	}
	public String getSend_remark() {
		return send_remark;
	}
	public void setSend_remark(String send_remark) {
		this.send_remark = send_remark;
	}
	public int getSend_xf() {
		return send_xf;
	}
	public void setSend_xf(int send_xf) {
		this.send_xf = send_xf;
	}
	public int getReceive_xf() {
		return receive_xf;
	}
	public void setReceive_xf(int receive_xf) {
		this.receive_xf = receive_xf;
	}
}
