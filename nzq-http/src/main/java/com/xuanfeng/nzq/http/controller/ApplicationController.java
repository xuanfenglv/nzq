package com.xuanfeng.nzq.http.controller;

import com.xuanfeng.nzq.api.controller.ApplicationBaseController;
import com.xuanfeng.nzq.api.response.app.ShortApplication;
import com.xuanfeng.nzq.commons.Result;
import com.xuanfeng.nzq.commons.ResultUtil;
import com.xuanfeng.nzq.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 好友申请controller
 * @author: lvxianqing
 * @create: 2019/04/17 14:11
 */
@RestController
public class ApplicationController implements ApplicationBaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationService service;

    @Override
    public Result<List<ShortApplication>> selectByXf(@PathVariable("xf") Long xf) {
        logger.info("enter selectByXf,xf:{}",xf);
        List<ShortApplication> applicationList = service.selectByXf(xf);
        logger.info("end selectByXf.");
        return ResultUtil.createSuccessResult(applicationList);
    }
}
