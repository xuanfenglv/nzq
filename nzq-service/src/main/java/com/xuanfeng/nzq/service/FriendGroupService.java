package com.xuanfeng.nzq.service;

import com.alibaba.fastjson.JSON;
import com.xuanfeng.nzq.api.request.group.AddGroupReq;
import com.xuanfeng.nzq.api.request.group.UpdateGroupReq;
import com.xuanfeng.nzq.commons.Result;
import com.xuanfeng.nzq.commons.ResultUtil;
import com.xuanfeng.nzq.commons.RetEnum;
import com.xuanfeng.nzq.commons.javabean.UserSessionInfo;
import com.xuanfeng.nzq.commons.utils.SessionUtil;
import com.xuanfeng.nzq.domain.dao.FriendDao;
import com.xuanfeng.nzq.domain.dao.FriendGroupDao;
import com.xuanfeng.nzq.domain.mapper.FriendGroupMapper;
import com.xuanfeng.nzq.domain.model.FriendGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.xuanfeng.nzq.commons.RetEnum.非法请求;

/**
 * @description: 好友分组业务处理类
 * @author: lvxianqing
 * @create: 2018/12/16 16:34
 */
@Service
public class FriendGroupService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FriendGroupMapper mapper;
    @Autowired
    private FriendGroupDao dao;
    @Autowired
    private FriendDao friendDao;

    public Result add(AddGroupReq req) {
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setXf(req.getXf());
        friendGroup.setName(req.getName());
        mapper.insertSelective(friendGroup);
        logger.info("insert FriendGroup:{}", JSON.toJSONString(friendGroup));
        return ResultUtil.createSuccessResult(friendGroup.getId());
    }

    public Result update(UpdateGroupReq req) {
        // 校验分组是不是属于此人
        FriendGroup friendGroup = mapper.selectByPrimaryKey(req.getId());
        // 不是你的分组
        if (!friendGroup.getXf().equals(req.getXf())) {
            logger.info("不可修改不属于你的分组");
            return ResultUtil.createFailedResult(非法请求, "不可修改不属于你的分组");
        }
        FriendGroup friendGroup1 = new FriendGroup();
        BeanUtils.copyProperties(req, friendGroup1);
        mapper.updateByPrimaryKeySelective(friendGroup1);
        logger.info("update FriendGroup:{}", JSON.toJSONString(friendGroup));

        return ResultUtil.createSuccessResult();
    }

    @Transactional
    public Result delete(Long groupId) {
        // 1. 从session中取出xf
        UserSessionInfo userSessionInfo = SessionUtil.getUserSessionInfo();
        Long xf = userSessionInfo.getXf();

        // 2. 校验分组是不是属于此人
        FriendGroup friendGroup = mapper.selectByPrimaryKey(groupId);
        if (!friendGroup.getXf().equals(xf)) {
            logger.info("不可删除别人的分组");
            return ResultUtil.createFailedResult(RetEnum.非法请求, "不可删除别人的分组");
        }

        // 3. 查询默认分组
        Long defaultGroupId = dao.selectDefaultGroupId(userSessionInfo.getXf());
        logger.info("默认分组id:{}", defaultGroupId);
        if (defaultGroupId.equals(groupId)) {
            // 不可删除默认分组
            logger.info("不可删除默认分组");
            return ResultUtil.createFailedResult(RetEnum.非法请求, "不可删除默认分组");
        }

        // 4. 把删除分组的人移动到默认分组
        friendDao.moveFriend(defaultGroupId, groupId, xf);
        logger.info("把删除分组的人移动到默认分组");

        // 5. 删除分组
        mapper.deleteByPrimaryKey(groupId);
        logger.info("删除分组");
        return ResultUtil.createSuccessResult(defaultGroupId);
    }
}
