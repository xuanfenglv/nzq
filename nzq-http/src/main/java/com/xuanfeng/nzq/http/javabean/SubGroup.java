package com.xuanfeng.nzq.http.javabean;

import java.util.Date;

public class SubGroup {
	private int sno;
	private String sname;
	private Date sdate;
	private int xf;
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
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public int getXf() {
		return xf;
	}
	public void setXf(int xf) {
		this.xf = xf;
	}
	public SubGroup(int sno, String sname, Date sdate, int xf) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.sdate = sdate;
		this.xf = xf;
	}
	public SubGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

}
