package com.xuanfeng.nzq.domain.mapper;

import com.xuanfeng.nzq.domain.model.Application;

public interface ApplicationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);
}