package com.xuanfeng.nzq.http.javabean;

public class PersonGroup {

	private int sno;
	private String sname;
	private int online;
	private int total;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public PersonGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PersonGroup(int sno, String sname, int online, int total) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.online = online;
		this.total = total;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
