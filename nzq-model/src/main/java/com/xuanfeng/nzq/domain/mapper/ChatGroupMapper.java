package com.xuanfeng.nzq.domain.mapper;

import com.xuanfeng.nzq.domain.model.ChatGroup;

public interface ChatGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChatGroup record);

    int insertSelective(ChatGroup record);

    ChatGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChatGroup record);

    int updateByPrimaryKey(ChatGroup record);
}