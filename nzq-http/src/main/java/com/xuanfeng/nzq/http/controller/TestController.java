package com.xuanfeng.nzq.http.controller;

import com.xuanfeng.nzq.domain.mapper.UserMapper;
import com.xuanfeng.nzq.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("index")
    public String index() {
        return "game/index";
    }

    @RequestMapping("hello")
    public String hello() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("hello");
//        modelAndView.addObject("xf", 111);
//        return modelAndView;
        return "{\"type\",\"get\"}";
    }

    @RequestMapping(value = "put", method = RequestMethod.PUT)
    public String put() {

        return "{\"type\",\"put\"}";
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete() {

        return "{\"type\",\"delete\"}";
    }

    @GetMapping("user")
    private void getUser(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        System.out.println(user);
    }
}
