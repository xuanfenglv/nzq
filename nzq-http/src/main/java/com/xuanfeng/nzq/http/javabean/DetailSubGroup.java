package com.xuanfeng.nzq.http.javabean;

public class DetailSubGroup {

	private int fsno;
	private int fxf;
	private String remark;
	private String nickname;
	private int fstatus;
	private String grade;
	public DetailSubGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailSubGroup(int fsno, int fxf, String remark, String nickname,
			int fstatus) {
		super();
		this.fsno = fsno;
		this.fxf = fxf;
		this.remark = remark;
		this.nickname = nickname;
		this.fstatus = fstatus;
	}
	public DetailSubGroup(int fxf, String remark, String nickname,
			int fstatus, String grade) {
		super();
		this.fxf = fxf;
		this.remark = remark;
		this.nickname = nickname;
		this.fstatus = fstatus;
		this.grade = grade;
	}
	public int getFsno() {
		return fsno;
	}
	public void setFsno(int fsno) {
		this.fsno = fsno;
	}
	public int getFxf() {
		return fxf;
	}
	public void setFxf(int fxf) {
		this.fxf = fxf;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getFstatus() {
		return fstatus;
	}
	public void setFstatus(int fstatus) {
		this.fstatus = fstatus;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}
