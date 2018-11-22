package com.xuanfeng.nzq.http.javabean;

import java.util.Date;

public class UserInfo {

	private int xf;
	private String remark;
	public UserInfo(String remark, String nickname, String sex, String grade) {
		super();
		this.remark = remark;
		this.nickname = nickname;
		this.sex = sex;
		this.grade = grade;
	}
	private String pwd;
	private int photoID;
	private String nickname;
	private String sex;
	private int isFriend;
	public UserInfo(int xf, String nickname, String sex, String grade, int age) {
		super();
		this.xf = xf;
		this.nickname = nickname;
		this.sex = sex;
		this.grade = grade;
		this.age = age;
	}

	public UserInfo(String nickname, String sex, String s_birthday,
			String telephone, int money, String grade, int age) {
		super();
		this.nickname = nickname;
		this.sex = sex;
		this.s_birthday = s_birthday;
		this.telephone = telephone;
		this.money = money;
		this.grade = grade;
		this.age = age;
	}

	public UserInfo(int xf, String nickname, String sex, String grade, int age, int isFriend) {
		super();
		this.xf = xf;
		this.nickname = nickname;
		this.sex = sex;
		this.grade = grade;
		this.age = age;
		this.isFriend = isFriend;
	}
	private Date birthday;
	private String s_birthday;
	private String telephone;
	private Date jointime;
	private int money;
	private String grade;
	private int age;
	public UserInfo(String pwd, String nickname) {
		super();
		this.pwd = pwd;
		this.nickname = nickname;
	}
	
	public UserInfo(String pwd, String nickname, String sex, Date birthday,
			String telephone, Date jointime, int money) {
		super();
		this.pwd = pwd;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;
		this.telephone = telephone;
		this.jointime = jointime;
		this.money = money;
	}

	public int getXf() {
		return xf;
	}
	public void setXf(int xf) {
		this.xf = xf;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getPhotoID() {
		return photoID;
	}
	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getJointime() {
		return jointime;
	}
	public void setJointime(Date jointime) {
		this.jointime = jointime;
	}
	public UserInfo(int xf, String pwd, int photoID, String nickname,
			String sex, Date birthday, String telephone, Date jointime) {
		super();
		this.xf = xf;
		this.pwd = pwd;
		this.photoID = photoID;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;
		this.telephone = telephone;
		this.jointime = jointime;
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(int isFriend) {
		this.isFriend = isFriend;
	}

	public String getS_birthday() {
		return s_birthday;
	}

	public void setS_birthday(String s_birthday) {
		this.s_birthday = s_birthday;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
