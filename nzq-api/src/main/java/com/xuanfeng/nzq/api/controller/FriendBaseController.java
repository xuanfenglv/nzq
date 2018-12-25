package com.xuanfeng.nzq.api.controller;

import com.xuanfeng.nzq.api.request.friends.UpdateGroupRequest;
import com.xuanfeng.nzq.api.request.friends.UpdateRemarkRequest;
import com.xuanfeng.nzq.api.response.user.FriendUserInfo;
import com.xuanfeng.nzq.commons.Result;
import org.springframework.web.bind.annotation.*;

@RequestMapping("friends")
public interface FriendBaseController {

    @GetMapping("{id}")
    Result<FriendUserInfo> getFriendUserInfo(@PathVariable("id") Long id);

    @PutMapping("remark")
    Result updateRemark(@RequestBody UpdateRemarkRequest request);

    @PutMapping("group")
    Result updateGroup(@RequestBody UpdateGroupRequest request);
}
