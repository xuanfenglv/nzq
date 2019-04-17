package com.xuanfeng.nzq.domain.dao;

import com.xuanfeng.nzq.api.response.app.ShortApplication;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    // 查询与某人相关的好友申请
    @Select("select app.*,(select nickname from User where id = app.send_xf) sendNickname,(select nickname from User where id = app.receive_xf) receiveNickname from Application app where (send_xf=#{xf} and send_visible=1) or (receive_xf=#{xf} and receive_visible=1)")
    List<ShortApplication> selectByXf(Long xf);
}
