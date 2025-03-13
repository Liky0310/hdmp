package com.hmdp.interceptor;

import com.hmdp.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: LoginInterceptor
 * Package: com.hmdp.interceptor
 * Description:
 *
 * @Author lky
 * @Create 2025/3/14 00:43
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户
        if (UserHolder.getUser() == null) {
            //不存在用户 拦截
            response.setStatus(401);
            return false;
        }
        //存在用户放行
        return true;
    }
}
