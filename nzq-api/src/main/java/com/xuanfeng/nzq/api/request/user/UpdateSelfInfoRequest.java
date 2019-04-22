package com.xuanfeng.nzq.api.request.user;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 修改个人信息请求
 * @author: lvxianqing
 * @create: 2018/12/07 17:31
 */
@Data
public class UpdateSelfInfoRequest {
    private String nickname;
    private Byte sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String tel;
}
