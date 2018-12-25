package com.xuanfeng.nzq.api.request.base;

import com.xuanfeng.nzq.commons.javabean.UserSessionInfo;
import com.xuanfeng.nzq.commons.utils.SessionUtil;

/**
 * @description: 带有我id的请求
 * @author: lvxianqing
 * @create: 2018/12/16 16:47
 */

public class WithMyXfRequest {
    private Long xf;

    {

        try {
            UserSessionInfo info = SessionUtil.getUserSessionInfo();
            setXf(info.getXf());
        } catch (Exception e) {
            System.out.println(123);
        }
    }
    public Long getXf() {
        return xf;
    }

    public void setXf(Long xf) {
        this.xf = xf;
    }
}
