package com.xuanfeng.nzq.http.controller;

import com.xuanfeng.nzq.api.controller.UserBaseController;
import com.xuanfeng.nzq.api.request.user.LoginRequest;
import com.xuanfeng.nzq.api.request.user.RegisterUserRequest;
import com.xuanfeng.nzq.api.response.user.OtherUserInfo;
import com.xuanfeng.nzq.api.response.user.SelfUserInfo;
import com.xuanfeng.nzq.commons.Result;
import com.xuanfeng.nzq.commons.ResultUtil;
import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 用户controller
 * @author: lvxianqing
 * @create: 2018/11/22 14:08
 */
@RestController
public class UserController implements UserBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService service;

    public Result<Long> register(@RequestBody @Validated RegisterUserRequest request) {
        logger.info("Enter method register,request:{}.", request);
        Long xf = service.registerUser(request);
        logger.info("End method register.");
        return ResultUtil.createSuccessResult(xf);
    }

    public Result login(@RequestBody @Validated LoginRequest request) {
        logger.info("Enter method login,request:{}.", request);
        boolean isSuccess = service.login(request.getXf(), request.getPassword());
        logger.info("End method login.");
        if (isSuccess) {
            return ResultUtil.createSuccessResult(request.getXf());
        } else {
            return ResultUtil.createFailedResult(RetEnum.LOGIN_FAIL, "用户名或密码错误");
        }
    }

    @Override
    public Result<SelfUserInfo> querySelfUserInfo(Long xf) {
        return null;
    }

    @Override
    public Result<OtherUserInfo> searchOtherUser(Long xf) {
        return null;
    }

    @Override
    public Result<List<OtherUserInfo>> searchOtherUsers(String nickname, Byte sex, Byte grade, Long lastXf) {
        return null;
    }

    @Override
    public Result updateSelfUserInfo(SelfUserInfo request) {
        return null;
    }
}
