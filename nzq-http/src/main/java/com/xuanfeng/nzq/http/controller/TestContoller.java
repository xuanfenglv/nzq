package com.xuanfeng.nzq.http.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2019/04/25 21:29
 */
@RestController
public class TestContoller {
    @RequestMapping("tt")
    int test() throws Exception {
        if (true) {
            throw new Exception("paoyige");
        }

        return 1;
    }

}
