package com.xuanfeng.nzq.http.controller;

import com.xuanfeng.nzq.api.controller.UserBaseController;
import com.xuanfeng.nzq.api.request.user.LoginRequest;
import com.xuanfeng.nzq.api.request.user.RegisterUserRequest;
import com.xuanfeng.nzq.api.request.user.UpdateSelfInfoRequest;
import com.xuanfeng.nzq.api.response.user.OtherUserInfo;
import com.xuanfeng.nzq.api.response.user.SelfUserInfo;
import com.xuanfeng.nzq.commons.Result;
import com.xuanfeng.nzq.commons.ResultUtil;
import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.commons.javabean.UserSessionInfo;
import com.xuanfeng.nzq.commons.utils.SessionUtil;
import com.xuanfeng.nzq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description: 用户controller
 * @author: lvxianqing
 * @create: 2018/11/22 14:08
 */
@RestController
public class UserController implements UserBaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

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
        boolean isSuccess = service.login(request.getXf(), request.getPwd());
        logger.info("End method login.");
        if (isSuccess) {
            // 设置session
            HttpSession session = SessionUtil.getSession(true);
            UserSessionInfo sessionInfo = new UserSessionInfo();
            sessionInfo.setXf(request.getXf());
            session.setAttribute("user",sessionInfo);

            return ResultUtil.createSuccessResult();


        } else {
            return ResultUtil.createFailedResult(RetEnum.LOGIN_FAIL, "用户名或密码错误");
        }
    }

    @Override
    public Result<SelfUserInfo> querySelfUserInfo() {
        logger.info("Enter method querySelfUserInfo.");
        UserSessionInfo info =SessionUtil.getUserSessionInfo();
        SelfUserInfo selfUserInfo = service.querySelfUserInfo(info.getXf());
        logger.info("End method querySelfUserInfo.");
        return ResultUtil.createSuccessResult(selfUserInfo);
    }

    @Override
    public Result updateSelfUserInfo(@RequestBody UpdateSelfInfoRequest request) {
        logger.info("Enter method updateSelfUserInfo,request:{}.", request);
        service.updateSelfUserInfo(request,SessionUtil.getUserSessionInfo().getXf());
        logger.info("End method updateSelfUserInfo.");
        return ResultUtil.createSuccessResult();
    }

    @Override
    public Result<OtherUserInfo> searchOtherUser(@PathVariable Long xf) {
        logger.info("Enter method searchOtherUser,xf:{}.", xf);
        OtherUserInfo otherUserInfo = service.searchOtherUser(xf);
        logger.info("End method searchOtherUser.");
        return ResultUtil.createSuccessResult(otherUserInfo);
    }

    @Override
    public Result<List<OtherUserInfo>> searchOtherUsers(String nickname, Byte sex, Byte grade,Integer pageSize, Integer pageNum) {
        logger.info("Enter method searchOtherUsers,nickname:{},sex:{},grade:{},lastXf:{}.",nickname, sex,grade,pageSize,pageNum);
        List<OtherUserInfo> otherUserInfos = service.searchOtherUsers(nickname,sex,grade,pageSize,pageNum);
        logger.info("End method searchOtherUsers.");
        return ResultUtil.createSuccessResult(otherUserInfos);
    }
}
