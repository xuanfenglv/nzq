package com.xuanfeng.nzq.domain.dao;

import com.xuanfeng.nzq.api.request.friends.UpdateGroupRequest;
import com.xuanfeng.nzq.api.request.friends.UpdateRemarkRequest;
import com.xuanfeng.nzq.api.response.user.FriendUserInfo;
import com.xuanfeng.nzq.domain.entity.FriendInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

public interface FriendDao {

    @Select("select fxf from Friend where xf = #{xf}")
    Set<Long> selectFriendIds(Long xf);

    @Select("select fxf id,remark,nickname,im_status status,group_id from Friend f,`User` u where u.id=f.xf and u.id=#{xf}")
    List<FriendInfo> selectFriendInfos(Long xf);

    @Delete("delete from Friend where xf=#{xf} and fxf=#{fxf}")
    int deleteFriend(Long fxf, Long xf);

    @Select("select nickname,sex,birthday,tel,grade from `User` where id=#{xf}")
    FriendUserInfo selectFriendInfo(Long xf);

    @Update("update Friend set remark=#{remark} where xf = #{xf} and fxf=#{fxf}")
    int updateRemark(UpdateRemarkRequest request);

    @Update("update Friend set group_id=#{groupId} where xf = #{xf} and fxf=#{fxf}")
    int updateGroup(UpdateGroupRequest request);
}
