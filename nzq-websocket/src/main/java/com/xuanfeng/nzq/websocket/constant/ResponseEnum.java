package com.xuanfeng.nzq.websocket.constant;

public enum ResponseEnum {
    OK(1, "正确"),
    登录过期(2, "未登录或登录过期"),
    参数错误(3, "参数不正确"),
    权限不足(4, "权限不足"),
    需要备注(5, "需要备注"),
    已被封号(6,"已经被封号"),
    登录失败(7, "登录失败"),
    服务器故障(8, "服务器故障"),
    非法请求(9, "非法请求"),
    其他错误(10, "未知错误");

    private ResponseEnum(int ret, String tip) {
        this.ret = ret;
        this.tip = tip;
    }

    private final int ret;
    private final String tip;

    public String getTip() {
        return tip;
    }

    public int getRet() {
        return ret;
    }
}
