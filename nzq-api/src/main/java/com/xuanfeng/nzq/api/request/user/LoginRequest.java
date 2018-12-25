package com.xuanfeng.nzq.api.request.user;

import javax.validation.constraints.NotNull;

/**
 * @description: 登录请求
 * @author: lvxianqing
 * @create: 2018/11/22 13:38
 */

public class LoginRequest {
    @NotNull(message = "param 'xf' 不能为空")
    private Long xf;
    @NotNull(message = "param 'pwd' 不能为空")
    private String pwd;

    @NotNull
    public Long getXf() {
        return xf;
    }

    public void setXf(@NotNull Long xf) {
        this.xf = xf;
    }

    @NotNull
    public String getPwd() {
        return pwd;
    }

    public void setPwd(@NotNull String pwd) {
        this.pwd = pwd;
    }
}
