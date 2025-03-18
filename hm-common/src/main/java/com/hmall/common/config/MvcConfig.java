package com.hmall.common.config;


import com.hmall.common.interceptor.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Lord_Bao
 * @Date 2025/3/11 15:14
 * @Version 1.0
 */
@Configuration
//springmvc模块才能加载此配置. gateway模块基于webFlux,是不会加载此配置,故而不会出问题.
@ConditionalOnClass(DispatcherServlet.class)
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加用户信息拦截器
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
