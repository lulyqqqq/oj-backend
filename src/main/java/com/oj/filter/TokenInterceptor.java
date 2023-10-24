package com.oj.config;

import com.google.gson.Gson;
import com.oj.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private JWTUtils jwtUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        response.setCharacterEncoding("utf-8");

        String token = jwtUtils.getToken(request);
        if (token != null) {
            boolean result = jwtUtils.verifyToken(token);
            if (result) {
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            Map<String,Object> json = new HashMap<>();
            json.put("success", "false");
            json.put("msg", "认证失败，未通过拦截器");
            json.put("code", "500");
            Gson gson = new Gson();
            String jsonString = gson.toJson(json);
            response.getWriter().append(jsonString);
            System.out.println("认证失败，未通过拦截器");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;

    }
}
