package com.xuanfeng.nzq.http.controller;

import com.xuanfeng.nzq.api.controller.FriendBaseController;
import com.xuanfeng.nzq.api.request.friends.UpdateGroupRequest;
import com.xuanfeng.nzq.api.request.friends.UpdateRemarkRequest;
import com.xuanfeng.nzq.api.response.user.FriendUserInfo;
import com.xuanfeng.nzq.commons.Result;
import com.xuanfeng.nzq.commons.ResultUtil;
import com.xuanfeng.nzq.service.FriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 好友controller
 * @author: lvxianqing
 * @create: 2018/12/15 17:23
 */
@RestController
public class FriendController implements FriendBaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FriendService friendService;

    @Override
    public Result<FriendUserInfo> getFriendUserInfo(@PathVariable("id") Long id) {
        logger.info("Enter method getFriendUserInfo,id:{}.", id);
        FriendUserInfo friendUserInfo = friendService.queryFriendInfo(id);
        logger.info("End method getFriendUserInfo.");
        return ResultUtil.createSuccessResult(friendUserInfo);
    }

    @Override
    public Result updateRemark(@RequestBody UpdateRemarkRequest request) {
        logger.info("Enter method updateRemark,request:{}.", request);
        friendService.updateRemark(request);
        logger.info("End method updateRemark.");
        return ResultUtil.createSuccessResult();
    }

    @Override
    public Result updateGroup(UpdateGroupRequest request) {
        logger.info("Enter method updateGroup,request:{}.", request);
        friendService.updateGroup(request);
        logger.info("End method updateGroup.");
        return ResultUtil.createSuccessResult();
    }

}
