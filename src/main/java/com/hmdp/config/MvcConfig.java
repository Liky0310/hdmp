package com.hmdp.config;

import com.hmdp.interceptor.LoginInterceptor;
import com.hmdp.interceptor.RefreshTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MvcConfig
 * Package: com.hmdp.config
 * Description:
 *
 * @Author lky
 * @Create 2025/3/14 00:33
 * @Version 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
     /**
      * 添加拦截器
      *
      * @param registry 拦截器注册中心
      */
    @Override
     public void addInterceptors(InterceptorRegistry registry) {
         //登陆拦截器
         registry
                 .addInterceptor(new LoginInterceptor())
                 .excludePathPatterns("/user/code"
                         , "/user/login"
                         , "/blog/hot"
                         , "/shop/**"
                         , "/shop-type/**"
                         , "/upload/**"
                         , "/voucher/**"
                 )
                 .order(1);
         //Token续命拦截器
         registry
                 .addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                 .addPathPatterns("/**")
                 .order(0);
     }
}
