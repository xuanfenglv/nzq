package com.xuanfeng.nzq.domain.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/10/10 23:21
 */

public interface UserDao {

    @Update("update `user` set status = #{status} where id = #{xf}")
    int changeStatus(@Param("xf") Long xf, @Param("status") Byte status);

    @Select("select id from where id = #{xf} and pwd = #{pwd}")
    Long selectIdByIdAndPwd(@Param("xf") Long xf, @Param("pwd") String pwd);
}
