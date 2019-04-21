package com.xuanfeng.nzq.commons;

import com.alibaba.fastjson.JSON;

/**
 * @description: Result工具类
 * @author: lvxianqing
 * @create: 2018/11/22 14:26
 */

public class ResultUtil {
    private static final Result result= new Result();

    public static final String NO_LOGIN = JSON.toJSONString(createFailedResult(RetEnum.NOTLOGIN, "未登录"));

    public static Result createSuccessResult() {
        return result;
    }

    public static Result createSuccessResult(Object data) {
        return new Result(data);
    }

    public static Result createFailedResult(RetEnum retEnum, String errorDatil) {
        return new Result(retEnum, errorDatil);
    }


}
