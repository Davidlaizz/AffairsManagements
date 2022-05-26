package com.pigeon.affairsmanagements.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pigeon.affairsmanagements.utils.Result;
import com.pigeon.affairsmanagements.utils.Status;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.Writer;

public class IndexInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
/*        Cookie[] cookies = session.getAttribute();*/
        response.setContentType("application/json;charset=utf-8");
        Boolean isCookieFind = false;
/*        for(Cookie cookie : cookies)
        {
            String cookieName = cookie.getName();
            if(cookieName.equals("user"))
                isCookieFind = true;
        }
        if(!isCookieFind)
        {

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Result<String> status = new Result<String>(Status.ERROR, "");
            String res = mapper.writeValueAsString(status);
            out.println(res);
*//*            response.sendRedirect("login.html");*//*
            return false;
        }
        else
            return true;*/

        if(session.getAttribute("user") == null)
        {

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Result<String> status = new Result<String>(Status.ERROR, "未登录");
            String res = mapper.writeValueAsString(status);
            out.println(res);
            /*            response.sendRedirect("login.html");*/
            return false;
        }
        else
            return true;

    }


}
