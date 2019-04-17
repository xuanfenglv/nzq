package com.xuanfeng.nzq.service;

import com.xuanfeng.nzq.api.response.app.ShortApplication;
import com.xuanfeng.nzq.domain.dao.ApplicationDao;
import com.xuanfeng.nzq.domain.mapper.ApplicationMapper;
import com.xuanfeng.nzq.domain.model.Application;
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

}
