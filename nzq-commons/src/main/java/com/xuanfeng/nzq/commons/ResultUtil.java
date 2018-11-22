package com.xuanfeng.nzq.commons;

/**
 * @description: Result工具类
 * @author: lvxianqing
 * @create: 2018/11/22 14:26
 */

public class ResultUtil {
    private static Result result= new Result();

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
