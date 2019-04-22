package com.xuanfeng.nzq.commons.utils;

import com.xuanfeng.nzq.commons.javabean.UserSessionInfo;
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
    public static HttpSession getSession(boolean create) {

        return getRequest().getSession(create);
    }

    public static UserSessionInfo getUserSessionInfo() {
        return (UserSessionInfo)getSession(false).getAttribute("user");
    }

    /**
     * 获取当前用户账号
     * @return
     */
    public static Long getXf() {
        return getUserSessionInfo().getXf();
    }

}
