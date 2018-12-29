package com.xuanfeng.nzq.commons;

public enum RetEnum {
    CORRECT(1,"正确"),
    NOTLOGIN(2,"未登录或登录过期"),
    PARAM_ERROR(3,"参数不正确"),
    NO_PERMISSION(4, "权限不足"),
    NEED_COMMENT(5, "需要备注"),
    HAVE_FENG(6, "已被封号"),
    LOGIN_FAIL(7, "登录失败"),
    SERVER_ERROR(8, "服务器故障"),
    非法请求(9, "非法请求"),
    其他错误(10, "其他错误");

    private int ret;
    private String desc;

    private RetEnum(int ret, String desc) {
        this.ret = ret;
        this.desc = desc;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
