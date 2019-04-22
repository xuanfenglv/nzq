package com.xuanfeng.nzq.service;

import com.alibaba.fastjson.JSON;
import com.xuanfeng.nzq.api.response.app.ShortApplication;
import com.xuanfeng.nzq.commons.Result;
import com.xuanfeng.nzq.commons.ResultUtil;
import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.commons.utils.SessionUtil;
import com.xuanfeng.nzq.domain.dao.ApplicationDao;
import com.xuanfeng.nzq.domain.mapper.ApplicationMapper;
import com.xuanfeng.nzq.domain.model.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 好友申请service
 * @author: lvxianqing
 * @create: 2018/11/23 12:09
 */
@Service
public class ApplicationService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationMapper mapper;
    @Autowired
    private ApplicationDao dao;

    public Long createOrUpdate(Application application) {
        Long appId = dao.selectApplicationId(application.getSendXf(), application.getReceiveXf());

        if (appId == null) {
            mapper.insertSelective(application);
        } else {
            application.setId(appId);
            mapper.updateByPrimaryKey(application);
        }
        return application.getId();
    }

    public List<ShortApplication> selectByXf(Long xf) {
        return dao.selectByXf(xf);
    }

    public Result delete(Long appId) {
        // 获取当前用户账号
        Long xf = SessionUtil.getXf();
        logger.info("获取当前用户账号:{}", xf);
        Application application = mapper.selectByPrimaryKey(appId);
        logger.info("查询到申请:{}", JSON.toJSONString(application));
        if (application == null) {
            logger.info("不存在的申请id:" + appId);
            return ResultUtil.createFailedResult(RetEnum.非法请求, "不存在的申请id:" + appId);
        }
        if (xf.equals(application.getSendXf())) {
            logger.info("发送方删除");
            dao.senderDelete(appId);
        } else if (xf.equals(application.getReceiveXf())) {
            logger.info("请求方删除");
            dao.receiverDelete(appId);
        } else {
            logger.info("与你无关的请求id:" + appId);
            return ResultUtil.createFailedResult(RetEnum.非法请求, "与你无关的请求id:" + appId);
        }
        logger.info("删除申请成功");
        return ResultUtil.createSuccessResult();
    }
}
