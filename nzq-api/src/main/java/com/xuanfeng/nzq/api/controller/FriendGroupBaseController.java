package com.xuanfeng.nzq.api.controller;

import com.xuanfeng.nzq.api.request.group.AddGroupReq;
import com.xuanfeng.nzq.api.request.group.UpdateGroupReq;
import com.xuanfeng.nzq.commons.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 好友分组控制器接口
 * @author: lvxianqing
 * @create: 2018/12/16 16:35
 */

@RequestMapping("friend-groups")
public interface FriendGroupBaseController {

    @PostMapping("")
    public Result add(@RequestBody AddGroupReq req);

    @PutMapping("")
    Result update(@RequestBody UpdateGroupReq req);

    @DeleteMapping("{id}")
    Result delete(@PathVariable Long id);

}
