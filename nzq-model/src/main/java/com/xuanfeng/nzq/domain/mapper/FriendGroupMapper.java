package com.xuanfeng.nzq.domain.mapper;

import com.xuanfeng.nzq.domain.model.FriendGroup;

public interface FriendGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FriendGroup record);

    int insertSelective(FriendGroup record);

    FriendGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FriendGroup record);

    int updateByPrimaryKey(FriendGroup record);
}