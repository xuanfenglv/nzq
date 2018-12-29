package com.xuanfeng.nzq.websocket.main.im.msg.response;

import com.xuanfeng.nzq.domain.entity.FriendInfo;
import com.xuanfeng.nzq.domain.entity.GroupInfo;

import java.util.List;

/**
 * @description: 初始化账号响应
 * @author: lvxianqing
 * @create: 2018/11/23 10:45
 */

public class InitAccountResp {
    private List<GroupInfo> groupList;
    private List<FriendInfo> friendList;

    public List<GroupInfo> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupInfo> groupList) {
        this.groupList = groupList;
    }

    public List<FriendInfo> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<FriendInfo> friendList) {
        this.friendList = friendList;
    }
}
