package com.xuanfeng.nzq.http.controller;

import com.xuanfeng.nzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.RequestDispatcher;


/**
 * Servlet implementation class LoginServlet
 */
@Controller
public class LoginController {

    @Autowired
    private UserService service;

    @PostMapping("login")
    public String login(Long xf, String pwd, Model model) {
        // TODO Auto-generated method stub
        boolean result = service.login(xf, pwd);

        RequestDispatcher dis = null;
        if (result == true) {
            model.addAttribute("xf", xf);
            return "game/index";
        } else {
            return "/jsp/game/fail.html";
        }
    }

    @GetMapping("/index")
    public String index() {
        return "game/index";
    }
}
