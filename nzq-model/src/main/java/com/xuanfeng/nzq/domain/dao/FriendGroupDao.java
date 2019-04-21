package com.xuanfeng.nzq.domain.dao;

import com.xuanfeng.nzq.domain.entity.GroupInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/10/10 23:22
 */

public interface FriendGroupDao {
    @Select("select id,name from FriendGroup where xf=#{xf}")
    List<GroupInfo> selectGroupInfos(Long xf);

    @Select("select min(id) from FriendGroup where xf=#{xf}")
    Long selectDefaultGroupId(Long xf);
}
