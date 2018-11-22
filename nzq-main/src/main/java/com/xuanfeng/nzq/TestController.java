package com.xuanfeng.nzq;

import com.xuanfeng.nzq.domain.mapper.UserMapper;
import com.xuanfeng.nzq.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/11/22 17:58
 */
//@RestController
public class TestController {
    @Autowired
    private UserMapper mapper;

    @GetMapping("user")
    User getUser() {
        return mapper.selectByPrimaryKey(10001l);
    }
}
