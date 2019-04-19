package com.xuanfeng.nzq.domain.dao;

import com.xuanfeng.nzq.api.response.app.ShortApplication;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/10/10 23:23
 */

public interface ApplicationDao {

    @Select("select id from Application where send_xf=#{sendXf} and receive_xf=#{receiveXf}")
    Long selectApplicationId(@Param("sendXf") Long sendXf, @Param("receiveXf") Long receiveXf);

    @Update("update Application set status=#{status} where id=#{applicatinId}")
    int changStatus(@Param("applicatinId") Long applicatinId, @Param("status") Byte status);

    // 查询与某人相关的好友申请(按照时间降序排列)
    @Select("select app.*,(select nickname from User where id = app.send_xf) sendNickname,(select nickname from User where id = app.receive_xf) receiveNickname " +
            "from Application app where (send_xf=#{xf} and send_visible=1) or (receive_xf=#{xf} and receive_visible=1)  " +
            "order by mtime desc")
    List<ShortApplication> selectByXf(Long xf);
}
