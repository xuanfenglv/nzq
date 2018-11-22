package com.xuanfeng.nzq.api.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @description: 其他人用户信息
 * @author: lvxianqing
 * @create: 2018/11/22 20:14
 */

public class OtherUserInfo {
    private Long id;
    private String nickname;
    private Byte sex;
    private Byte grade;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

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
        this.nickname = nickname;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
