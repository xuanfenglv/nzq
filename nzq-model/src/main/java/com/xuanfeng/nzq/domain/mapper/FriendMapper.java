package com.xuanfeng.nzq.domain.mapper;

import com.xuanfeng.nzq.domain.model.Friend;

public interface FriendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}