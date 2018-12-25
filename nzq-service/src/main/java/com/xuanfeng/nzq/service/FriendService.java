package com.xuanfeng.nzq.service;

import com.xuanfeng.nzq.api.request.friends.UpdateGroupRequest;
import com.xuanfeng.nzq.api.request.friends.UpdateRemarkRequest;
import com.xuanfeng.nzq.api.response.user.FriendUserInfo;
import com.xuanfeng.nzq.domain.dao.FriendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 好友业务处理类
 * @author: lvxianqing
 * @create: 2018/12/15 17:46
 */
@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    public FriendUserInfo queryFriendInfo(Long xf) {
        return friendDao.selectFriendInfo(xf);
    }

    public void updateRemark(UpdateRemarkRequest request) {
        friendDao.updateRemark(request);
    }

    public void updateGroup(UpdateGroupRequest request) {
        friendDao.updateGroup(request);
    }
}
