package com.xuanfeng.nzq.domain.dao;

import com.xuanfeng.nzq.api.response.user.OtherUserInfo;
import com.xuanfeng.nzq.api.response.user.SelfUserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/10/10 23:21
 */

public interface UserDao {

    @Select("select nickname,sex,birthday,tel,grade,money from `User` where id=#{xf}")
    SelfUserInfo querySelfUserInfo(Long xf);

    @Select("select id,nickname,sex,birthday,grade from `User` where id=#{xf}")
    OtherUserInfo searchOtherUser(Long xf);

    // TODO: 2018/11/23
    @Select("<script>select id,nickname,sex,birthday,grade from `User` " +
            "<where> " +
            "<if test=\"nickname != null\">" +
            "   <bind name=\"pattern\" value=\"'%' + nickname + '%'\" />" +
            "   and nickname like #{pattern}" +
            "</if>" +
            "<if test=\"sex != null\">" +
            "   and sex = #{sex}" +
            "</if>" +
            "<if test=\"grade != null\">" +
            "   and grade = #{grade}" +
            "</if>" +
            " limit #{offSet},#{pageNum}" +
            "</where>" +
            "</script>")
    List<OtherUserInfo> searchOtherUsers(@Param("nickname") String nickname, @Param("sex") Byte sex, @Param("grade") Byte grade, @Param("offSet") Integer offSet,@Param("pageNum") Integer pageNum);

    @Update("update `User` set im_status = #{status} where id = #{xf}")
    int changeImStatus(@Param("xf") Long xf, @Param("status") Byte status);

    @Update("update `User` set nzq_status = #{status} where id = #{xf}")
    int changeNzqStatus(@Param("xf") Long xf, @Param("status") Byte status);

    @Select("select id from `User` where id = #{xf} and pwd = #{pwd}")
    Long selectIdByIdAndPwd(@Param("xf") Long xf, @Param("pwd") String pwd);

    @Select("select nickname from `User` where id=#{xf}")
    String queryNickname(@Param("xf") Long xf);
}
