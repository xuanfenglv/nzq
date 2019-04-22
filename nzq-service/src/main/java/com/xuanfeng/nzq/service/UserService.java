package com.xuanfeng.nzq.service;

import com.xuanfeng.nzq.api.request.user.RegisterUserRequest;
import com.xuanfeng.nzq.api.request.user.UpdateSelfInfoRequest;
import com.xuanfeng.nzq.api.response.user.OtherUserInfo;
import com.xuanfeng.nzq.api.response.user.SelfUserInfo;
import com.xuanfeng.nzq.commons.utils.DateUtil;
import com.xuanfeng.nzq.domain.constant.NzqStatusEnum;
import com.xuanfeng.nzq.domain.constant.UserStatusEnum;
import com.xuanfeng.nzq.domain.dao.UserDao;
import com.xuanfeng.nzq.domain.mapper.UserMapper;
import com.xuanfeng.nzq.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/11/22 12:28
 */
@Service
public class UserService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserDao dao;

    public Long registerUser(RegisterUserRequest request) {
        User user =new User();
        BeanUtils.copyProperties(request, user);
        mapper.insertSelective(user);
        logger.info("[DB] insert into User, user:{}", user);
        return user.getId();
    }
    public boolean login(Long xf,String pwd) {

        Long id = dao.selectIdByIdAndPwd(xf, pwd);
        if (id != null) {
            return true;
        } else {
            return false;
        }

    }

    public void changeImStatus(Long xf, UserStatusEnum statusEnum) {
        dao.changeImStatus(xf, statusEnum.getStatus());
    }

    public void changeNzqStatus(Long xf, NzqStatusEnum statusEnum) {
        dao.changeNzqStatus(xf, statusEnum.getStatus());
    }

    public SelfUserInfo querySelfUserInfo(Long xf) {
        SelfUserInfo selfUserInfo = dao.querySelfUserInfo(xf);
        selfUserInfo.setAge(DateUtil.getAge(selfUserInfo.getBirthday()));
        return selfUserInfo;
    }

    public OtherUserInfo searchOtherUser(Long xf) {
        return dao.searchOtherUser(xf);
    }

    public List<OtherUserInfo> searchOtherUsers(String nickname, Byte sex, Byte grade,Integer pageSize, Integer pageNum) {
        int offSet = pageSize * (pageNum - 1);

        return dao.searchOtherUsers(nickname,sex,grade,offSet,pageSize);
    }

    public void updateSelfUserInfo(UpdateSelfInfoRequest request, Long xf) {
        User user = new User();
        user.setId(xf);
        BeanUtils.copyProperties(request, user);
        mapper.updateByPrimaryKeySelective(user);
    }
}
