package com.crh.wxbase.common.config;

import com.crh.wxbase.common.config.interceptor.ApiLoginHandlerInterceptor;
import com.crh.wxbase.common.config.interceptor.ManagerLoginHandlerInterceptor;
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
        //管理后台拦截器
        registry.addInterceptor(new ManagerLoginHandlerInterceptor())
                .addPathPatterns("/manager/**");
        //小程序拦截器
        registry.addInterceptor(new ApiLoginHandlerInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/syswxuser/login");
    }
    /*  swagger excludePathPatterns
        "/swagger-ui.html",
        "/configuration/ui",
        "/swagger-resources/**",
        "/configuration/security",
        "/v2/api-docs",
        "/error",
        "/csrf",
        "/webjars/**",
        "/**-/favicon.ico"
    */

}
