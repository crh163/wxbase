package com.crh.wxbase.common.config;

import com.crh.wxbase.common.config.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author rory.chen
 * @date 2021-02-24 17:43
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册登录拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/",
                        "/sysuser/login",

                        //swagger
                        "/swagger-ui.html",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/v2/api-docs",
                        "/error",
                        "/csrf",
                        "/webjars/**",
                        "/**/favicon.ico");
    }
}
