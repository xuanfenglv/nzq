package com.xuanfeng.nzq.domain.mapper;

import com.xuanfeng.nzq.domain.model.ChatInfo;

public interface ChatInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChatInfo record);

    int insertSelective(ChatInfo record);

    ChatInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChatInfo record);

    int updateByPrimaryKey(ChatInfo record);
}