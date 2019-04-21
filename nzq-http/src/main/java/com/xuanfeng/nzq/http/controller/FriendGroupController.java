package com.xuanfeng.nzq.http.controller;

import com.xuanfeng.nzq.api.controller.FriendGroupBaseController;
import com.xuanfeng.nzq.api.request.group.AddGroupReq;
import com.xuanfeng.nzq.api.request.group.UpdateGroupReq;
import com.xuanfeng.nzq.commons.Result;
import com.xuanfeng.nzq.service.FriendGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 好友分组controller
 * @author: lvxianqing
 * @create: 2019/04/21 14:41
 */

@RestController
public class FriendGroupController implements FriendGroupBaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FriendGroupService service;

    @Override
    public Result add(@RequestBody AddGroupReq req) {
        logger.info("enter method add req:{}",req);
        Result result = service.add(req);
        logger.info("exist method add result:{}",result);
        return result;
    }

    @Override
    public Result update(@RequestBody UpdateGroupReq req) {
        logger.info("enter method update req:{}",req);
        Result result = service.update(req);
        logger.info("exist method update result:{}",result);
        return result;
    }

    @Override
    public Result delete(@PathVariable Long id) {
        logger.info("enter method delete id:{}",id);
        Result result = service.delete(id);
        logger.info("exist method delete result:{}",result);
        return result;
    }
}
