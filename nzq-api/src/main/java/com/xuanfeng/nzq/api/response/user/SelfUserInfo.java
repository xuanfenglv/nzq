package com.xuanfeng.nzq.api.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 自己的用户信息
 * @author: lvxianqing
 * @create: 2018/11/22 19:54
 */
@Data
public class SelfUserInfo {
    private Long id;
    private String nickname;
    private Byte sex;
    private int age;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date birthday;
    private String tel;
    private Byte grade;
    private Long money;
}

