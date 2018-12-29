package com.xuanfeng.nzq.domain.model;

import java.util.Date;

public class User {
    private Long id;

    private String nickname;

    private String pwd;

    private Byte sex;

    private Date birthday;

    private String tel;

    private Byte grade;

    private Long money;

    private Byte imStatus;

    private Byte nzqStatus;

    private Date ctime;

    private Date mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Byte getImStatus() {
        return imStatus;
    }

    public void setImStatus(Byte imStatus) {
        this.imStatus = imStatus;
    }

    public Byte getNzqStatus() {
        return nzqStatus;
    }

    public void setNzqStatus(Byte nzqStatus) {
        this.nzqStatus = nzqStatus;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}