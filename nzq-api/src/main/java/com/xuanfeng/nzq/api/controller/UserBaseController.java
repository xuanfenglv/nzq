package com.xuanfeng.nzq.api.controller;

import com.xuanfeng.nzq.api.request.user.LoginRequest;
import com.xuanfeng.nzq.api.request.user.RegisterUserRequest;
import com.xuanfeng.nzq.api.request.user.UpdateSelfInfoRequest;
import com.xuanfeng.nzq.api.response.user.OtherUserInfo;
import com.xuanfeng.nzq.api.response.user.SelfUserInfo;
import com.xuanfeng.nzq.commons.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 用户controller接口
 * @author: lvxianqing
 * @create: 2018/11/22 12:33
 */
@RequestMapping("users")
public interface UserBaseController {
    @PostMapping("")
    Result<Long> register(@RequestBody RegisterUserRequest request);

    @PostMapping("login")
    Result login(@RequestBody LoginRequest request);

    @GetMapping("self")
    Result<SelfUserInfo> querySelfUserInfo();

    @GetMapping("strangers/{xf}")
    Result<OtherUserInfo> searchOtherUser(@PathVariable("xf") Long xf);

    // TODO: 2018/11/22 后期做分页
    @GetMapping("strangers")
    Result<List<OtherUserInfo>> searchOtherUsers(
            @RequestParam(value = "nickname",required = false) String nickname,
            @RequestParam(value = "sex",required = false) Byte sex,
            @RequestParam(value = "grade",required = false) Byte grade,
            @RequestParam(value = "pageSize",required = false,defaultValue = "100") Integer pageSize,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum
    );

    @PutMapping("self")
    Result updateSelfUserInfo(@RequestBody UpdateSelfInfoRequest request);
}
