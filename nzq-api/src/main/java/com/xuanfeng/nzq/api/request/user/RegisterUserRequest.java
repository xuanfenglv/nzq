package com.xuanfeng.nzq.api.request.user;

import com.xuanfeng.nzq.api.Mobile;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @description: 注册用户请求
 * @author: lvxianqing
 * @create: 2018/11/22 12:39
 */

public class RegisterUserRequest {
    @Length(min = 1, max = 20, message = "param 'nickname' 长度范围为[1,20]")
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    @Length(min = 1, max = 20, message = "param 'password' 长度范围为[8,20]")
    @NotBlank(message = "昵称不能为空")
    private String password;
    @Range(min = 0, max = 1, message = "param 'sex' 取值为0、1")
    @NotNull(message = "param 'sex' 不能为空")
    private Byte sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "param 'birthday' 必须为过去的时间点")
    @NotNull(message = "param 'birthday' 不能为空")
    private Date birthday;
    @Mobile(message = "param 'tel' 手机格式错误")
    private String tel;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public Byte getSex() {
        return sex;
    }

    public void setSex(@NotNull Byte sex) {
        this.sex = sex;
    }

    @NotNull
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(@NotNull Date birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
