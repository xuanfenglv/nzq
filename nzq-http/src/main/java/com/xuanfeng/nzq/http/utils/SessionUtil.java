package com.xuanfeng.nzq.http.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

//    public static int getMyXf() {
//        HttpSession session = getSession();
//        if (session == null) {
//            return 0;
//        }
//        return ((SimpleUser)session.getAttribute("user")).getXf();
//    }

    /**
     * SpringMvc下获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;

    }

    /**
     * SpringMvc下获取session
     *
     * @return
     */
    public static HttpSession getSession() {

        return getRequest().getSession(false);
    }


}
