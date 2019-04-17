package com.xuanfeng.nzq.api.controller;

import com.xuanfeng.nzq.api.response.app.ShortApplication;
import com.xuanfeng.nzq.commons.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("applications")
public interface ApplicationBaseController {

    @RequestMapping("{xf}")
    Result<List<ShortApplication>> selectByXf(@PathVariable("xf") Long xf);
}
