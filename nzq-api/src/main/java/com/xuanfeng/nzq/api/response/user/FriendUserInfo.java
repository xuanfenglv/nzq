package com.xuanfeng.nzq.api.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 好友用户信息
 * @author: lvxianqing
 * @create: 2018/11/29 20:22
 */

public class FriendUserInfo {
    private String nickname;
    private Byte sex;
    private int age;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date birthday;
    private Long tel;
    private Byte grade;
//    private Long money;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

//    public Long getMoney() {
//        return money;
//    }
//
//    public void setMoney(Long money) {
//        this.money = money;
//    }
}
