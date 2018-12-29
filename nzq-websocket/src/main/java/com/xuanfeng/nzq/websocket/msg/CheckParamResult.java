package com.xuanfeng.nzq.websocket.msg;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:45 2018/4/18
 * @Modified By:
 */
public class CheckParamResult {
    private boolean valid = true;
    private String errmsg;

    public void setErrMsg(String errmsg) {
        this.valid = false;
        this.errmsg = errmsg;
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrmsg() {
        return errmsg;
    }

}
