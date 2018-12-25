package com.xuanfeng.nzq.domain.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/10/10 23:23
 */

public interface ApplicationDao {

    @Select("select id form Application where sendXf=#{sendXf} and receiveXf=#{receiveXf}")
    Long selectApplicationId(Long sendXf, Long receiveXf);

    @Update("update Application set status=#{status} where id=#{applicatinId}")
    int changStatus(Long applicatinId, Byte status);
}
